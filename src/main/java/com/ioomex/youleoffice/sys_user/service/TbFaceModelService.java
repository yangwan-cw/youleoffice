package com.ioomex.youleoffice.sys_user.service;

import com.ioomex.youleoffice.sys_user.entity.param.FaceParam;
import com.ioomex.youleoffice.sys_user.entity.po.TbFaceModel;
import com.baomidou.mybatisplus.extension.service.IService;
public interface TbFaceModelService extends IService<TbFaceModel>{


    String searchFaceByUserId(FaceParam faceParam);

    void insertFace(FaceParam faceParam);

    void deleteFace(FaceParam faceParam);
}
