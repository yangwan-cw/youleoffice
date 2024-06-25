package com.ioomex.youleoffice.sys_user.controller;
import com.ioomex.youleoffice.sys_user.entity.po.TbAction;
import com.ioomex.youleoffice.sys_user.service.TbActionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 行为表(tb_action)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/tb_action")
@Api("action")
public class TbActionController {
/**
* 服务对象
*/
    @Autowired
    private TbActionService tbActionService;

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
    @GetMapping("selectOne")
    public TbAction selectOne(Integer id) {

    return tbActionService.getById(id);
    }

}
