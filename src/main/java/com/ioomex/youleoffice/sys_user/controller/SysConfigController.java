package com.ioomex.youleoffice.sys_user.controller;
import com.ioomex.myhttpclientstarter.HttpService;
import com.ioomex.youleoffice.sys_user.entity.SysConfig;
import com.ioomex.youleoffice.sys_user.service.SysConfigService;
import com.ioomex.youleoffice.sys_user.service.impl.SysConfigServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

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
