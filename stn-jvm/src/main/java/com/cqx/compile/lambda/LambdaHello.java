package com.cqx.compile.lambda;

/**
 * Created by BG307435 on 2018/11/12.
 */
public class LambdaHello {

    public void sayHi() {
        String bcc = "dsafdasf";
        Runnable r = () -> {
            System.out.println("1123");
            System.out.println(bcc);
        };
        System.out.println(r.toString());
    }
}
