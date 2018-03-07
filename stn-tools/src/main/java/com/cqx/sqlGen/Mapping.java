package com.cqx.sqlGen;

import java.lang.annotation.*;

/**
 * Created by BG307435 on 2017/9/21.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Mapping {

    /**
     * 对应的excel中列的序号
     *
     * @return
     */
    int index();

    int SQLIndex() default 0;

    String des() default "";

}
