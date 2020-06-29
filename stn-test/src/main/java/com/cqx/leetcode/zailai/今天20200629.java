package com.cqx.leetcode.zailai;

public class 今天20200629 {

    class Solution {
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        /**
         * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
         * 二叉树最大深度
         *
         * @param root
         * @return
         */
        public int maxDepth(TreeNode root) {
            return depth(root);
        }


        private int depth(TreeNode node) {
            if (node == null) {
                return 0;
            }
            int left = depth(node.left);
            int right = depth(node.right);
            if (left > right) {
                return 1 + left;
            } else {
                return 1 + right;
            }
        }
    }


    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
     */
    class Solution1 {
        public int maxProfit(int[] prices) {
            int max = 0;
            for (int i = 0; i < prices.length; i++) {
                int buy = prices[i];
                for (int j = i + 1; j < prices.length; j++) {
                    int sell = prices[j];
                    max = Math.max(max, sell - buy);
                }
            }
            return max;
        }

        public int maxProfit1(int[] prices) {
            int min = prices[0];
            int max = 0;
            for (int i = 0; i < prices.length; i++) {
                min = Math.min(prices[i], min);
                max = Math.max(prices[i] - min, max);
            }
            return max;
        }
    }


    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
     * https://github.com/azl397985856/leetcode/blob/master/problems/122.best-time-to-buy-and-sell-stock-ii.md
     */
    class Solution2 {
        public int maxProfit(int[] prices) {
            int profit = 0;
            for (int i = 0; i < prices.length; i++) {
                if (i + 1 < prices.length) {
                    int val = prices[i + 1] - prices[i];
                    if (val > 0) {
                        profit += val;
                    }
                }
            }
            return profit;
        }
    }


    /**
     * 判断是否回文
     * https://github.com/azl397985856/leetcode/blob/master/problems/125.valid-palindrome.md
     * https://leetcode.com/problems/valid-palindrome/description/
     */
    class Solution3 {
        public boolean isPalindrome(String s) {
            if (s == null) {
                return false;
            }
            char[] chars = s.toLowerCase().toCharArray();
            int start = 0;
            int end = chars.length - 1;
            boolean skip = false;
            while (start < end) {
                boolean letterOrDigit = Character.isLetterOrDigit(chars[start]);
                if (!letterOrDigit) {
                    start++;
                    skip = true;
                }
                boolean letterOrDigit1 = Character.isLetterOrDigit(chars[end]);
                if (!letterOrDigit1) {
                    end--;
                    skip = true;
                }
                if (skip) {
                    skip = false;
                } else {
                    if (chars[start] == chars[end]) {
                        start++;
                        end--;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
