package com.ioomex.youleoffice.sys_user.controller;
import com.ioomex.youleoffice.sys_user.entity.po.TbRole;
import com.ioomex.youleoffice.sys_user.service.TbRoleService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 角色表(tb_role)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/tb_role")
public class TbRoleController {
/**
* 服务对象
*/
    @Autowired
    private TbRoleService tbRoleService;

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
    @GetMapping("selectOne")
    public TbRole selectOne(Integer id) {
    return tbRoleService.getById(id);
    }

}
