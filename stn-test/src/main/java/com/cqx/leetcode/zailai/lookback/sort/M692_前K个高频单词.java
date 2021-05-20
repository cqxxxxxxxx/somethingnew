package com.cqx.leetcode.zailai.lookback.sort;

import java.util.*;

/**
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * <p>
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 * <p>
 * 示例 1：
 * <p>
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 * 注意，按字母顺序 "i" 在 "love" 之前。
 */
public class M692_前K个高频单词 {

    /**
     * 1. 先hashMap统计次数， 时间复杂度 N
     * 2. 大顶堆排序  时间复杂度 NLogK
     *
     * @param words
     * @param k
     * @return
     */
    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        //小顶堆
        PriorityQueue<String> q = new PriorityQueue<>((a, b) -> {
            if (count.get(a).equals(count.get(b))) {
                return b.compareTo(a);
            } else {
                return count.get(a) - count.get(b);
            }
        });
        // 3.依次向堆加入元素。
        count.keySet().forEach(x -> {
            q.offer(x);
            if (q.size() > k) {
                q.poll();
            }
        });
        //todo 有问题，这样就没有用到 小顶堆 优先级队列 poll的特性
//        List<Striqng> r = q.stream().collect(Collectors.toList());
        List<String> r = new ArrayList<>();
        while (q.size() > 0) {
            r.add(q.poll());
        }
        Collections.reverse(r);
        return r;
    }


    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        List<String> list = topKFrequent(words, 3);
//        List<String> list1 = topKFrequent1(words, 3);
//        System.out.println(list);


    }
}
