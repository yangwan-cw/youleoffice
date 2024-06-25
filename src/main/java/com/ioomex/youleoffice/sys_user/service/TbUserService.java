package com.ioomex.youleoffice.sys_user.service;

import com.ioomex.youleoffice.sys_user.entity.po.TbUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

public interface TbUserService extends IService<TbUser>{
    public int registerUser(String registerCode, String code, String nickname, String photo);

    public Set<String> searchUserPermissions(int userId);

    Integer login(String code);
}
