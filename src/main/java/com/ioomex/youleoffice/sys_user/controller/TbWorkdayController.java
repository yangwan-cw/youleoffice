package com.ioomex.youleoffice.sys_user.controller;
import com.ioomex.youleoffice.sys_user.entity.po.TbWorkday;
import com.ioomex.youleoffice.sys_user.service.TbWorkdayService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* (tb_workday)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/tb_workday")
public class TbWorkdayController {
/**
* 服务对象
*/
    @Autowired
    private TbWorkdayService tbWorkdayService;

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
    @GetMapping("selectOne")
    public TbWorkday selectOne(Integer id) {
    return tbWorkdayService.getById(id);
    }

}
