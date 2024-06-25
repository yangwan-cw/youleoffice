package com.ioomex.youleoffice.sys_user.controller;
import cn.hutool.core.date.DateUtil;
import com.ioomex.youleoffice.config.shiro.JwtUtil;
import com.ioomex.youleoffice.config.swagger.StartSwaggerScan;
import com.ioomex.youleoffice.sys_user.entity.po.TbCheckin;
import com.ioomex.youleoffice.sys_user.service.TbCheckinService;
import com.ioomex.youleoffice.utils.R;
import io.swagger.annotations.ApiOperation;
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
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TbCheckinService checkinService;
    @GetMapping("/validCanCheckIn")
    @ApiOperation("查看用户今天是否可以签到")
    public R validCanCheckIn(@RequestHeader("token") String token){
        int userId=jwtUtil.getUserId(token);
        String result=checkinService.validCanCheckIn(userId, DateUtil.today());
        return R.ok(result);
    }

}
