package com.cqx.model;

import com.cqx.annotation.Autowired;
import com.cqx.annotation.Bean;

/**
 * Created by Shan on 2017/1/20.
 */
@Bean
public class Room {
    @Autowired
    private Person person;

    public void whoisin(){
        System.out.println(person.getName() + "saasdf");
    }
}
