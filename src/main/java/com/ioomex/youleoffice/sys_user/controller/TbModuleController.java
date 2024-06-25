package com.ioomex.youleoffice.sys_user.controller;
import com.ioomex.youleoffice.sys_user.entity.po.TbModule;
import com.ioomex.youleoffice.sys_user.service.TbModuleService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 模块资源表(tb_module)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/tb_module")
public class TbModuleController {
/**
* 服务对象
*/
    @Autowired
    private TbModuleService tbModuleService;

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
    @GetMapping("selectOne")
    public TbModule selectOne(Integer id) {
    return tbModuleService.getById(id);
    }

}
