package com.ioomex.youleoffice.common;

import cn.hutool.core.util.StrUtil;
import com.ioomex.youleoffice.sys_user.entity.SysConfig;
import com.ioomex.youleoffice.sys_user.mapper.SysConfigMapper;
import com.ioomex.youleoffice.sys_user.mapper.TbHolidaysMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.List;

@Component
@Slf4j
public class ApplicationInitializer {

    @Autowired
    private SysConfigMapper sysConfigMapper;


    @Autowired
    private SystemConstant systemConstant;


    @Autowired
    private TbHolidaysMapper tbHolidaysMapper;

    /**
     * 初始化打卡常量时间
     */
    @PostConstruct
    public void initConstant() {
        List<SysConfig> result = sysConfigMapper.selectAllData();
        log.info("result {}", result);
        result.forEach(config -> {
            String key = config.getParamKey();
            key = StrUtil.toCamelCase(key);
            String value = config.getParamValue();
            log.info("value {}", value);
            try {
                Field field = systemConstant.getClass().getDeclaredField(key);
                field.setAccessible(true);
                field.set(systemConstant, value);
            } catch (Exception e) {
                log.error("执行异常", e);
            }
        });
    }


}
