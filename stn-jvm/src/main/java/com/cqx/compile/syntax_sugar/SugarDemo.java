package com.cqx.compile.syntax_sugar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BG307435 on 2018/11/14.
 */
public class SugarDemo {

    public void genericSugar() {
        List<Integer> integerList = new ArrayList<>();
        for (Integer integer : integerList) {
            System.out.println(integer);
        }
    }

    public void intSugar() {
        Integer a = 1;
        Integer b = 233;
        Integer c = a + b;
    }

}
