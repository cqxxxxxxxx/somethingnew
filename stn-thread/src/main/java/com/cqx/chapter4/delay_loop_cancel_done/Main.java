package com.cqx.chapter4.delay_loop_cancel_done;

import java.util.concurrent.*;

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
//            Future s = executorService.submit(resultTasks[i]);
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
            if (!resultTasks[i].isCancelled()) {
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
