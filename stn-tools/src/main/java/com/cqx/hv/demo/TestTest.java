package com.cqx.hv.demo;

import com.cqx.hv.DoValid;
import org.junit.Test;
import org.springframework.stereotype.Component;

/**
 * Created by Shan on 2017/2/17.
 */
@Component
public class TestTest {

    @Test
    public void doTest(){
        Person person = new Person();
        person.setName("");
        person.setAge(3);
        person.setPhone("adsfdfasd");
        person.setEmail("saf@qq.com");

        aaa(person);
    }



    @DoValid
    public void aaa(@DoValid Person person){
        System.out.println(person.toString());
    }

}
