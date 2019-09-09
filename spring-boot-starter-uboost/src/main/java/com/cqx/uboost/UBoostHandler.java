package com.cqx.uboost;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/9
 */
public interface UBoostHandler {

    default Object[] boostBefore(Object[] args) {
        return args;
    }

    ;

    default Object boostAfter(Object result, Object[] args) {
        return result;
    }

    ;

    default <T extends Throwable> T boostOnException(T throwable, Object[] args) {
        return throwable;
    }

}
