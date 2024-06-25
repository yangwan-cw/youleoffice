package com.ioomex.youleoffice.sys_user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@Data
@TableName(value = "sys_config")
public class SysConfig implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 参数名
     */
    @TableField(value = "param_key")
    private String paramKey;

    /**
     * 参数值
     */
    @TableField(value = "param_value")
    private String paramValue;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    private Boolean status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;
}