package com.ioomex.youleoffice.sys_user.controller;
import com.ioomex.youleoffice.sys_user.entity.po.TbPermission;
import com.ioomex.youleoffice.sys_user.service.TbPermissionService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* (tb_permission)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/tb_permission")
public class TbPermissionController {
/**
* 服务对象
*/
    @Autowired
    private TbPermissionService tbPermissionService;

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
    @GetMapping("selectOne")
    public TbPermission selectOne(Integer id) {
    return tbPermissionService.getById(id);
    }

}
