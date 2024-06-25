package com.ioomex.youleoffice.config.shiro;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 过滤请求，鉴权器
 *
 * @author ioomex
 * @since 2024-06-12 18:34
 */
@Component
@Scope("prototype")
@Slf4j
public class OAuth2Filter extends AuthenticatingFilter {

    @Autowired
    private ThreadLocalToken threadLocalToken;

    @Value("${youleoffice.jwt.cache-expire}")
    private Integer cacheExpire;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 从请求中获取令牌对象，封装成 AuthenticationToken
     *
     * @param servletRequest  请求
     * @param servletResponse 返回
     * @return 封装的令牌对象
     * @throws Exception 异常
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = getRequestToken((HttpServletRequest) servletRequest);
        if (StrUtil.isBlank(token)) {
            return null;
        }
        // 将token 封装成OAuth2Token，以便 shiro 框架使用
        return new OAuth2Token(token);
    }

    @Override
    public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        super.doFilterInternal(request, response, chain);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return checkRequest((HttpServletRequest) request);
    }


    /**
     * 拦截请求，判断请求是否需要 shiro 处理,
     * 这里处理shiro 主要的原因是因为浏览的
     * 跨域请求会请求两次，第一次是options
     * 为了预检，第二次才是真正的请求，所以
     * 我们处理一下options请求可以直接跳过shiro的处理
     */
    private boolean checkRequest(HttpServletRequest request) {
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        // 所有 shiro 处理的请求都需要 shiro 处理
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        threadLocalToken.clear();


        // 验证token是否为空,如果为空则返回401,否则继续验证token是否有效
        String token = getRequestToken(req);
        if (StrUtil.isBlank(token)) {
            resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
            resp.getWriter().print("无效的令牌____");
            return false;
        }

        try {
            jwtUtil.verifyToken(token);
        } catch (TokenExpiredException e) {
            // 这里产生异常说明token已经过期,如果redis中有缓存，说明是客户端过期,但是服务端没有过期失效
            if (Boolean.TRUE.equals(redisTemplate.hasKey(token))) {
                // 刷新token
                redisTemplate.delete(token);

                // 通过工具类重新获取 userId
                Integer userId = jwtUtil.getUserId(token);
                token = jwtUtil.generateToken(userId);

                // 存储
                redisTemplate.opsForValue().set(token, userId + "", cacheExpire, TimeUnit.DAYS);

                // 存储到媒介类,用在 aop，这里的aop为了的是客户端判断是否有新的token
                threadLocalToken.set(token);
            } else {
                // 客户端已经过期,服务端也过期,返回401
                resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
                resp.getWriter().print("令牌已过期");
                log.info("令牌已过期");
                return false;
            }
        } catch (Exception e) {
            // 这种是为了防止伪造
            resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
            resp.getWriter().print("无效的令牌____");
            log.info("无效的令牌____");
            return false;
        }
        return executeLogin(req, resp);
    }


    /**
     * 自定义实现 Shiro 身份验证机制的重写方法。当通过 Oauth2Realm 身份认证尝试失败时会调用这个方法
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest servletRequest,
                                     ServletResponse servletResponse) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
        try {
            resp.getWriter().print(e.getMessage());
        }catch (Exception e1){
            e1.printStackTrace();
        }
        return false;
    }



    /**
     * 获取请求头的tokne
     *
     * @param httpServletRequest 请求
     * @return 返回对象
     */
    private String getRequestToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
//        if (StrUtil.isNotEmpty(token)) {
//            token = httpServletRequest.getParameter("token");
//        }
        return token;
    }
}