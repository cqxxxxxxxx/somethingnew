package com.cqx.compile.foreach;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/17
 */
public class ForEachDemo {

    public void foreach0() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        for (String s : list) {
            System.out.println(s);
            list.remove(s);
        }
    }

    public static void main(String[] args) {
        ForEachDemo forEachDemo = new ForEachDemo();
        forEachDemo.foreach0();
    }
}
