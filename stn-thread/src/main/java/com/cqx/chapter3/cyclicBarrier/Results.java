package com.cqx.chapter3.cyclicBarrier;

/**
 * Created by cqx on 2017/2/20.
 */
public class Results {

    private int data[];

    public Results(int size) {
        data = new int[size];
    }

    public void setData(int position, int value) {
        data[position] = value;
    }

    public int[] getData() {
        return data;
    }
}
