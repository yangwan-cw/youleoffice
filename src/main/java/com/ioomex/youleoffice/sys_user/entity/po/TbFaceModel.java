package com.ioomex.youleoffice.sys_user.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@Data
@TableName(value = "tb_face_model")
public class TbFaceModel implements Serializable {
    /**
     * 主键值
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 用户人脸模型
     */
    @TableField(value = "face_model")
    private String faceModel;

    private static final long serialVersionUID = 1L;
}