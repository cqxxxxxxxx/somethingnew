package com.cqx.skeleton;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/10
 */
public class FoxException extends RuntimeException {
    public FoxException() {
    }

    public FoxException(String message) {
        super(message);
    }

    public FoxException(String message, Throwable cause) {
        super(message, cause);
    }

    public FoxException(Throwable cause) {
        super(cause);
    }

    public FoxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
