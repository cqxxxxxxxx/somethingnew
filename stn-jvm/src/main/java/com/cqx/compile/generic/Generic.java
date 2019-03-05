package com.cqx.compile.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/18
 */
public class Generic {

    private Map<String, String> map = new HashMap<>();

    public static void main(String[] args) {
        List<Generic> generics = new ArrayList<>();
        generics.add(new Generic());
        Generic generic = generics.get(1);
        System.out.println(generics.getClass().getGenericSuperclass());
    }
}
