package com.cqx.common;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BG307435 on 2018/5/15.
 */
public class AssertTest {


    /**
     * "-ea"或者"-enableassertions"
     *
     * @param args
     */
    public static void main(String[] args) {
        boolean assertionsEnabled = false;
        assert assertionsEnabled == true;
        System.out.println("Assertions enabled: " + assertionsEnabled);
    }

    @Test
    public void test1() {
//        boolean assertionsEnabled = false;
//        assert assertionsEnabled = true;
//        System.out.println("Assertions enabled: " + assertionsEnabled);
        Map<String, Object> a = new HashMap<>();
        Object o = a.get("aaa");
        System.out.println(o);
    }
}
