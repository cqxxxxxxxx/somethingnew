package com.cqx.leetcode.zailai.lookback.dp;

import java.util.*;

/**
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * <p>
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * <p>
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * <p>
 * 链接：https://leetcode-cn.com/problems/word-break
 */
public class M139_单词拆分 {

    /**
     * 只有DP会写 好理解，剩下两个的DFS,BFS的memo不会搞，遇到重复单词会超时
     * dp
     * dp[i] 标识前i个字符能否被正确拆分
     * dp[i] = dp[j] && check(j,i), j从0~i遍历
     * 即dp[i]被j拆分为左右两部分，如果左边dp[j]=true，并且右边部分是单词，那么就是true
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            dp[i] = false;
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        ArrayList<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        wordBreak0("leetcode", wordDict);
        System.out.println("leetcode".substring(0, 4));
        System.out.println("leetcode".substring(4, 8));
        System.out.println("leetcode".substring(0, 3));
    }

    Set<String> memory = new HashSet<>();

    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
        }
        return DFS(s, set);
    }

    public boolean DFS(String s, Set<String> set) {
        if (s.length() == 0) return true;
        if (memory.contains(s)) return false;//如果记忆中存在此字符串，返回false，结束递归。
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            strb.append(s.charAt(i));
            if (set.contains(strb.toString()) && !memory.contains(s.substring(i + 1))) {
                if (DFS(s.substring(i + 1), set)) {
                    return true;
                } else {
                    memory.add(s.substring(i + 1));//对子串失败的情况进行记忆
                }
            }
        }
        memory.add(s);//对s失败的情况进行记忆
        return false;
    }


    /**
     * bfs
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak0(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        Deque<Integer> q = new LinkedList<>();
        int[] memo = new int[s.length()];
        q.push(0);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Integer start = q.pop();
                for (int j = start; j <= s.length(); j++) {
                    if (set.contains(s.substring(start, j))) {
                        if (j == s.length() - 1) {
                            return true;
                        }
                        q.push(j);
                    }
                }
            }
        }
        return false;
    }


}
