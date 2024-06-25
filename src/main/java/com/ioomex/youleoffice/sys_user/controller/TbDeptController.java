package com.ioomex.youleoffice.sys_user.controller;
import com.ioomex.youleoffice.sys_user.entity.po.TbDept;
import com.ioomex.youleoffice.sys_user.service.TbDeptService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* (tb_dept)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/tb_dept")
public class TbDeptController {
/**
* 服务对象
*/
    @Autowired
    private TbDeptService tbDeptService;

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
    @GetMapping("selectOne")
    public TbDept selectOne(Integer id) {
    return tbDeptService.getById(id);
    }

}
