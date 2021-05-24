package com.cqx.leetcode.zailai.lookback.tiretree;

/**
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * 进阶：你可以在 O(n) 的时间解决这个问题吗？
 * <p>
 * 示例 1：
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28.
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array
 */
public class M421_数组中两个数的最大异或值 {

    /**
     * 暴力超时
     * @param nums
     * @return
     */
    public static int findMaximumXOR(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                max = Math.max(max, nums[i] ^ nums[j]);
            }
        }
        return max;
    }

    /**
     * 字典树 有趣
     * @param nums
     * @return
     */
    public static int findMaximumXOR1(int[] nums) {
        Node root = initTrieTree(nums);
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            Node cur = root;
            int sum = 0;
            for (int i = MAX_BIT; i >= MIN_BIT; i--) {
                int bit = getBit(num, i);
                int xorBit = bit ^ 1;
                //如果有和num的第i位相反的，就往反的这边走
                Node node = cur.children[xorBit];
                if (node == null) {
                    node = cur.children[bit];
                } else {
                    sum += (1 << i);
                }
                cur = node;
            }
            max = Math.max(max, sum);
        }
        return max;
    }


    public static class Node {
        private Node[] children = new Node[2];
    }

    static final int MAX_BIT = 31;
    static final int MIN_BIT = 0;

    public static Node initTrieTree(int[] nums) {
        Node root = new Node();
        for (int num : nums) {
            Node cur = root;
            for (int i = MAX_BIT; i >= MIN_BIT; i--) {
                int bit = getBit(num, i);
                Node node = cur.children[bit];
                if (node == null) {
                    node = new Node();
                    cur.children[bit] = node;
                }
                cur = node;
            }
        }
        return root;
    }

    public static int getBit(int num, int i) {
        return (num >> i) & 1;
    }


    public static void main(String[] args) {
        Node node = initTrieTree(new int[]{2, 3, 4});
        System.out.println(100 >> 1);
        System.out.println(100 >>> 1);
        System.out.println(Integer.MAX_VALUE >> 1);
        System.out.println(Integer.MAX_VALUE >>> 1);
        System.out.println(-1 >> 1);
        System.out.println(-1 >>> 1);
    }
}
