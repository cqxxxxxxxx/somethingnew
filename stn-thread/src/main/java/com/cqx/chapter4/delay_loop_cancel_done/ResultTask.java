package com.cqx.chapter4.delay_loop_cancel_done;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by cqx on 2017/2/23.
 */
public class ResultTask extends FutureTask<String> {

    private String name;

    public ResultTask(Callable<String> callable) {
        super(callable);
        this.name = ((ExecutableTask) callable).getName();

    }

    @Override
    protected void done() {
        if (isCancelled()) {
            System.out.printf("%s: Has been canceled.\n", name);
        } else {
            System.out.printf("%s: Has finished.\n", name);
        }
    }
}
