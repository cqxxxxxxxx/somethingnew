package com.cqx.jagent.examples;

import java.util.concurrent.TimeUnit;

public class   AttachTest {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            TimeUnit.SECONDS.sleep(10);
            Person person = new Person();
            person.sayHi();
        }
    }
}
