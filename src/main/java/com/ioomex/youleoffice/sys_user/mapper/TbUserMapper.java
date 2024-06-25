package com.ioomex.youleoffice.sys_user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ioomex.youleoffice.sys_user.entity.po.TbUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.Set;

@Mapper
public interface TbUserMapper extends BaseMapper<TbUser> {


    boolean haveRootUser();

    Integer searchIdByOpenId(String openId);

    int  insertUserData(HashMap param);

    Set<String> searchUserPermissions(int userId);
}