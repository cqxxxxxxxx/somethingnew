package com.cqx.leetcode.zailai.lookback.heap;

import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/
 */
public class 剑指Offer41_数据流中的中位数 {


    /**
     * 小顶堆中每个值都大于等于大顶堤
     * 中位数就是 如果size相等， 则取两个top /2
     * 如果size不相等，则取多大顶堆的top
     */
    PriorityQueue<Integer> minHeap = new PriorityQueue<>( (n1, n2) -> n2 - n1);
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>( (n1, n2) -> n1 - n2);

    public void addNum(int num) {
        if(maxHeap.size() == minHeap.size()) {
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        }
        else {
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0D;
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        剑指Offer41_数据流中的中位数 obj = new 剑指Offer41_数据流中的中位数();
        obj.addNum(1);
        obj.addNum(2);
        obj.addNum(3);
        System.out.println(obj.findMedian());
    }
}
