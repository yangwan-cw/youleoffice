package com.ioomex.youleoffice.sys_user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ioomex.youleoffice.sys_user.entity.param.FaceParam;
import com.ioomex.youleoffice.sys_user.entity.po.TbFaceModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbFaceModelMapper extends BaseMapper<TbFaceModel> {

    String searchFaceByUserId(FaceParam faceParam);

    void insertFace(FaceParam faceParam);

    void deleteFace(FaceParam faceParam);
}