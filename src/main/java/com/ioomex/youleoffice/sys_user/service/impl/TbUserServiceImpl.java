package com.ioomex.youleoffice.sys_user.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ioomex.youleoffice.exception.YoulezuoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ioomex.youleoffice.sys_user.mapper.TbUserMapper;
import com.ioomex.youleoffice.sys_user.entity.po.TbUser;
import com.ioomex.youleoffice.sys_user.service.TbUserService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

@Service
@Slf4j
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {


    @Value("${wx.appid}")
    private String appId;

    @Value("${wx.secret}")
    private String appSecret;

    @Autowired
    private  TbUserMapper tbUserMapper;


    private String getOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        HashMap<String, Object> map = new HashMap<>();
        map.put("appid", appId);
        map.put("secret", appSecret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String response = HttpUtil.post(url, map);
        JSONObject json = JSONUtil.parseObj(response);
        String openId = json.getStr("openid");
        if (openId == null || openId.isEmpty()) {
            throw new RuntimeException("临时登陆凭证错误");
        }
        return openId;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int registerUser(String registerCode, String code, String nickname, String photo) {
        if ("000000".equals(registerCode)) {
            boolean bool = tbUserMapper.haveRootUser();
            if(bool){
                throw new YoulezuoException("存在超级管理员账户");
            }
            if (!bool) {
                String openId = getOpenId(code);
                HashMap<String, Object> param = new HashMap<>();
                param.put("openId", openId);
                param.put("nickname", nickname);
                param.put("photo", photo);
                param.put("role", "[0]");
                param.put("status", 1);
                param.put("createTime", LocalDateTime.now());
                param.put("root", true);
                tbUserMapper.insertUserData(param);
                int id = tbUserMapper.searchIdByOpenId(openId);
                return id;
            } else {
                throw new YoulezuoException("无法绑定超级管理员账号");
            }
        }
        return 0;
    }


    @Override
    public Set<String> searchUserPermissions(int userId) {
        Set<String> permissions=tbUserMapper.searchUserPermissions(userId);
        return permissions;
    }


    @Override
    public Integer login(String code) {
        String openId=getOpenId(code);
        Integer id=tbUserMapper.searchIdByOpenId(openId);
        if(id==null){
            throw new YoulezuoException("帐户不存在");
        }

        return id;
    }
}
