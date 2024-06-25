package com.ioomex.youleoffice.sys_user.controller;
import com.ioomex.youleoffice.common.ApiConstant;
import com.ioomex.youleoffice.config.shiro.JwtUtil;
import com.ioomex.youleoffice.sys_user.entity.param.LoginParam;
import com.ioomex.youleoffice.sys_user.entity.param.RegisterParam;
import com.ioomex.youleoffice.sys_user.entity.po.TbUser;
import com.ioomex.youleoffice.sys_user.service.TbUserService;
import com.ioomex.youleoffice.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
* 用户表(tb_user)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/user")
public class TbUserController {
/**
* 服务对象
*/
    @Autowired
    private TbUserService userService;


    @Value("${youleoffice.jwt.cache-expire}")
    private int cacheExpire;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate redisTemplate;


//
//    @Autowired
//    private  HttpService httpService;


    @PostMapping("/register")
    @ApiOperation("注册用户")
    public R register(@Valid @RequestBody RegisterParam form){
        int id=userService.registerUser(form.getRegisterCode(),form.getCode(),form.getNickname(),form.getPhoto());
        String token=jwtUtil.generateToken(id);
        Set<String> permsSet=userService.searchUserPermissions(id);
        saveCacheToken(token,id);
        return R.ok("用户注册成功").put("token",token).put("permission",permsSet);
    }



    @PostMapping("/login")
    @ApiOperation("登陆系统")
    public R login(@Valid @RequestBody LoginParam form){
        int id=userService.login(form.getCode());
        String token=jwtUtil.generateToken(id);
        saveCacheToken(token,id);
        Set<String> permsSet = userService.searchUserPermissions(id);
        return R.ok("登陆成功").put("token",token).put("permission",permsSet);
    }


    private void saveCacheToken(String token,int userId){
        redisTemplate.opsForValue().set(token,userId+"",cacheExpire, TimeUnit.DAYS);
    }


    @GetMapping("/data")
    public String getData(String year) {
//        return httpService.getByServiceName("holiday", ApiConstant.HOLIDAYS + year);
        return "";
    }

}
