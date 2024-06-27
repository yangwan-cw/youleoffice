package com.ioomex.youleoffice.sys_user.service.impl;

import com.ioomex.youleoffice.sys_user.entity.param.FaceParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ioomex.youleoffice.sys_user.mapper.TbFaceModelMapper;
import com.ioomex.youleoffice.sys_user.entity.po.TbFaceModel;
import com.ioomex.youleoffice.sys_user.service.TbFaceModelService;

@Service
@RequiredArgsConstructor
public class TbFaceModelServiceImpl extends ServiceImpl<TbFaceModelMapper, TbFaceModel> implements TbFaceModelService {

    private final TbFaceModelMapper tbFaceModelMapper;

    @Override
    public String searchFaceByUserId(FaceParam faceParam) {
        String result = tbFaceModelMapper.searchFaceByUserId(faceParam);
        return result;
    }

    @Override
    public void insertFace(FaceParam faceParam) {
        tbFaceModelMapper.insertFace(faceParam);
    }

    @Override
    public void deleteFace(FaceParam faceParam) {
        tbFaceModelMapper.deleteFace(faceParam);
    }
}
