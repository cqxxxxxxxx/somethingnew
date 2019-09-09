package com.cqx.uboost.annotation;

import java.lang.annotation.*;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/8
 */
@Target({
        ElementType.METHOD,
        ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UBoost {

    /**
     * 增强的advice
     *
     * @return
     */
    String value() default "";


    String[] values();

}
