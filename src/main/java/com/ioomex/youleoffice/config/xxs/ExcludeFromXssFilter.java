package com.ioomex.youleoffice.config.xxs;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcludeFromXssFilter {
}
