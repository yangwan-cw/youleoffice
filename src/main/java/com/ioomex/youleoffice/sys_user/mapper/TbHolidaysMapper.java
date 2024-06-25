package com.ioomex.youleoffice.sys_user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ioomex.youleoffice.sys_user.entity.po.TbHolidays;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbHolidaysMapper extends BaseMapper<TbHolidays> {
    Integer searchTodayIsHolidays();
}