package com.cqx.feign;

import feign.FeignException;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/25
 */
public class NoHystrixException extends FeignException {
    protected NoHystrixException(String message, Throwable cause) {
        super(message, cause);
    }
}
