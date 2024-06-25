package com.ioomex.youleoffice.sys_user.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 行为表
 */
@Data
@TableName(value = "tb_action")
public class TbAction implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 行为编号
     */
    @TableField(value = "action_code")
    private String actionCode;

    /**
     * 行为名称
     */
    @TableField(value = "action_name")
    private String actionName;

    private static final long serialVersionUID = 1L;
}