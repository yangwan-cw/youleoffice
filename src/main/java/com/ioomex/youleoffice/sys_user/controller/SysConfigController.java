package com.ioomex.youleoffice.sys_user.controller;
import com.ioomex.youleoffice.sys_user.entity.SysConfig;
import com.ioomex.youleoffice.sys_user.service.impl.SysConfigServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* (sys_config)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/sys_config")
public class SysConfigController {
/**
* 服务对象
*/
    @Autowired
    private SysConfigServiceImpl sysConfigServiceImpl;

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
    @GetMapping("selectOne")
    public SysConfig selectOne(Integer id) {
        return null;
    }

}
