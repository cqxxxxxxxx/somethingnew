package com.cqx.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by BG307435 on 2017/8/7.
 */
@Component
public class DemoComponent {

    public DemoComponent(){
        String date = new Date().toString();
        System.out.println("init" + date);
    }


    private DemoAA demoAA;

    private String name;

    private String des;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public DemoAA getDemoAA() {
        return demoAA;
    }

    @Autowired
    public void setDemoAA(DemoAA demoAA) {
        String date = new Date().toString();
        System.out.println("@autowiring " + date);
        this.demoAA = demoAA;
    }
}
