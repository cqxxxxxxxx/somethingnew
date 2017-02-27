package com.cqx.chapter6.concurrentLinkedDeque;

import com.cqx.chapter5.recursiveTask.DocumentMock;
import com.cqx.chapter5.recursiveTask.DocumentTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * {@link ConcurrentLinkedDeque} 非阻塞式并发数据列表
 * Created by cqx on 2017/2/20.
 */
public class Main {

    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
//        List list = new ArrayList();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            AddTask addTask = new AddTask(list);
            threads[i] = new Thread(addTask);
            threads[i].start();
        }

        //因为是非阻塞的 所有如果不等待任务跑完那获取的数据可能不对。所以需要join来等待所有任务跑完
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Main: Size of the List: %d\n", list.size());


        for (int i = 0; i < threads.length; i++) {
            PollTask pollTask = new PollTask(list);
            threads[i] = new Thread(pollTask);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Main: Size of the List: %d\n", list.size());
    }
}
