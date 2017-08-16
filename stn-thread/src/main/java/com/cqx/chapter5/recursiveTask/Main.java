package com.cqx.chapter5.recursiveTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * {@link java.util.concurrent.RecursiveTask} 有返回值
 * Created by cqx on 2017/2/20.
 */
public class Main {

    public static void main(String[] args) {

        DocumentMock mock = new DocumentMock();
        String[][] document = mock.generateDocument(100, 100, "the");  //生成个100行 一行1000个单词的文档 目标单词是the
        DocumentTask documentTask = new DocumentTask(document, 0, 100, "the");
        ForkJoinPool pool = new ForkJoinPool(); //无参构造器生成个默认的ForkJoinPool对象
        pool.execute(documentTask); //在线程池里执行该task

        //不断的循环显示线程池的任务处理的进展信息
        do {
            System.out.println("****************************************************************");
            System.out.printf("Main: Parallelism: %d.\n", pool.getParallelism());   //并行的线程数量????
            System.out.printf("Main: Active Thread: %d.\n", pool.getActiveThreadCount());   //活跃的任务数量??
            System.out.printf("Main: Task Count: %d.\n", pool.getQueuedTaskCount());    //等待的任务数量
            System.out.printf("Main: Steal Count: %d.\n", pool.getStealCount());
            System.out.println("****************************************************************");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!documentTask.isDone());

        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.printf("Main: The word appears %d in the document", documentTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
