package com.cqx.leetcode.zailai.lookback.deque;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 */
public class H239_滑动窗口的最大值 {

    /**
     * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
     *
     * 示例:
     *
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     *
     *   滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *  
     * 提示：
     * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
     * @param nums
     * @param k
     * @return
     */
    /**
     * 1. 暴力法， 每次都查找下窗口内最大值 时间复杂度为 O（m*k）
     * 2. 单调双端队列
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数按从大到小排序
        Deque<Integer> que = new LinkedList<>();
        int[] arr = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            // 保持队列中的元素为max->min
            while (!que.isEmpty() && nums[i] >= nums[que.getLast()]) {
                que.pollLast();
            }
            que.add(i);
            // 如果队首元素超出当前滑窗范围，则出队
            if (i - que.getFirst() >= k) {
                que.pollFirst();
            }
            // 需要在满足经过一个完整滑窗，再开始记录滑窗最大值
            if (i >= k - 1) {
                arr[i - k + 1] = nums[que.getFirst()];
            }
        }
        return arr;
    }

    public static int[] xx(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) {
            return nums;
        }
        int[] arr = new int[len - k + 1];
        int arr_index = 0;
        //我们需要维护一个单调递增的双向队列
        Deque<Integer> deque = new LinkedList<>();
        //先将第一个窗口的值按照规则入队
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.offerLast(nums[i]);
        }
        //存到数组里，队头元素
        arr[arr_index++] = deque.peekFirst();
        //移动窗口
        for (int j = k; j < len; j++) {
            //对应咱们的红色情况，则是窗口的前一个元素等于队头元素
            if (nums[j - k] == deque.peekFirst()) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[j]) {
                deque.removeLast();
            }
            deque.offerLast(nums[j]);
            arr[arr_index++] = deque.peekFirst();
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] a = {5, 4, 3, 2, 1, 1, 1, 1};
        int[] xx = H239_滑动窗口的最大值.xx(a, 3);
    }
}
