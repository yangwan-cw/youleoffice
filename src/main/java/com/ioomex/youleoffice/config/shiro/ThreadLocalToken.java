package com.ioomex.youleoffice.config.shiro;

import org.springframework.stereotype.Component;

/**
 * token 存放到线程变量中，为了在各个地方都能获取到，方便各个地方的鉴权
 * 并且放这里也有好处就是是线程隔离
 *
 * @author ioomex
 * @since 2024-06-12 18:31
 */
@Component
public class ThreadLocalToken {

    private final ThreadLocal<String> local = new ThreadLocal<>();

    public void set(String token) {
        local.set(token);
    }

    public String getToken() {
        return local.get();
    }

    public void clear() {
        local.remove();
    }

}