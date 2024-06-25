package com.ioomex.youleoffice.sys_user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ioomex.youleoffice.sys_user.entity.po.TbCheckin;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface TbCheckinMapper extends BaseMapper<TbCheckin> {
    Integer haveCheckin(HashMap map);
}