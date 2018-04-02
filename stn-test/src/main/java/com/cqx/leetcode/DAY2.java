package com.cqx.leetcode;

import org.junit.Test;

/**
 * Created by cqx on 2018/3/25.
 */
public class DAY2 {
    //      Definition for a binary tree node.
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public TreeNode sortedArrayToBST(int[] nums) {
//        TreeNode root;
//        if (nums.length > 1) {
//            root = middleOf(nums);
//        } else {
//            root = new TreeNode(nums[0]);
//        }
//        return root;

        if (nums.length == 0) {
            return null;
        }
        TreeNode root = parse(nums, 0, nums.length - 1);
        return root;
    }


    public TreeNode parse(int[] nums, int low, int high) {
        if (low > high) { // Done
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = parse(nums, low, mid - 1);
        node.right = parse(nums, mid + 1, high);
        return node;
    }

    /**
     * 辣鸡
     *
     * @param nums
     * @return
     */
    public TreeNode middleOf(int[] nums) {
        if (nums.length == 1) {
            TreeNode node = new TreeNode(nums[0]);
            return node;
        }
        if (nums.length == 2) {
            TreeNode node = new TreeNode(nums[1]);
            node.left = new TreeNode(nums[0]);
            return node;
        }
        if (nums.length > 2) {
            int length = nums.length;
            int leftLength = (length + 1) / 2 - 1;
            int rightLength = length - (length + 1) / 2;

            int[] leftNums = new int[leftLength];
            int[] rightNums = new int[rightLength];
            int middleVal = nums[((length + 1) / 2) - 1];

            System.arraycopy(nums, 0, leftNums, 0, leftLength);
            System.arraycopy(nums, (length + 1) / 2, rightNums, 0, rightLength);

            TreeNode root = new TreeNode(middleVal);
            root.left = middleOf(leftNums);
            root.right = middleOf(rightNums);
            return root;
        }

        return null;
    }

    @Test
    public void test1() {
        int[] ascInts = new int[20];
        for (int i = 0; i < 20; i++) {
            ascInts[i] = i;
        }
        TreeNode tree = sortedArrayToBST(ascInts);
    }
}
