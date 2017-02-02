package com.cqx.model;

import com.cqx.annotation.Autowired;
import com.cqx.annotation.Bean;

/**
 * Created by Shan on 2017/1/19.
 */
@Bean
public class Person {
    private String name;
    private String phone;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
