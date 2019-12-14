package com.cqx.verify.fluent;

import com.cqx.verify.fluent.sure.ListSure;
import com.cqx.verify.fluent.sure.NumberSure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.cqx.verify.fluent.sure.ObjectSure.notNull;
import static com.cqx.verify.fluent.sure.StringSure.notEmpty;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/11/4
 */
public class Verifier {

    /**
     * Verifier.notNull(T t)
     * .bt(x)
     * .orThrow()
     */
    public static <T> VerifyChain<T> target(T t) {
        return new VerifyChain().target(t);
    }


    public static void main(String[] args) {
        Verifier.target("cqx")
                .sure(notEmpty())
                .orElse(System.out::print);

//        Verifier.target(" ")
//                .sure(notBlank())
//                .orElseThrow(new RuntimeException("ss"));

        List<String> strings = new ArrayList<>();
        Verifier.target(strings)
                .sure(notNull())
                .sure(ListSure.notEmpty())
                .orElseReturn(x -> Collections.EMPTY_LIST);


        Verifier.target(100)
                .sure(NumberSure.ge(100))
                .sure(NumberSure.lt(200))
                .orElse(System.out::println);

        Verifier.target(100L)
                .sure(NumberSure.gt(100L))
                .sure(NumberSure.lt(200L))
                .orElse(System.out::println);
    }
}
