package com.cqx.common;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Java函数的参数是拷贝传递，只不过传递的是地址指针，按这个思路看看下面这个TEST
 * Created by Shan on 2017/2/10.
 */
public class Boom {

    class People {
        public String name;

        public People(String name) {
            this.name = name;
        }
    }

    @Test
    public void dateTest() {
        Date date = new Date();
        System.out.println(date.getTime());
        System.out.println(System.currentTimeMillis());
        Date date1 = new Date(date.getTime());
        System.out.println(date1.getYear());
        Date date2 = new Date(System.currentTimeMillis());
        System.out.println(date2.getYear());
    }

    @Test
    public void test() {
        int a = 10;
        method1(a);
        System.out.println(a);

        Integer b = 10;
        method2(b);
        System.out.println(b);

//        System.exit(0);       //退出

        String c = "c";
        method3(c);
        System.out.println(c);

        People people = new People("cqx");
        method4(people);
        System.out.println(people.name);


    }

    public void method1(int a) {
        a = 100;
    }

    public void method2(Integer b) {
        b = 100;
    }

    public void method3(String c) {
        c = "ccccc";
    }

    public void method4(People people) {
        people.name = "cqxnmb";
    }

    @Test
    public void kongge() {
        char var1 = '\u0000';   //0
        char var2 = '\u0020';   //空格
        String var3 = "01   ";
        StringBuilder sb = new StringBuilder(var1).append(var2);
        String var4 = String.valueOf(var1) + String.valueOf(var2);
        System.out.println("var1:" + var1);
        System.out.println("var2:" + var2);
        System.out.println("var3:" + var3);
        System.out.println("var4:" + var4);
        System.out.println(var1 == ' ');
        System.out.println(var2 == ' ');
        System.out.println(var1 == 0);
    }


    @Test(expected = NullPointerException.class)
    public void testFor() {
        List<String> strings = null;
        for (String s : strings) {
            System.out.println(s);
        }
    }

    @Test
    public void arrayCopy() {
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {5, 6, 7, 8};
        System.arraycopy(a1, 1, a2, 1, 2);
        System.out.println(1);
    }

    @Test
    public void calculate() {
        System.out.println(5/3);
        System.out.println(5%3);
    }

    @Test
    public void split() {
        String phoneStr = "13910394576\n" +
                "15251766970\n" +
                "17706535333\n";
        String[] phones = phoneStr.split("\n");
        System.out.println(phones.length);
        List<String> badDatas = Arrays.stream(phones).filter(x -> x.length() != 11).map(String::trim).collect(Collectors.toList());
        List<String> goodDatas = Arrays.stream(phones).filter(x -> x.length() == 11).map(String::trim).collect(Collectors.toList());
        String result = goodDatas.stream().map(x -> "\"" + x + "\"").collect(Collectors.joining(","));
        System.out.println(result);

        //查找重复出现的
        List<String> aaa = goodDatas.stream().filter(x ->
            goodDatas.stream().filter(s -> s.equals(x)).count() > 1).collect(Collectors.toList());
        System.out.println(aaa);
    }


    @Test
    public void test111() {
        String a = "Yuan Xingjun <xjyuan@best-inc.com>; Xing Cheng <xingcheng@best-inc.com>; Guo Lulu <guolulu@best-inc.com>; Wang Enren <erwang@best-inc.com>; Han Qunyi <qyhan@best-inc.com>; Xie Gui <xiegui@best-inc.com>; Li Sen(HZ) <lisen.hz@best-inc.com>; Wu Haibao <hbwu@best-inc.com>; Luo Xinyue <luoxy.hr@best-inc.com>; Liu Ling <liuling@best-inc.com>; Liang Jiawei <jwliang94@best-inc.com>; Qian Yifan <yfqian.hz@best-inc.com>; Wang Yuting <BG275579@best-inc.com>; Feng Caiyun <cyfeng@best-inc.com>; Kong Xiangling <xlkong@best-inc.com>; Li Changxin <licx@best-inc.com>; Yang Jiaqi <yjq@best-inc.com>; Yuan Qianyun <qyyuan@best-inc.com>; Zhao Junmei <jmzhao@best-inc.com>; Huang Chan <chuang45@best-inc.com>; Liu Mengjiao <mjliu@best-inc.com>; Wang Meng <mwang81@best-inc.com>; Cui Hui <hcui46@best-inc.com>; Xu Lingjie <ljxu@best-inc.com>; Jin Yi <yjin@best-inc.com>; Yang Jinlian <jlyang.hz@best-inc.com>; Sun Jinping <jpsun@best-inc.com>; Mao Zhaoxin <zxmao@best-inc.com>; Zhang Lianming <zhanglm@best-inc.com>; Zhang guiyuan <gyzhang@best-inc.com>; Huang Xiaoqing <xqhuang.rd@best-inc.com>; Zhu Huaping <hpzhu@best-inc.com>; Wang Lixuan <lxwang.wlx@best-inc.com>; Yang Jing(RD) <jyang.hz@best-inc.com>; Chen Jixing <jxchen35@best-inc.com>";
        System.out.println(a.split(";").length);
    }

    @Test
    public void testInstanceOf() {
//        BigDecimal bigDecimal = null;
//        System.out.println(bigDecimal.toString());
//        long a = 0L;
//        Object b = a;
//        System.out.println(b instanceof Long);
//        int c = 0;
//        Object d = c;
//        System.out.println(d instanceof Integer);
//        Long.parseLong("0");
        Long l1 = Long.valueOf(0);
        System.out.println(l1.longValue() == 0L);
    }
}
