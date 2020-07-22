package com.cqx.leetcode.zailai;

import java.util.Stack;

public class 今天20200707 {


    public static class Solution {
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }
        }

        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return root;
            }
            invertTree(root.left);
            invertTree(root.right);
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            return root;
        }


        public TreeNode invertTree1(TreeNode root) {
            if (root == null) {
                return root;
            }
            invertTree(root.left);
            invertTree(root.right);
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            return root;
        }
    }


    public static class Solution1 {
        class MyQueue {
            private Stack<Integer> stack1;
            private Stack<Integer> stack2;

            /**
             * Initialize your data structure here.
             */
            public MyQueue() {
                stack1 = new Stack<>();
                stack2 = new Stack<>();
            }

            /**
             * Push element x to the back of queue.
             */
            public void push(int x) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                stack1.push(x);
                while (!stack2.isEmpty()) {
                    stack1.push(stack2.pop());
                }
            }

            /**
             * Removes the element from in front of queue and returns that element.
             */
            public int pop() {
                return stack1.pop();
            }

            /**
             * Get the front element.
             */
            public int peek() {
                return stack1.peek();
            }

            /**
             * Returns whether the queue is empty.
             */
            public boolean empty() {
                return stack1.empty();
            }

        }
    }


    /**
     * https://leetcode-cn.com/problems/ugly-number/
     */
    class Solution2 {
        public boolean isUgly(int num) {
            if (num <= 0) {
                return false;
            }

            int[] factor = new int[]{2, 3, 5};
            for (int i : factor) {
                while (num % i == 0) {
                    num = num / i;
                }
            }
            if (num == 1) {
                return true;
            }
            return false;
        }
    }

    class Solution3 {
        public void moveZeroes(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                int v0 = nums[i];
                if (v0 == 0) {
                    for (int j = i + 1; j < nums.length; j++) {
                        int v1 = nums[j];
                        if (v1 != 0) {
                            nums[i] = v1;
                            nums[j] = 0;
                            break;
                        }
                    }
                }
            }
        }

        public void moveZeroes1(int[] nums) {
            int index = 0;
            for (int i = 0; i < nums.length; i++) {
                int v0 = nums[i];
                if (v0 != 0) {
                    nums[index] = v0;
                    index++;
                }
            }
            for (int i = index; i < nums.length; i++) {
                nums[i] = 0;
            }
        }
    }

    /**
     * https://leetcode-cn.com/problems/power-of-four/solution/4de-mi-by-leetcode/
     * 数论
     * 2的幂次方特点（数学性质以及二进制表示）
     * 4的幂次方特点（数学性质以及二进制表示）
     */
    class Solution4 {
        public boolean isPowerOfFour(int num) {
            if (num == 0) {
                return false;
            }
            if (num == 1) {
                return true;
            }
            if (num % 4 != 0) {
                return false;
            }
            if (num == 4) {
                return true;
            }
            return isPowerOfFour(num / 4);
        }

        public boolean isPowerOfFour1(int num) {
            return (num > 0) && ((num & (num - 1)) == 0) && ((num & 0xaaaaaaaa) == 0);
        }
    }
}
