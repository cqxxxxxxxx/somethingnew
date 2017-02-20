package com.cqx.chapter2.lock;

/**
 * 获取写锁的时候，读锁是阻塞的，只有释放了写锁，其他线程才能获取读锁。
 * 同理读锁被某些线程占有的时候，写锁是获取不了的，只有读锁被释放，写锁才有机会获取。
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
