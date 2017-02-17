package com.cqx.hv;

import java.lang.annotation.*;

/**
 * 标记在方法参数上
 * 运行期保留
 * Created by Shan on 2017/2/17.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DoValid {
    boolean required() default true;
}
