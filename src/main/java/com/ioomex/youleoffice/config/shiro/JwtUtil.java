package com.ioomex.youleoffice.config.shiro;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JwtUtil: jwt token 工具生成
 *
 * @author sutton
 * @since 2024-06-11 22:55
 */
@Component
@Slf4j
public class JwtUtil {

    @Value("${youleoffice.jwt.secret}")
    private String secret;

    @Value("${youleoffice.jwt.expire}")
    private Integer expire;

    /**
     * 生成token
     *
     * @param user 存储用户信息
     * @return token
     */
    public String generateToken(Integer userId) {
        DateTime offset = DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, 5);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTCreator.Builder builder = JWT.create();
        return builder
          .withClaim("userId", userId)
          .withExpiresAt(offset)
          .sign(algorithm);
    }

    /**
     * 获取token中的用户信息
     *
     * @param token token
     * @return 用户id
     */
    public Integer getUserId(String token) {
        DecodedJWT decodedJwt = JWT.decode(token);
        return decodedJwt.getClaim("userId").asInt();
    }

    /**
     * 验证token是否有效
     *
     * @param token token
     */
    public void verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWT.require(algorithm).build().verify(token);
    }
}