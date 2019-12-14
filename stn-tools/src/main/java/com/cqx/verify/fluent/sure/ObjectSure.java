package com.cqx.verify.fluent.sure;

import com.cqx.verify.fluent.ISure;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/11/4
 */
public class ObjectSure {

    public static <T extends Object> ISure<T> notNull() {
        return x -> x != null;
    }
}
