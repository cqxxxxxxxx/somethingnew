package com.cqx.chapter3.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by cqx on 2017/2/20.
 */
public class Searcher implements Runnable {

    private int firstRow;
    private int lastRow;
    private MatrixMock matrixMock;
    private int number;
    private final CyclicBarrier barrier;
    private Results results;

    public Searcher(int firstRow, int lastRow, MatrixMock matrixMock, int number, CyclicBarrier barrier, Results results) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.matrixMock = matrixMock;
        this.number = number;
        this.barrier = barrier;
        this.results = results;
    }

    @Override
    public void run() {
        int counter;
        System.out.printf("%s: Processing lines from %d to %d.\n", Thread.currentThread().getName(), firstRow, lastRow);
        for (int i = firstRow; i < lastRow; i++) {
            int row[] = matrixMock.getRow(i);
            counter = 0;
            for (int j = 0; j < row.length; j++) {
                if (row[j] == number) {
                    counter++;
                }
            }
            results.setData(i, counter);
        }
        System.out.printf("%s: Lines processed.\n", Thread.currentThread().getName());
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
