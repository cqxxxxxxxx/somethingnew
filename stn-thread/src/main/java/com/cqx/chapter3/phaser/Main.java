package com.cqx.chapter3.phaser;

import com.cqx.chapter3.cyclicBarrier.Grouper;
import com.cqx.chapter3.cyclicBarrier.MatrixMock;
import com.cqx.chapter3.cyclicBarrier.Results;
import com.cqx.chapter3.cyclicBarrier.Searcher;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;

/**
 *
 * Created by cqx on 2017/2/20.
 */
public class Main {

    public static void main(String[] args){
        Phaser phaser = new Phaser(3);  //参与同步的线程3个
        FileSearch system = new FileSearch("C:\\Windows", "log", phaser);
        FileSearch apps = new FileSearch("C:\\Program Files", "log", phaser);
        FileSearch documents = new FileSearch("C:\\Users", "log", phaser);

        Thread systemThread = new Thread(system, "System");
        Thread appsThread = new Thread(apps, "Apps");
        Thread documentsThread = new Thread(documents, "Documents");

        systemThread.start();
        appsThread.start();
        documentsThread.start();

        try {
            //等待三个线程执行结束
            systemThread.join();
            appsThread.join();
            documentsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Terminated: "+ phaser.isTerminated());
    }
}
