package com.cqx.leetcode.zailai.lookback.sort;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * <p>
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * <p>
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * <p>
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 */
public class M215_数组中的第K个最大元素 {

    /**
     * 基于快排，当快排的pivot为k时候，就可以直接返回对应的值了。
     *
     * @param nums
     * @param k
     * @return
     */
    Random random = new Random();

    public int findKthLargest0(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    public int quickSelect(int[] a, int l, int r, int index) {
        int q = randomPartition(a, l, r);
        if (q == index) {
            return a[q];
        } else {
            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
        }
    }

    public int randomPartition(int[] a, int l, int r) {
        int i = random.nextInt(r - l + 1) + l;
        swap(a, i, r);
        return partition(a, l, r);
    }

    public int partition(int[] a, int l, int r) {
        //i表示比x小的l,r之间的索引位置。刚开始为l-1即为不存在。
        int x = a[r], i = l - 1;
        for (int j = l; j < r; ++j) {
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


//============================================

    /**
     * TODO 理解错了，其实是可以有重复的 因为含有重复元素，不能直接用小顶对
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
//        Set<Integer> set = new HashSet<>();
        //小顶堆
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int num : nums) {
//            if (set.add(num)) {
            if (q.size() < k) {
                q.add(num);
            } else {
                if (q.peek() < num) {
                    q.add(num);
                    q.poll();
                }
            }
//            }
        }
        return q.peek();
    }
}
