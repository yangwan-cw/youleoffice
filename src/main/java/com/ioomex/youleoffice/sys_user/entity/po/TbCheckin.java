package com.ioomex.youleoffice.sys_user.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 签到表
 */
@Data
@TableName(value = "tb_checkin")
public class TbCheckin implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 签到地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 国家
     */
    @TableField(value = "country")
    private String country;

    /**
     * 省份
     */
    @TableField(value = "province")
    private String province;

    /**
     * 城市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 区划
     */
    @TableField(value = "district")
    private String district;

    /**
     * 考勤结果
     */
    @TableField(value = "`status`")
    private Byte status;

    /**
     * 风险等级
     */
    @TableField(value = "risk")
    private Integer risk;

    /**
     * 签到日期
     */
    @TableField(value = "`date`")
    private Date date;

    /**
     * 签到时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}