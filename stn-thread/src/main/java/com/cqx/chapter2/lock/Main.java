package com.cqx.chapter2.lock;

/**
 * Created by Shan on 2017/2/19.
 */
public class Main {

    public static void main(String[] args){
        PricesInfo pricesInfo = new PricesInfo();

        Reader[] readers = new Reader[5];
        Thread[] readerThreads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            readers[i] = new Reader(pricesInfo);
            readerThreads[i] = new Thread(readers[i]);
        }

        Writer writer = new Writer(pricesInfo);
        Thread writerThread = new Thread(writer);

        for (int i = 0; i < 5; i++) {
            readerThreads[i].start();
        }
        writerThread.start();

    }

}
