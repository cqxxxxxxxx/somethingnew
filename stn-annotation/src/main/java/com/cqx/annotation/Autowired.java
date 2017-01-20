package com.cqx.annotation;

import java.lang.annotation.*;

/**
 * 类似spring autowired
 * 注解作用类型为 属性field
 * 注解保留的时期到 运行期
 * Created by Shan on 2017/1/19.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

    boolean required() default true;
}
