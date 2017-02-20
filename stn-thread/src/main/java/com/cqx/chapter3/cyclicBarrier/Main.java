package com.cqx.chapter3.cyclicBarrier;

import com.cqx.chapter3.semaphore.Job;
import com.cqx.chapter3.semaphore.PrintQueue;

import java.util.concurrent.CyclicBarrier;

/**
 *
 * Created by cqx on 2017/2/20.
 */
public class Main {

    public static void main(String[] args){
        final int ROWS = 10000;
        final int NUMBERS = 1000;
        final int SEARCH = 5;   //目标数字
        final int PARTICIPANTS = 5;     //等待的线程数量
        final int LINES_PARTICIPANT = 2000;

        MatrixMock matrixMock = new MatrixMock(ROWS, NUMBERS, SEARCH);
        Results results = new Results(ROWS);
        Grouper grouper = new Grouper(results);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(PARTICIPANTS, grouper);
        Searcher[] searchers = new Searcher[PARTICIPANTS];
        for (int i = 0; i < PARTICIPANTS; i++) {
            searchers[i] = new Searcher(i*LINES_PARTICIPANT,
                    i*LINES_PARTICIPANT + LINES_PARTICIPANT,
                    matrixMock, 5, cyclicBarrier, results);
            Thread thread = new Thread(searchers[i]);
            thread.start();
        }
        System.out.printf("Main: The main thread has finished.\n");
    }
}
