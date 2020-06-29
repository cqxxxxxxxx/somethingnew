package com.cqx.leetcode.zailai;

public class 今天20200620 {

    /**
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/description/?utm_source=LCUS&utm_medium=ip_redirect_q_uns&utm_campaign=transfer2china
     * 双指针
     */
    class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums.length == 0) return 0;
            int i = 0;
            for (int j = 1; j < nums.length; j++) {
                if (nums[j] != nums[i]) {
                    i++;
                    nums[i] = nums[j];
                }
            }
            return i + 1;
        }
    }

    /**
     * https://leetcode-cn.com/problems/maximum-subarray/submissions/
     * 最优前缀和
     * https://github.com/azl397985856/leetcode/blob/master/problems/53.maximum-sum-subarray-cn.md#%E8%A7%A3%E6%B3%95%E4%B8%89---%E4%BC%98%E5%8C%96%E5%89%8D%E7%BC%80%E5%92%8C---from-lucifer
     */
    class Solution2 {
        public int maxSubArray(int[] nums) {
            int max = Integer.MIN_VALUE;
            if (nums == null) {
                return 0;
            }
            for (int i = 0; i < nums.length; i++) {
                int maxInRound = nums[i];
                int sum = nums[i];
                for (int j = i + 1; j < nums.length; j++) {
                    sum = sum + nums[j];
                    maxInRound = maxInRound > sum ? maxInRound : sum;
                }
                max = max > maxInRound ? max : maxInRound;
            }
            return max;
        }
    }


    /**
     * 合并两个有序数组
     * 归并排序 从后往前比较，并从后往前插入
     * https://leetcode-cn.com/problems/merge-sorted-array/
     */
    class Solution3 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int current = m + n - 1;
            while (current >= 0) {
                if (n == 0) {
                    return;
                } else if (m == 0) {
                    nums1[current] = nums2[n - 1];
                    n--;
                } else {
                    int val0 = nums1[m - 1];
                    int val1 = nums2[n - 1];
                    if (val1 > val0) {
                        nums1[current] = val1;
                        n--;
                    } else {
                        nums1[current] = val0;
                        m--;
                    }
                }
                current--;
            }
        }
    }


}
