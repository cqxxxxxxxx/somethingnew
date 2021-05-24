package com.cqx.leetcode.zailai.lookback.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 */
public class M347_前K个高频元素 {

    /**
     * 小顶堆 。模板代码照着抄
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent0(int[] nums, int k) {
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num : nums) {
            numCountMap.put(num, numCountMap.getOrDefault(num, 0) + 1);
        }
        //小顶堆
        PriorityQueue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>((a, b) -> {
            return a.getValue() - b.getValue();
        });
        for (Map.Entry<Integer, Integer> entry : numCountMap.entrySet()) {
            if (q.size() < k) {
                q.add(entry);
                continue;
            }
            if (q.peek().getValue() < entry.getValue()) {
                q.poll();
                q.add(entry);
            }
        }
        int[] r = new int[k];
        for (int i = 0; i < k; i++) {
            r[i] = q.poll().getKey();
        }
        return r;
    }

    /**
     * 这边用的是大顶堆， 其实应该用小顶堆，大顶堆的话每个值都需要插入，小顶堆只要维护K个值就好了
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num : nums) {
            numCountMap.put(num, numCountMap.getOrDefault(num, 0) + 1);
        }
        //默认是小顶堆, 改造成大顶堆
        PriorityQueue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>((a, b) -> {
            return b.getValue() - a.getValue();
        });
        for (Map.Entry<Integer, Integer> entry : numCountMap.entrySet()) {
            q.add(entry);
        }
        int[] r = new int[k];
        for (int i = 0; i < k; i++) {
            r[i] = q.poll().getKey();
        }
        return r;
    }
}
