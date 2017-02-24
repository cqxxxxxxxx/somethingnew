package com.cqx.chapter4.dealFirstOrAll;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by cqx on 2017/2/20.
 */
public class Main {

    public static void main(String[] args) {
        String name = "test";
        String password = "test";

        UserValidator validator1 = new UserValidator("V1");
        UserValidator validator2 = new UserValidator("V2");

        TaskValidator task1 = new TaskValidator(validator1, name, password);
        TaskValidator task2 = new TaskValidator(validator2, name, password);

        List<TaskValidator> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        ExecutorService executor = Executors.newCachedThreadPool();

//        调用invokeAny()  获取返回的第一个完成的任务
//        String result;
//        try {
//            //接收任务列表并运行，返回第一个完成任务且没抛出异常的执行结果。
//            result = executor.invokeAny(taskList);
//            System.out.printf("Main: Result: %s.\n", result);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        executor.shutdown();
//        System.out.println("Main: End of the Execution.");



//        调用invokeAll() 获取所有的执行结果
        List<Future<String>> results = null;
        try {
            results = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.println("Main: Printing the results");
        for (int i = 0; i < results.size(); i++) {
            Future<String> future = results.get(i);
            try {
                String str = future.get();
                System.out.println(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }



    }
}
