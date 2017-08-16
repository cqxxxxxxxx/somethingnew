package com.cqx.chapter6.linkedBlockingDeque;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * {@link LinkedBlockingDeque} 阻塞式并发数据列表
 * Created by cqx on 2017/2/20.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        //双端队列大小为3
        //如果addTask线程插入的时候列表已满，则阻塞直到有空位就继续插入
        //如果执行take()的时候队列为空，则阻塞直到里面有东西。
        LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>(3);

        Client client = new Client(deque);
        Thread thread = new Thread(client);
        thread.start();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                String str = deque.take();
                System.out.printf("Main: Str: %s at %s. Size: %d\n", str, new Date(), deque.size());
            }
            TimeUnit.MILLISECONDS.sleep(300);   //休眠300毫秒
        }

        System.out.printf("Main: End of the program.");
    }
}
