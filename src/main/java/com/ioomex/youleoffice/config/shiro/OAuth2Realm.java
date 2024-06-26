package com.ioomex.youleoffice.config.shiro;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ioomex.youleoffice.exception.YoulezuoException;
import com.ioomex.youleoffice.sys_user.entity.po.TbUser;
import com.ioomex.youleoffice.sys_user.service.TbUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * OAuth2Realm
 *
 * @author sutton
 * @since 2024-06-11 23:30
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TbUserService tbUserService;


    /**
     * @param token 传入的token 符不符合OAuth2Token对象
     * @return 符合或者不符合OAuth2Token对象
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }


    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        TbUser tbUser = (TbUser) principalCollection.getPrimaryPrincipal();
        Set<String> permissions = tbUserService.searchUserPermissions(tbUser.getId());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String parseToken = authenticationToken.getPrincipal().toString();
        Integer userId = jwtUtil.getUserId(parseToken);

        TbUser tbUser = tbUserService.getOne(new LambdaQueryWrapper<TbUser>()
          .eq(TbUser::getId, userId)
          .eq(TbUser::getStatus, 1));

        if(ObjUtil.isEmpty(tbUser)){
            throw new YoulezuoException("账号不存在或被封禁,请联系管理员");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(tbUser, parseToken, getName());
        return simpleAuthenticationInfo;
    }
}