package com.cqx.leetcode.zailai.lookback.qianzuihe;


import java.util.HashMap;
import java.util.Map;

/**
 * 前缀和 + hashMap + 回溯
 */
public class M437_路径总和III {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private Map<Integer, Integer> map;
    private int sum;
    private int count = 0;

    public int pathSum(TreeNode root, int sum) {
        this.map = new HashMap<>();
        map.put(0, 1);
        this.sum = sum;
        dfs(root, 0);
        return count;
    }

    private void dfs(TreeNode node, int preSum) {
        if (node == null) {
            return;
        }
        preSum += node.val;
        if (map.get(preSum - sum) != null) {
            count += map.get(preSum - sum);
        }
        map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        dfs(node.left, preSum);
        dfs(node.right, preSum);
        map.put(preSum, map.get(preSum) - 1);
    }
}
