package com.cqx.spi.impl;

import com.cqx.spi.SaySomething;

public class DogSay implements SaySomething {
    @Override
    public void saySth(String sth) {
        System.out.println("wuwuwuuuu");
        System.out.println(sth);
    }
}
