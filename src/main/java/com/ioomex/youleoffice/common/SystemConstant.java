package com.ioomex.youleoffice.common;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * SystemConstant
 *
 * @author sutton
 * @since 2024-06-12 20:48
 */
@Data
@Component
public class SystemConstant {
    public String attendanceStartTime;
    public String attendanceTime;
    public String attendanceEndTime;
    public String closingStartTime;
    public String closingTime;
    public String closingEndTime;
}