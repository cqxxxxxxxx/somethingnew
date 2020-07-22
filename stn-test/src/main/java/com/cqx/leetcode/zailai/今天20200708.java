package com.cqx.leetcode.zailai;

import java.util.*;

public class 今天20200708 {

    /**
     * https://leetcode-cn.com/problems/intersection-of-two-arrays/description/
     */
    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set = new HashSet<>();
            Set<Integer> setR = new HashSet<>();
            for (int i = 0; i < nums1.length; i++) {
                set.add(nums1[i]);
            }
            for (int i = 0; i < nums2.length; i++) {
                if (set.contains(nums2[i])) {
                    setR.add(nums2[i]);
                }
            }
            int[] r = new int[setR.size()];
            int index = 0;
            for (Integer integer : setR) {
                r[index] = integer;
                index++;
            }
            return r;
        }
    }


    /**
     * https://leetcode-cn.com/problems/assign-cookies/
     */
    class Solution1 {
        public int findContentChildren(int[] g, int[] s) {
            Arrays.sort(g);
            Arrays.sort(s);
            int sIndex = 0;
            int r = 0;
            for (int i = 0; i < g.length; i++) {
                int minVal = g[i];
                for (; sIndex < s.length; ) {
                    if (minVal <= s[sIndex]) {
                        r++;
                        sIndex++;
                        break;
                    }
                    sIndex++;
                }
            }
            return r;
        }
    }


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public static class Solution2 {
        public static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }
        }

        public int[] findMode(TreeNode root) {
            Map<Integer, Integer> map = new HashMap<>();
            recursion(root, map);
            int max = Integer.MIN_VALUE;
            List<Integer> list = new ArrayList<>();
            for (Map.Entry<Integer, Integer> x : map.entrySet()) {
                if (x.getValue() > max) {
                    max = x.getValue();
                }
            }
            for (Map.Entry<Integer, Integer> x : map.entrySet()) {
                if (x.getValue() == max) {
                    list.add(x.getKey());
                }
            }
            int[] r = new int[list.size()];
            for (int i = 0; i < r.length; i++) {
                r[i] = list.get(i);
            }
            return r;
        }


        private void recursion(TreeNode node, Map<Integer, Integer> map) {
            if (node != null) {
                Integer count = map.get(node.val);
                if (count != null) {
                    map.put(node.val, ++count);
                } else {
                    map.put(node.val, 1);
                }
                recursion(node.left, map);
                recursion(node.right, map);
            }
        }
    }

    public static void main(String[] args) {
        Solution2.TreeNode root = new Solution2.TreeNode(1);
        root.right = new Solution2.TreeNode(2);
        root.right.right = new Solution2.TreeNode(2);
        Solution2 solution2 = new Solution2();
        int[] mode = solution2.findMode(root);
    }
}
