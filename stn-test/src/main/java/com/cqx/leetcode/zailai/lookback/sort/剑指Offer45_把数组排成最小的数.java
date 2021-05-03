package com.cqx.leetcode.zailai.lookback.sort;

import java.util.Arrays;

/**
 * 剑指 Offer 45. 把数组排成最小的数
 * https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
 */
public class 剑指Offer45_把数组排成最小的数 {

    public String minNumber(int[] nums) {
// 几个要拼接的数
        int n = nums.length;
        // 拆分出来放到字符串数组里
        String[] ss = new String[n];
        for (int i = 0; i < n; i++) {
            ss[i] = String.valueOf(nums[i]);
        }
        // 两两组合排序 组合出小的放前面
        // 就单纯字典序 0123456789 也很字典序
        /**
         * 120 20
         * 120+20 => 12020
         * 20+120 => 20120 > 12020 => 即120放前面
         */
        Arrays.sort(ss, ((o1, o2) -> {
            String s1 = o1 + o2, s2 = o2 + o1;
            return s1.compareTo(s2);
        }));
        StringBuilder sb = new StringBuilder();
        for (String s : ss) {
            sb.append(s);
        }
        // 这题 [0,0] === > 00 不用输出 0
        return sb.toString();

    }

}
