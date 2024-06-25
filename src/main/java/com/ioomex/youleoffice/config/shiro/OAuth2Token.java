package com.ioomex.youleoffice.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * OAuth2Token: 用于封装 token 为了让 shiro 识别
 *
 * @author sutton
 * @since 2024-06-11 23:28
 */
public class OAuth2Token implements AuthenticationToken {

    private final String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}