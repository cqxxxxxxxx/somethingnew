package com.cqx.gen;

import java.lang.annotation.*;

/**
 * Created by BG307435 on 2017/9/21.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ColumnSort {

    int value();

    String des() default "";
}
