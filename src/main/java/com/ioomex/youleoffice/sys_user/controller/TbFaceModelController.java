package com.ioomex.youleoffice.sys_user.controller;
import cn.hutool.core.util.ObjUtil;
import com.ioomex.youleoffice.config.xxs.ExcludeFromXssFilter;
import com.ioomex.youleoffice.exception.YoulezuoException;
import com.ioomex.youleoffice.sys_user.entity.po.TbFaceModel;
import com.ioomex.youleoffice.sys_user.service.TbFaceModelService;
import com.ioomex.youleoffice.utils.ResultUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* (tb_face_model)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/tb_face_model")
public class TbFaceModelController {
/**
* 服务对象
*/
    @Autowired
    private TbFaceModelService tbFaceModelService;


}
