package com.ioomex.youleoffice.sys_user.controller;
import com.ioomex.youleoffice.sys_user.entity.po.TbHolidays;
import com.ioomex.youleoffice.sys_user.service.TbHolidaysService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 节假日表(tb_holidays)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/tb_holidays")
public class TbHolidaysController {
/**
* 服务对象
*/
    @Autowired
    private TbHolidaysService tbHolidaysService;

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
    @GetMapping("selectOne")
    public TbHolidays selectOne(Integer id) {
    return tbHolidaysService.getById(id);
    }

}
