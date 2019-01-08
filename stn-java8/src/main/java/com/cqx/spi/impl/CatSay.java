package com.cqx.spi.impl;

import com.cqx.spi.SaySomething;

public class CatSay implements SaySomething {
    @Override
    public void saySth(String sth) {
        System.out.println("miaomiaomiao");
        System.out.println(sth);
    }
}
