package com.cqx.compile.stackslot;

import java.util.ArrayList;
import java.util.List;

public class SlotTest {

    public void test0() {
        List a = new ArrayList();
        List b = new ArrayList();
        List c = new ArrayList();
        List d = new ArrayList();
        System.out.println(a.size());
        System.out.println(b.size());
        System.out.println(c.size());
        System.out.println(d.size());
    }

    public void test1() {
        List a = new ArrayList();
        System.out.println(a.size());

        List b = new ArrayList();
        System.out.println(b.size());

        List c = new ArrayList();
        System.out.println(c.size());

        List d = new ArrayList();
        System.out.println(d.size());
    }
}
