package com.ioomex.youleoffice.sys_user.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 疫情城市列表
 */
@Data
@TableName(value = "tb_city")
public class TbCity implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 城市名称
     */
    @TableField(value = "city")
    private String city;

    /**
     * 拼音简称
     */
    @TableField(value = "code")
    private String code;

    private static final long serialVersionUID = 1L;
}