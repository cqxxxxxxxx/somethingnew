package com.cqx.sort.mergeSort;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * fork-join 框架的归并排序
 * Created by BG307435 on 2017/9/29.
 */
public class Main {

    public static void main(String[] args) {

        Integer[] unsorted = new Integer[10000];
        Random random = new Random();
        int i = 0;
        while (i < unsorted.length) {
            int j = random.nextInt(unsorted.length * 10);
            unsorted[i++] = j;
        }
        MergeTask mergeTask = new MergeTask(unsorted, 0, 10000);
        ForkJoinPool pool = new ForkJoinPool(); //无参构造器生成个默认的ForkJoinPool对象
        pool.execute(mergeTask); //在线程池里执行该task

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
        } while (!mergeTask.isDone());

        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            Integer[] result = mergeTask.get();
            System.out.printf("Main: ", mergeTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
