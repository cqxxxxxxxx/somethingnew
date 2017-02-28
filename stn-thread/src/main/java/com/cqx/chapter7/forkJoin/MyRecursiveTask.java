package com.cqx.chapter7.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by Shan on 2017/2/27.
 */
public class MyRecursiveTask extends RecursiveTask<Integer> {

    private int[] array;
    private int start, end;

    public MyRecursiveTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        Integer ret = 0;
        MyWorkThread myWorkThread = (MyWorkThread) Thread.currentThread();
        myWorkThread.addTask();     //吧当前线程的ThreadLocal变量counter+1

        if (end - start < 100){
            ret = sumArray(array, start, end);
        }else {
            int mid = (start + end) / 2;
            MyRecursiveTask task1 = new MyRecursiveTask(array, start, mid);
            MyRecursiveTask task2 = new MyRecursiveTask(array, mid, end);
            invokeAll(task1, task2);

            try {
                ret = task1.get() + task2.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    private Integer addResult(MyRecursiveTask task1, MyRecursiveTask task2) {
        int value = 0;
        try {
            value = task1.get().intValue() + task2.get().intValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 计算数组指定下标内的元素之和
     * @param array
     * @param start
     * @param end
     * @return
     */
    private Integer sumArray(int[] array, int start, int end){
        Integer sum = 0;
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
        return sum;
    }
}
