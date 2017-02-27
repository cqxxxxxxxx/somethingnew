package com.cqx.chapter6.linkedBlockingDeque;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by Shan on 2017/2/25.
 */
public class Client implements Runnable {

    private LinkedBlockingDeque<String> deque;

    public Client(LinkedBlockingDeque<String> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                sb.append(":");
                sb.append(j);

                try {
                    deque.put(sb.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Client: End.");
    }
}
