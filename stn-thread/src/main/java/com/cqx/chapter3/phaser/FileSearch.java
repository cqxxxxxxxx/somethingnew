package com.cqx.chapter3.phaser;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by cqx on 2017/2/21.
 */
public class FileSearch implements Runnable {
    private String initPath;
    private String end;
    private List<String> results;
    private Phaser phaser;

    public FileSearch(String initPath, String end, Phaser phaser){
        this.initPath = initPath;
        this.end = end;
        this.phaser = phaser;
        results = new ArrayList<>();
    }

    private void directoryProcess(File file){
        File[] files = file.listFiles();
        if (files != null){
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()){
                    directoryProcess(files[i]);
                }else {
                    fileProcess(files[i]);
                }
            }
        }
    }

    /**
     * 如果文件以特定字符串结尾，则放入result中
     * @param file
     */
    private void fileProcess(File file){
        if (file.getName().endsWith(end)){
            results.add(file.getAbsolutePath());
        }
    }

    private void filterResults(){
        List<String> newResults = new ArrayList<>();
        long actualDate = Instant.now().toEpochMilli(); //获取当前时间 unix时间戳
        for (int i = 0; i < results.size(); i++) {
            File file = new File(results.get(i));
            long fileDate = file.lastModified();    //获取文件最后修改时间
            if (actualDate - fileDate < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)){
                //如果间隔时间小雨一天,则把该文件路径添加斤新的列表中
                newResults.add(results.get(i));
            }
        }
        results = newResults;   //把新列表的引用赋给老的列表

    }

    /**
     * 检查列表长度 0则把未找到文件这个信息打印到控制台，
     *
     * 如果长度大于0 则
     * deregister 撤销注册
     * @return
     */
    private boolean checkResults(){
        if (results.isEmpty()){
            System.out.printf("%s: Phase %d: 0 results.\n",
                    Thread.currentThread().getName(), phaser.getPhase());
            System.out.printf("%s: Phase %d: End.\n",
                    Thread.currentThread().getName(), phaser.getPhase());
            //用Phaser的arriveAndDeregister()方法，通知Phaser对象当前线程已经结束,不进行接下来的操作
            phaser.arriveAndDeregister();
            return false;
        }else {
            System.out.printf("%s: Phase %d: %d results.\n",
                    Thread.currentThread().getName(), phaser.getPhase(), results.size());
            //通知phaser对象当前线程已经完成当前阶段，需要被阻塞直到其他线程也完成当前阶段
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }

    private void showInfo(){
        for (int i = 0; i < results.size(); i++) {
            File file = new File(results.get(i));
            System.out.printf("$s: %S\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }
        phaser.arriveAndAwaitAdvance();
    }


    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: Starting.\n", Thread.currentThread().getName());
        File file = new File(initPath);
        if (file.isDirectory()){
            directoryProcess(file);
        }
        if (!checkResults()){   //为空则结束 返回
            return;
        }
        filterResults();
        if (!checkResults()){
            return;
        }
        showInfo();
        phaser.arriveAndDeregister();
        System.out.printf("%s: Work completed.\n", Thread.currentThread().getName());

    }
}
