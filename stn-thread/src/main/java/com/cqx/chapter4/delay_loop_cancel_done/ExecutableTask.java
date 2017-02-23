package com.cqx.chapter4.delay_loop_cancel_done;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by cqx on 2017/2/23.
 */
public class ExecutableTask implements Callable<String> {

    private String name;
    public String getName(){
        return name;
    }

    public ExecutableTask(String name){
        this.name = name;
    }

    @Override
    public String call() throws Exception {

        long duration = (long) (Math.random()*10);
        System.out.printf("%s: Waiting %d seconds for results.\n", this.name, duration);
        TimeUnit.SECONDS.sleep(duration);
        return "Hello, world. I'm" + name;
    }
}
