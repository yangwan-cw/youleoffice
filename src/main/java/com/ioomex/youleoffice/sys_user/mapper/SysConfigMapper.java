package com.ioomex.youleoffice.sys_user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ioomex.youleoffice.sys_user.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfig> {

    List<SysConfig> selectAllData();
}