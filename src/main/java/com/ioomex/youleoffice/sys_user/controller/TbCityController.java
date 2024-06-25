package com.ioomex.youleoffice.sys_user.controller;
import com.ioomex.youleoffice.sys_user.entity.po.TbCity;
import com.ioomex.youleoffice.sys_user.service.TbCityService;
import io.swagger.annotations.ApiOperation;
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

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
    @GetMapping("selectOne")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    public TbCity selectOne(Integer id) {
    return tbCityService.getById(id);
    }

}
