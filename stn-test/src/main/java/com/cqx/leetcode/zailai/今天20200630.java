package com.cqx.leetcode.zailai;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 今天20200630 {

    /**
     * https://leetcode.com/problems/single-number/solution/
     */
    class Solution {
        /**
         * time O（n^2）
         * space O(n)
         *
         * @param nums
         * @return
         */
        public int singleNumber(int[] nums) {
            int r = 0;
            for (int i = 0; i < nums.length; i++) {
                int v1 = nums[i];
                boolean exist = false;
                for (int j = 0; j < nums.length; j++) {
                    int v2 = nums[j];
                    if (v2 == v1 && j != i) {
                        exist = true;
                    }
                }
                if (!exist) {
                    r = v1;
                    break;
                }
            }
            return r;
        }

        /**
         * time O(logN)
         * space O(N)
         *
         * @param nums
         * @return
         */
        public int singleNumber1(int[] nums) {
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; ) {
                int v0 = nums[i];
                if (i + 1 >= nums.length - 1) {
                    return nums[i + 1];
                }
                int v1 = nums[i + 1];
                if (v0 != v1) {
                    return v0;
                } else {
                    i = i + 2;
                }
            }
            return 9;
        }


        /**
         * 2∗(a+b+c)−(a+a+b+b+c)=c
         * time O(n + n)
         * space O(n + n)
         *
         * @param nums
         * @return
         */
        public int singleNumber3(int[] nums) {
            int sumOfSet = 0, sumOfNums = 0;
            Set<Integer> set = new HashSet();

            for (int num : nums) {
                if (!set.contains(num)) {
                    set.add(num);
                    sumOfSet += num;
                }
                sumOfNums += num;
            }
            return 2 * sumOfSet - sumOfNums;
        }


        /**
         * 异或运算
         * https://leetcode.com/problems/single-number/solution/
         *
         * @param nums
         * @return
         */
        public int singleNumber4(int[] nums) {
            int a = 0;
            for (int i : nums) {
                a ^= i;
            }
            return a;
        }
    }


    public static class Solution1 {
        public static class MinStack {
            private int[] holder;
            private int index = -1;
            private Integer min = null;

            /**
             * initialize your data structure here.
             */
            public MinStack() {
                holder = new int[10];
            }

            public void push(int x) {
                if (min == null) {
                    min = x;
                } else {
                    if (x < min) {
                        min = x;
                    }
                }
                holder[++index] = x;
                if (index == holder.length - 1) {
                    //扩容
                    int[] holderNew = new int[holder.length * 2];
                    System.arraycopy(holder, 0, holderNew, 0, holder.length);
                    holder = holderNew;
                }
            }

            public void pop() {
                int v = holder[index];
                index--;
                if (min == v) {
                    min = Integer.MAX_VALUE;
                    for (int i = 0; i <= index; i++) {
                        min = Integer.min(min, holder[i]);
                    }
                }
            }

            public int top() {
                return holder[index];
            }

            public int getMin() {
                return min;
            }
        }


    }

    class Solution2 {
        public int[] twoSum(int[] numbers, int target) {
            int head = 0;
            int tail = numbers.length - 1;
            while (head < tail) {
                int v1 = numbers[head];
                int v2 = numbers[tail];
                if (v1 + v2 == target) {
                    return new int[]{head, tail};
                }
                if (v1 + v2 < target) {
                    head++;
                } else {
                    tail--;
                }
            }
            return new int[]{0, 0};
        }
    }

    public static void main(String[] args) {
        Solution1.MinStack stack = new Solution1.MinStack();
        stack.push(2);
        stack.push(0);
        stack.push(3);
        stack.push(0);
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.getMin());

    }
}
