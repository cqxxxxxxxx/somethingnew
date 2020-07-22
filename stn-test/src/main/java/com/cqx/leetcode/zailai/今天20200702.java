package com.cqx.leetcode.zailai;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 今天20200702 {


    /**
     * https://leetcode.com/problems/majority-element/description/
     */
    public static class Solution {
        /**
         * time O(n+n)
         * space O(n+n)
         *
         * @param nums
         * @return
         */
        public int majorityElement(int[] nums) {
            int half = nums.length / 2;
            Map<Integer, Integer> numCountMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                boolean b = numCountMap.containsKey(nums[i]);
                if (b) {
                    Integer count = numCountMap.get(nums[i]);
                    numCountMap.put(nums[i], count + 1);
                } else {
                    numCountMap.put(nums[i], 1);
                }
            }
            for (Map.Entry<Integer, Integer> entry : numCountMap.entrySet()) {
                if (entry.getValue() > half) {
                    return entry.getKey();
                }
            }
            return 1;
        }

        public int majorityElement1(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }

        /**
         * 投票算法
         * https://leetcode-cn.com/problems/majority-element/solution/duo-shu-yuan-su-by-leetcode-solution/
         * 我们维护一个候选众数 candidate 和它出现的次数 count。初始时 candidate 可以为任意值，count 为 0；
         * 我们遍历数组 nums 中的所有元素，对于每个元素 x，在判断 x 之前，如果 count 的值为 0，我们先将 x 的值赋予 candidate，随后我们判断 x：
         * 如果 x 与 candidate 相等，那么计数器 count 的值增加 1；
         * 如果 x 与 candidate 不等，那么计数器 count 的值减少 1。
         * 在遍历完成后，candidate 即为整个数组的众数。
         *
         * @param nums
         * @return
         */
        public int majorityElement2(int[] nums) {
            int count = 0;
            Integer candidate = null;

            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                }
                count += (num == candidate) ? 1 : -1;
            }

            return candidate;
        }
    }


    /**
     * TODO 不会做
     * https://leetcode-cn.com/problems/factorial-trailing-zeroes/
     */
    static class Solution1 {
        public static int trailingZeroes(int n) {
            // Calculate n!
            BigInteger nFactorial = BigInteger.ONE;
            for (int i = 2; i <= n; i++) {
                nFactorial = nFactorial.multiply(BigInteger.valueOf(i));
            }

            // Count how many 0's are on the end.
            int zeroCount = 0;

            while (nFactorial.mod(BigInteger.TEN).equals(BigInteger.ZERO)) {
                nFactorial = nFactorial.divide(BigInteger.TEN);
                zeroCount++;
            }

            return zeroCount;
        }

        public static int trailingZeroes1(int n) {
            int count = 0;
            int x = 5;

            return count;
        }
    }

    /**
     * https://leetcode-cn.com/problems/reverse-bits/
     * TODO 不会写
     */
    public class Solution2 {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {

            return 1;
        }
    }


    public static void main(String[] args) {
        int i = Solution1.trailingZeroes1(30);
        System.out.println(i);
        System.out.println(Solution1.trailingZeroes(30));
    }
}
