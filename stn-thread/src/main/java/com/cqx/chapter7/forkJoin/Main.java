package com.cqx.chapter7.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * {@link PriorityBlockingQueue} 阻塞式线程安全列表 根据添加元素的comparable接口的实现来决定插入元素的位置
 * Created by cqx on 2017/2/20.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        MyWokerThreadFactory factory = new MyWokerThreadFactory();
        //设置最多同步4个线程， 工厂对象， 异常处理器， 是否异步
        ForkJoinPool forkJoinPool = new ForkJoinPool(4, factory, null, false);

        int[] array = new int[100000];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }

        MyRecursiveTask task = new MyRecursiveTask(array, 0, array.length);
        forkJoinPool.execute(task);
        task.join();    //join 让main线程等待任务的完成，然后继续执行
        forkJoinPool.shutdown();        //关闭pool对象
        forkJoinPool.awaitTermination(1, TimeUnit.DAYS);    //等待执行器的结束
        System.out.printf("Main: Result: %d\n", task.get());
        System.out.printf("Main: End of the program\n");
    }
}
