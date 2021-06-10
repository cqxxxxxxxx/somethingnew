package com.cqx.stnspringboot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

    public static void main(String[] args) {

        List<StringBuilder> list = new ArrayList() {{
            System.out.println("true");
            System.out.println("false");
        }};
        StringBuilder sb = new StringBuilder();

        Set<StringBuilder> set = new HashSet<>(list);
        set.add(sb);
        System.out.println(set.contains(sb));   // should print true
        sb.append("oops");
        System.out.println(set.contains(sb));   // should print false
    }
}
