package com.cqx.leetcode.zailai.lookback.tiretree;

import java.util.Arrays;

/**
 * 给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
 * 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR xi) ，其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
 * 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
 *  
 * 示例 1：
 * 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
 * 输出：[3,3,7]
 * 解释：
 * 1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
 * 2) 1 XOR 2 = 3.
 * 3) 5 XOR 2 = 7.
 * <p>
 * 示例 2：
 * 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
 * 输出：[15,-1,5]
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-xor-with-an-element-from-array
 */
public class H1707_与数组中元素的最大异或值 {

    /**
     * 跟{@link M421_数组中两个数的最大异或值} 类似，字典树
     * 区别就是queries中Mi限制了最大值， 可以nums小到大排序，然后queries中按Mi小到大排序
     * queries进行计算时候，树中逐步放入小于Mi的nums，这样每次都能符合树中值小于Mi
     */


    /**
     * 比特位字典树节点
     */
    public static class Tire {
        /**
         * 比特位，int最大位为符号位不参与
         */
        private static final Integer MAX_BIT = 31;
        private static final Integer MIN_BIT = 0;

        private Tire[] children = new Tire[2];

        public void add(int val) {
            Tire cur = this;
            for (int i = MAX_BIT; i >= MIN_BIT; i--) {
                int bit = getBit(val, i);
                Tire node = cur.children[bit];
                if (node == null) {
                    node = new Tire();
                    cur.children[bit] = node;
                }
                cur = node;
            }
        }

        public int getMaxXor(int val) {
            Tire cur = this;
            int max = 0;
            for (int i = MAX_BIT; i >= MIN_BIT; i--) {
                int bit = getBit(val, i);
                int xorBit = bit ^ 1;
                Tire node = cur.children[xorBit];
                if (node != null) {
                    max = max + (1 << i);
                } else {
                    node = cur.children[bit];
                }
                cur = node;
            }
            return max;
        }

        public int getBit(int val, int bit) {
            return (val >> bit) & 1;
        }
    }

    public Tire root = new Tire();


    /**
     * @param nums
     * @param queries
     * @return
     */
    public int[] maximizeXor0(int[] nums, int[][] queries) {
        Arrays.sort(nums);
        //newQueries[i][2]=原来queries的索引位置，因为输出结果顺序要跟queries一致
        int[][] newQueries = new int[queries.length][3];
        for (int i = 0; i < queries.length; i++) {
            newQueries[i][0] = queries[i][0];
            newQueries[i][1] = queries[i][1];
            newQueries[i][2] = i;
        }
        Arrays.sort(newQueries, (a, b) -> {
            return a[1] - b[1];
        });
        int numIndex = 0;
        int[] r = new int[newQueries.length];
        for (int i = 0; i < newQueries.length; i++) {
            int v = newQueries[i][0];
            int max = newQueries[i][1];
            while (numIndex < nums.length) {
                if (nums[numIndex] <= max) {
                    root.add(nums[numIndex]);
                    numIndex++;
                } else {
                    break;
                }
            }
            if (numIndex == 0) {
                r[newQueries[i][2]] = -1;
            } else {
                r[newQueries[i][2]] = root.getMaxXor(v);
            }
        }
        return r;
    }

    public static void main(String[] args) {
        H1707_与数组中元素的最大异或值 o = new H1707_与数组中元素的最大异或值();
        o.maximizeXor0(new int[]{0, 1, 2, 3, 4},
                new int[][]{{3, 1}, {1, 3}, {5, 6}});

    }


//====================   暴力    超出时间限制======================

    /**
     * 暴力
     * 不行，超出时间限制
     *
     * @param nums
     * @param queries
     * @return
     */
    public int[] maximizeXor1(int[] nums, int[][] queries) {
        //O(NLogN)
        Arrays.sort(nums);
        int[] r = new int[queries.length];
        //O(N*N)
        for (int j = 0; j < queries.length; j++) {
            int v = queries[j][0];
            int max = queries[j][1];
            int res = -1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > max) {
                    break;
                }
                res = Math.max(nums[i] ^ v, res);
            }
            r[j] = res;
        }
        return r;
    }
}
