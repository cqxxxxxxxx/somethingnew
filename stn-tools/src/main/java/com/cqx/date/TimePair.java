package com.cqx.date;

/**
 * Author :keepcleargas
 * Date   :2016-12-22 21:39.
 */
public class TimePair {
    private long startTime;
    private long endTime;

    public TimePair() {
    }

    public TimePair(long startTime, long endTime) {
        this.endTime = endTime;
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "TimePair{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
