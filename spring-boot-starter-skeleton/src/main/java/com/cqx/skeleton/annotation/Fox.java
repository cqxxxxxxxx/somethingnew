package com.cqx.skeleton.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/9
 */
@Target({
        ElementType.METHOD,
        ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Fox {

    /**
     * meaning less
     *
     * @return
     */
    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";

    /**
     * 针对每个接口的配置类
     * 会覆盖掉全局的统一配置
     * 类似feign中的做法
     *
     * @return
     */
    Class<?> configuration() default void.class;
}
