package com.cqx.unacc;

import java.util.Optional;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/16
 */
public class UnAccessible {

    public String pureMsg(String msg) {
        return Optional.ofNullable(msg).orElse("").replace("dog", "god!!!");
    }
}
