package com.cqx.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * 可以看看 {@link java.util.function}
 * 比较好的文章 http://www.cnblogs.com/figure9/archive/2014/10/24/4048421.html
 * Created by Administrator on 2017/2/2.
 */
public class LambdaDemo {

    /**
     * runnable的lambda实现与普通实现的比较
     */
    @Test
    public void runnableTest(){

        //不用lambda 按以前的匿名内部类实现
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("without using lambda");
            }
        }).start();

        //lambda 实现1
        Runnable runnable = () -> System.out.println("Runnable Lambda1");
        new Thread(runnable).start();

        //lambda 实现2
        new Thread(() -> System.out.println("Runnable Lambda2")).start();
    }

    /**
     * 排序的lambda实现与普通实现的比较
     */
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

    /**
     * lambda方法引用  用于简化lambda表达式的写法
     * 1.objectName::instanceMethod
     * 2.ClassName::staticMethod
     * 3.ClassName::instanceMethod
     *
     * 1. 2.两种类似 等同于把lambda表达式的参数直接当成instanceMethod|staticMethod的参数来调用
     * System.out::println等同于x->System.out.println(x)  out对象的println方法
     * Math::max等同于(x, y)->Math.max(x,y) Math类的静态方法max
     *
     * 3.
     */
    @Test
    public void methodReferenceTest(){
        String[] args = {"a", "b", "c", "1"};
        Arrays.stream(args).forEach(System.out::println);

        //// TODO: 2017/2/2 add more examples... 
    }

}
