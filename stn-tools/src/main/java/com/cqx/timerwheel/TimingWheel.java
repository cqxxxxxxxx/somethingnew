package com.cqx.timerwheel;

import java.util.concurrent.DelayQueue;

public class TimingWheel {

    private long tickMs;
    private TaskList[] buckets;
    //2的n次方
    private int wheelSize;
    private long interval;
    private long startMs;
    private DelayQueue<TaskList> delayQueue;

    public TimingWheel(long tickMs, int expectWheelSize, long startMs, DelayQueue<TaskList> delayQueue) {
        this.tickMs = tickMs;
        this.wheelSize = tableSizeFor(expectWheelSize);
        this.startMs = startMs;
        this.buckets = new TaskList[wheelSize];
        this.interval = tickMs * wheelSize;
        this.delayQueue = delayQueue;
    }

    public void add(TimerTask timerTask) {
        int mod = mod(timerTask.delayMs);
        TaskList taskList = buckets[mod];
        if (taskList == null) {
            taskList = new TaskList();
            buckets[mod] = taskList;
        }
        taskList.add(timerTask);
        delayQueue.offer(taskList);
    }


    /**
     * 计算时间轮中的位置
     *
     * @param wheelSize
     * @param n
     * @return
     */
    private final int mod(long n) {
        return Math.toIntExact(n & (wheelSize - 1));
    }

    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        // 这里1073741824 = 2^30,防止溢出
        return (n < 0) ? 1 : (n >= 1073741824) ? 1073741824 : n + 1;
    }
}
