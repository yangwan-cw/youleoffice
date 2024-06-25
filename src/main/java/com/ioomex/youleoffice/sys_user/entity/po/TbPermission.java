package com.ioomex.youleoffice.sys_user.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@Data
@TableName(value = "tb_permission")
public class TbPermission implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 权限
     */
    @TableField(value = "permission_name")
    private String permissionName;

    /**
     * 模块ID
     */
    @TableField(value = "module_id")
    private Integer moduleId;

    /**
     * 行为ID
     */
    @TableField(value = "action_id")
    private Integer actionId;

    private static final long serialVersionUID = 1L;
}