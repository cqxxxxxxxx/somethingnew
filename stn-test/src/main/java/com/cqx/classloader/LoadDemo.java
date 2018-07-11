package com.cqx.classloader;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by BG307435 on 2017/12/7.
 */
public class LoadDemo {

    private ClassLoader classLoader = LoadDemo.class.getClassLoader();

    public void printClassLoader() {
        System.out.println(this.getClass().getClassLoader());
    }

    @Test
    public void print() {
        System.out.println(System.getProperty("java.ext.dirs"));
    }

    @Test
    public void loadFromByteArray() throws IOException {
        String path = "com/cqx/AppTest.class";
        InputStream inputStream = classLoader.getResourceAsStream(path);
        byte[] bytes = new byte[1024];
        inputStream.read(bytes);
        System.out.println(bytes.length);
    }

    @Test
    public void classNotFoundTest() throws ClassNotFoundException {
        String classpath = classLoader.getResource("").toString();
        System.out.println("classpath:" + classpath);
        classLoader.loadClass("notfound");
    }
}
