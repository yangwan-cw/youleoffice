package com.ioomex.youleoffice.sys_user.controller;
import com.ioomex.youleoffice.sys_user.entity.po.TbMeeting;
import com.ioomex.youleoffice.sys_user.service.TbMeetingService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 会议表(tb_meeting)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/tb_meeting")
public class TbMeetingController {
/**
* 服务对象
*/
    @Autowired
    private TbMeetingService tbMeetingService;

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
    @GetMapping("selectOne")
    public TbMeeting selectOne(Integer id) {
    return tbMeetingService.getById(id);
    }

}
