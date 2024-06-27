package com.ioomex.youleoffice.sys_user.controller;

import com.ioomex.youleoffice.sys_user.entity.po.TbCity;
import com.ioomex.youleoffice.sys_user.service.TbCityService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 疫情城市列表(tb_city)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/tb_city")
public class TbCityController {
    /**
     * 服务对象
     */
    @Autowired
    private TbCityService tbCityService;




}
