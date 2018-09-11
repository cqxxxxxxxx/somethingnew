package com.cqx.javawebbook.compile;


public class Cifa {

    public Runnable runnable;

    public Cifa() {
        Object o = new Object();
        runnable = () -> {
            System.out.println(o);
            String name = "cqx";
            System.out.println(name);
        };
        Comparable<Integer> comparable = new Comparable<Integer>() {
            @Override
            public int compareTo(Integer integer) {
                System.out.println(o);
                return 0;
            }
        };
    }

    public synchronized void test1() {
        int a = 1;
        Long b = 2l;
        System.out.println(a + b);
    }

    public void test2() {
        int a = 1;
        synchronized (this) {
            a ++;
        }
        System.out.println(a);
        return;
    }

    public void test3() {
        StaticInner staticInner = new StaticInner();
        staticInner.name = "Cqx";
        staticInner.getName();

        PublicInner publicInner = new PublicInner();
        publicInner.num = 1;
        publicInner.setNum(2);
    }

    public static void test4() {
        StaticInner staticInner = new StaticInner();
        staticInner.name = "Cqx";
        staticInner.getName();
        Cifa cifa = new Cifa();
        Cifa.PublicInner publicInner =  cifa.new PublicInner();
        publicInner.num = 1;
        publicInner.setNum(2);
    }



    public static class StaticInner {
        String name;

        Integer num;

        public String getName() {
            return name;
        }
    }


    public class PublicInner {

        public PublicInner() {
            System.out.println("public inner inited");
        }

        public String publicName;
        public Integer num;

        public void setNum(int num) {
            this.num = num;
        }
    }
}
