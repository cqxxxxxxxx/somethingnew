package com.cqx.uboost;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/9
 */
public class UBoostException extends RuntimeException {

    public UBoostException(String msg) {
        super(msg);
    }

    public UBoostException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UBoostException(Throwable cause) {
        super(cause);
    }
}
