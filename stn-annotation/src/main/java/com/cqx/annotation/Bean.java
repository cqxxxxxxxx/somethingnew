package com.cqx.annotation;

import java.lang.annotation.*;

/**
 * 类似spring component
 * Created by Shan on 2017/1/19.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bean {
    String name() default "";
}
