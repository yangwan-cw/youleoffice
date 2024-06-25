package com.ioomex.youleoffice.sys_user.service;

import com.ioomex.youleoffice.sys_user.entity.po.TbCheckin;
import com.baomidou.mybatisplus.extension.service.IService;
public interface TbCheckinService extends IService<TbCheckin>{


    String validCanCheckIn(int userId, String today);
}
