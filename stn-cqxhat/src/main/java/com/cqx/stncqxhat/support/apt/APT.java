package com.cqx.stncqxhat.support.apt;

import java.lang.annotation.*;

/**
 * 标识可以通过注解处理器处理
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/20
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.ANNOTATION_TYPE,
        ElementType.TYPE
})
public @interface APT {
}
