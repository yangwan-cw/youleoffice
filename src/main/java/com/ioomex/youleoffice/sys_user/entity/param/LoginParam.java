package com.ioomex.youleoffice.sys_user.entity.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel
public class LoginParam {
    @NotBlank(message = "临时授权不能为空")
    private String code;
}
