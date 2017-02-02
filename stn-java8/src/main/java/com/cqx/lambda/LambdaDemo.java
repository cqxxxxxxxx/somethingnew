package com.cqx.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/2/2.
 */
public class LambdaDemo {

    @Test
    public void runnableTest(){

        //不用lambda 按以前的匿名内部类实现
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("without using lambda");
            }
        }).start();

        //lambda 实现
        new Thread(() -> System.out.println("master")).start();
    }

    @Test
    public void sortTest(){
        String[] datas0 = {"chen", "qi", "xing", "534"};
        String[] datas1 = {"chen", "qi", "xing", "534"};
        String[] datas2 = {"chen", "qi", "xing", "534"};
        //安默认的字典排序
        Arrays.sort(datas0);
        Stream.of(datas0).forEach(data -> System.out.println(data));

        //不使用lambda，通过 {@link Comparable}接口 安字符串长度排序
        Arrays.sort(datas1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length())
                    return 1;
                else if (o1.length() == o2.length())
                    return 0;
                else
                    return -1;
            }
        });
        Stream.of(datas1).forEach(data -> System.out.println(data));

        //使用lambda 安字符串长度排序
        Arrays.sort(datas2, (v1, v2) -> Integer.compare(v1.length(), v2.length()));
        Stream.of(datas2).forEach(data -> System.out.println(data));

    }

}
