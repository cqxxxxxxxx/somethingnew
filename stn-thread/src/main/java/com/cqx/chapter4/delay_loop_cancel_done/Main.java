package com.cqx.chapter4.delay_loop_cancel_done;

import com.cqx.chapter4.Server;
import com.cqx.chapter4.Task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by cqx on 2017/2/20.
 */
public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ResultTask[] resultTasks = new ResultTask[5];
        for (int i = 0; i < 5; i++) {
            ExecutableTask executableTask = new ExecutableTask("Task" + i);
            resultTasks[i] = new ResultTask(executableTask);
            executorService.submit(resultTasks[i]);
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < resultTasks.length; i++) {
            resultTasks[i].cancel(true);
        }
        for (int i = 0; i < resultTasks.length; i++) {
            if (!resultTasks[i].isCancelled()){
                try {
                    System.out.printf("%s\n", resultTasks[i].get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        executorService.shutdown();
    }
}
