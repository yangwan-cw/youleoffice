package com.ioomex.youleoffice.sys_user.controller;
import com.ioomex.youleoffice.config.swagger.StartSwaggerScan;
import com.ioomex.youleoffice.sys_user.entity.po.TbCheckin;
import com.ioomex.youleoffice.sys_user.service.TbCheckinService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 签到表(tb_checkin)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/tb_checkin")
@StartSwaggerScan
public class TbCheckinController {
/**
* 服务对象
*/
    @Autowired
    private TbCheckinService tbCheckinService;

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
    @GetMapping("selectOne")
    public TbCheckin selectOne(Integer id) {
    return tbCheckinService.getById(id);
    }

}
