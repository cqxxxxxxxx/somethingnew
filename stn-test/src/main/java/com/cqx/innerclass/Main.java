package com.cqx.innerclass;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BG307435 on 2017/11/17.
 */
public class Main {



    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.values();

        Outer1.Inner1 inner1 = new Outer1.Inner1();
        inner1.cqx();
        Outer1.Inner1.say();

        //如果吧Inner2设为private 这边就编译报错了
        Outer2.Inner2 inner2 = new Outer2().getInner2();


    }

}
