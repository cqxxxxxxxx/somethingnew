package com.cqx.plugin.basic.acc;

import com.cqx.plugin.basic.unacc.UnAccessible;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/16
 */
public class Accessible {

    public String sayHi(String msg) {
        System.out.println(new UnAccessible().pureMsg(msg));
        return msg;
    }
}
