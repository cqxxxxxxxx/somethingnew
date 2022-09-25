package com.cqx.leetcode.zailai.lookback.backtrack;

/**
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 * <p>
 * 卡特兰公式推导：G(n)=G(0)*G(n-1) + G(1)*G(n-2) +....+ G(n-2)*G(1) + G(n-1)G(0)
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 */
public class M96_不同的二叉搜索树 {


    /**
     * 假设n个节点的二叉树搜索树数量为G(n) , f(i) 为以i为根节点的二叉搜索树数量个数
     * G(n)=f(1)+f(2)+f(3)+f(4)+...+f(n)
     * <p>
     * 当以i为根节点时，左子树数量为 i-1, 右子树数量为 n-i(因为他是二叉搜索树)
     * f(i)=G(i-1)*G(n-i)
     * <p>
     * 综上 G(n)=G(0)*G(n-1) + G(1)*G(n-2) +....+ G(n-2)*G(1) + G(n-1)G(0)
     * 即卡特兰公式
     *
     * <p>
     * <p>
     * 动态规划
     * dp[n] 表示 n个节点时候二叉搜索树的数量
     * dp[n]=G(0)*G(n-1) + G(1)*G(n-2) +....+ G(n-2)*G(1) + G(n-1``````)G(0)
     *
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = 0;
            for (int j = 0; j < i; j++) {
                dp[i] = dp[i] + dp[j] * dp[i - j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int i = numTrees(4);
        System.out.println(i);
    }


//====================== 错误，我想的是用回溯+枝剪统计， 不过应该也能做出来 ==========================

    /**
     * 二叉树的数组表示
     * n层的树有2的n次-1个节点,即数组大小为2的n次-1
     * 数组下标k的子节点位置为2k+1,2k+2
     * <p>
     * 回溯
     * 左节点赋值
     * 右节点赋值
     * 左右都赋值
     *
     * @param n
     * @return
     */
    public int numTrees1(int n) {
        boolean[] nodes = new boolean[n];
        backtrack(nodes, n);
        return count;
    }

    private int count = 0;

    /**
     * @param nodes  表示节点， true false表示是否被使用了
     * @param remain 剩下几个节点没有使用
     */
    private void backtrack(boolean[] nodes, int remain) {
        if (remain == 0) {
            count++;
            return;
        }
        for (int i = 0; i < nodes.length; i++) {
            boolean node = nodes[i];
            if (node) {
                continue;
            }
            node = true;
        }
    }
}
