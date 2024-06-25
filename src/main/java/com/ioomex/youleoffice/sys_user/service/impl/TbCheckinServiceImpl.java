package com.ioomex.youleoffice.sys_user.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.ioomex.youleoffice.common.SystemConstant;
import com.ioomex.youleoffice.sys_user.mapper.TbHolidaysMapper;
import com.ioomex.youleoffice.sys_user.mapper.TbWorkdayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ioomex.youleoffice.sys_user.entity.po.TbCheckin;
import com.ioomex.youleoffice.sys_user.mapper.TbCheckinMapper;
import com.ioomex.youleoffice.sys_user.service.TbCheckinService;

import java.util.HashMap;

@Service
public class TbCheckinServiceImpl extends ServiceImpl<TbCheckinMapper, TbCheckin> implements TbCheckinService {

    @Autowired
    private TbHolidaysMapper holidaysMapper;

    @Autowired
    private TbWorkdayMapper workdayMapper;

    @Autowired
    private TbCheckinMapper tbCheckinMapper;

    @Autowired
    private SystemConstant systemConstant;


    @Override
    public String validCanCheckIn(int userId, String date) {
        boolean todayIsHolidaysResult = holidaysMapper.searchTodayIsHolidays() != null;
        boolean todayIsWorkdayResult = workdayMapper.searchTodayIsWorkday() != null;
        String type = "工作日";
        if (DateUtil.date().isWeekend()) {
            type = "节假日";
        }
        if (todayIsHolidaysResult) {
            type = "节假日";
        } else if (todayIsWorkdayResult) {
            type = "工作日";
        }

        if ("节假日".equals(type)) {
            return "节假日不需要考勤";
        } else {
            DateTime now = DateUtil.date();
            String start = DateUtil.today() + " " + systemConstant.attendanceStartTime;
            String end = DateUtil.today() + " " + systemConstant.attendanceEndTime;
            DateTime attendanceStart = DateUtil.parse(start);
            DateTime attendanceEnd = DateUtil.parse(end);
            if (now.isBefore(attendanceStart)) {
                return "没到上班考勤开始时间";
            } else if (now.isAfter(attendanceEnd)) {
                return "超过了上班考勤结束时间";
            } else {
                HashMap<String, Object> map = new HashMap();
                map.put("userId", userId);
                map.put("date", date);
                map.put("start", start);
                map.put("end", end);
                boolean bool = tbCheckinMapper.haveCheckin(map) != null;
                return bool ? "今日已经考勤，不用重复考勤" : "可以考勤";
            }
        }
    }

}
