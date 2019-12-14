package com.cqx.verify.fluent.sure;

import com.cqx.verify.fluent.ISure;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/11/4
 */
public class NumberSure {

//====================  Long ==========================

    public static ISure<Long> gt(long target) {
        return x -> x > target;
    }

    public static ISure<Long> ge(long target) {
        return x -> x >= target;
    }

    public static ISure<Long> lt(long target) {
        return x -> x < target;
    }

    public static ISure<Long> le(long target) {
        return x -> x <= target;
    }

    public static ISure<Long> eq(long target) {
        return x -> x == target;
    }


//====================  Integer ==========================

    public static ISure<Integer> gt(int target) {
        return x -> x > target;
    }

    public static ISure<Integer> ge(int target) {
        return x -> x >= target;
    }

    public static ISure<Integer> lt(int target) {
        return x -> x < target;
    }

    public static ISure<Integer> le(int target) {
        return x -> x <= target;
    }

    public static ISure<Integer> eq(int target) {
        return x -> x == target;
    }


}
