package com.cqx.common;

import org.junit.Test;

/**
 * Created by BG307435 on 2018/5/15.
 */
public class AssertTest {


    public static void main(String[] args) {
        boolean assertionsEnabled = false;
        assert assertionsEnabled = true;
        System.out.println("Assertions enabled: " + assertionsEnabled);
    }

    @Test
    public void test1() {
        boolean assertionsEnabled = false;
        assert assertionsEnabled = true;
        System.out.println("Assertions enabled: " + assertionsEnabled);
    }
}
