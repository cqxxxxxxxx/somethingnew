package com.cqx.chapter4;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Shan on 2017/2/22.
 */
public class Server {

    private ThreadPoolExecutor poolExecutor;

    public Server(){
        poolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    public void executeTask(Task task){
        System.out.println("Server: A new task has arrived");
        poolExecutor.execute(task); //发送任务给执行器
        System.out.printf("Server: Pool size: %d\n", poolExecutor.getPoolSize());   //获取线程池数量
        System.out.printf("Server: Active count: %d\n", poolExecutor.getActiveCount());   //获取线程池中正在使用的线程数量
        System.out.printf("Server: Completed Tasks: %d\n", poolExecutor.getCompletedTaskCount());   //获取经过线程池执行完成的任务数量
    }

    /**
     * shutdownNow() 立即关闭执行器，不再执行在等待中的任务，已经开始的就不管他们
     */
    public void endServer(){
        poolExecutor.shutdown();

//        List<Runnable> runnables = poolExecutor.shutdownNow();
//        runnables.stream().forEach(runnable -> System.out.println(runnable.toString()));
    }


}
