package com.cqx.spi;

import java.util.Iterator;
import java.util.Objects;
import java.util.ServiceLoader;

public class Main {

    public static void main(String[] args) {
        /**
         *
         //         *  这里写的有问题。 里面代码很简单
         //         * 1. serviceLoader根据内部写死的PREFIX：META-INF/services/ 目录下找SPI的文件
         //         * 2. 通过serviceLoader内部的iterator进行迭代时(通过一个内部类来实现这个接口的) 才进行实现类的初始化
         //         * 2. 是通过
         //         * 2. 一行行读取文件里的内容，即接口具体实现的全限定名(full qualified name)
         //         * 3. 然后用类加载器根据全限定名去加载之，然后返回
         * 这个流程细节可能有问题，具体看代码
         */
        CqxClassLoader cqxClassLoader = new CqxClassLoader();
        Thread.currentThread().setContextClassLoader(cqxClassLoader);
        System.out.println(Main.class.getClassLoader().toString());
        ServiceLoader<SaySomething> serviceLoader = ServiceLoader.load(SaySomething.class);
        Iterator<SaySomething> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            SaySomething next = iterator.next();
            next.saySth("wawawa");
        }


        Objects.requireNonNull(serviceLoader, "null 就跑出空指针异常并打印这个信息");
    }
}
