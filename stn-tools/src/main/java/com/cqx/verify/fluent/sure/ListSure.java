package com.cqx.verify.fluent.sure;

import com.cqx.verify.fluent.ISure;

import java.util.List;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/11/4
 */
public class ListSure {

    public static <E> ISure<List<E>> notEmpty() {
        return x -> x != null && !x.isEmpty();
    }

//    public static <E> boolean notEmpty(List<E> list) {
//        return list != null && !list.isEmpty();
//    }
}
