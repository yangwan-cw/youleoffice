package com.ioomex.youleoffice.sys_user.controller;

import com.ioomex.youleoffice.sys_user.service.SysConfigService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



/**
* (sys_config)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/sys_config")
@RequiredArgsConstructor
public class SysConfigController {

    private  final  SysConfigService sysConfigService;




}
