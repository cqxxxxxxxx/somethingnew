package com.cqx.model;

import com.cqx.annotation.Bean;
import com.cqx.callback.Callback1;

/**
 * Created by Shan on 2017/1/20.
 */
@Bean
public class CallbackTest implements Callback1 {
    private String fuck;

    public String getFuck() {
        return fuck;
    }

    public void setFuck(String fuck) {
        this.fuck = fuck;
    }

    @Override
    public void setBeanName() {
        System.out.println("fuck off");
        setFuck("尼玛花");
    }
}
