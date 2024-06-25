package com.ioomex.youleoffice.sys_user.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 模块资源表
 */
@Data
@TableName(value = "tb_module")
public class TbModule implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 模块编号
     */
    @TableField(value = "module_code")
    private String moduleCode;

    /**
     * 模块名称
     */
    @TableField(value = "module_name")
    private String moduleName;

    private static final long serialVersionUID = 1L;
}