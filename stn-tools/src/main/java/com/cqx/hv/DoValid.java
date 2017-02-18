package com.cqx.hv;

import java.lang.annotation.*;

/**
 * 标记在方法和方法参数上
 * 如果方法上没标记 则说明该方法不需要验证
 * Created by Shan on 2017/2/17.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DoValid {
    boolean required() default true;
}
