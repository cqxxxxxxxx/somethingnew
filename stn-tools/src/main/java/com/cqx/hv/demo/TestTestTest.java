package com.cqx.hv.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Shan on 2017/2/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTestTest {

    @Autowired
    TestTest testTest;

    @Test
    public void doTest(){
        Person person = new Person();
        person.setName("");
        person.setAge(3);
        person.setPhone("adsfdfasd");
        person.setEmail("saf@qq.com");

        testTest.aaa(person);
    }
}
