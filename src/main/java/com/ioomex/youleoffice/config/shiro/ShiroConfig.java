package com.ioomex.youleoffice.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 配置类
 *
 * @author ioome
 * @since 2024-06-12 20:20
 */

@Configuration
public class ShiroConfig {


    /**
     * 配置核心安全事务管理器
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(OAuth2Realm oAuth2Realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置自定义realm.
        securityManager.setRealm(oAuth2Realm);
        // 配置记住我
        securityManager.setRememberMeManager(null);
        return securityManager;
    }


    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * Web应用中,Shiro可控制的Web请求必须经过Shiro主过滤器的拦截
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, OAuth2Filter oauth2Filter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("oauth2Filter", oauth2Filter);
        shiroFilterFactoryBean.setFilters(filterMap);


        Map<String, String> NoAuthenticationMap = new LinkedHashMap<>();
        NoAuthenticationMap.put("/webjars/**", "anon");
        NoAuthenticationMap.put("/druid/**", "anon");
        NoAuthenticationMap.put("/app/**", "anon");
        NoAuthenticationMap.put("/sys/login", "anon");
        NoAuthenticationMap.put("/swagger/**", "anon");
        NoAuthenticationMap.put("/v2/api-docs", "anon");
        NoAuthenticationMap.put("/swagger-ui.html", "anon");
        NoAuthenticationMap.put("/swagger-resources/**", "anon");
        NoAuthenticationMap.put("/user/register", "anon");
        NoAuthenticationMap.put("/user/login", "anon");
        NoAuthenticationMap.put("/user/data", "anon");
        NoAuthenticationMap.put("/captcha.jpg", "anon");
        NoAuthenticationMap.put("/test/**", "anon");
        NoAuthenticationMap.put("/**", "oauth2Filter");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(NoAuthenticationMap);
        return shiroFilterFactoryBean;
    }

    /**
     * shiro 生命周期
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 鉴权
     */
    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}