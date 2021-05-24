package com.cqx.leetcode.zailai.lookback.slidewindow;

import java.util.*;

/**
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * <p>
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * <p>
 * 说明：
 * <p>
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 * <p>
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * <p>
 * 输出:
 * [0, 6]
 * <p>
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 * <p>
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
 */
public class M438_找到字符串中所有字母异位词 {

    /**
     * 滑动窗口，避免重复统计
     * 用了MAP，性能不行，改用数组比较好
     * map空间和效率没有直接使用数组高，可以优化， 优化后tryDecrCount方法也可以直接被优化掉
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams0(String s, String p) {
        Map<Character, Integer> map = new HashMap<>(p.length());
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        char[] chars = s.toCharArray();
        int left = 0;
        int windowSize = p.length();
        List<Integer> r = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            int curSize = i - left + 1;
            tryDecrCount(map, chars[i]);
            if (curSize == windowSize) {
                if (isValid(map)) {
                    r.add(left);
                }
            }
            if (curSize > windowSize) {
                Integer count = map.get(chars[left]);
                if (count != null) {
                    map.put(chars[left], count + 1);
                }
                left++;
                if (isValid(map)) {
                    r.add(left);
                }
            }
        }
        return r;
    }

    private void tryDecrCount(Map<Character, Integer> map, Character c) {
        Integer count = map.get(c);
        if (count != null) {
            map.put(c, count - 1);
        }
    }

    private boolean isValid(Map<Character, Integer> map) {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0) {
                return false;
            }
        }
        return true;
    }


// ================== 还是错误 ============================

    /**
     * TODO 错误
     * TODO 这种替换的方式不对， s='ababab' p='abc' 替换后 s='UUUUUU' p='UU'很显然 正确结果是空，而这种方式计算出来结果有很多
     * 技巧，脑筋急转弯
     * 吧s中所有字符属于p的 替换成一个大写唯一的U，如果不属于p的，替换成D
     * 然后原来的s字符串就变成了U和D组合成的新字符串比如 UUUDDUUDU 这样
     * 然后用D切分成若干个子数组， UUU, UU, U ，依次判断每个数组与p的长度关系，
     * 如果 u.length - p.length >= 0 ? u.length - p.length +1  : 0
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        char[] sChars = s.toCharArray();
        for (int i = 0; i < sChars.length; i++) {
            if (p.indexOf(sChars[i]) >= 0) {
                sChars[i] = 'U';
            } else {
                sChars[i] = 'D';
            }
        }
        char[] chars = p.toCharArray();
        Arrays.fill(chars, 'U');
        p = new String(chars);
        int l = p.length();
        List<Integer> r = new ArrayList<>();
        String s1 = new String(sChars);
        for (int i = 0; i < s1.length(); i++) {
            if (i + l - 1 >= s1.length()) {
                break;
            }
            if (s1.substring(i, i + l).equals(p)) {
                r.add(i);
            }
        }
        return r;
    }

    public static void main(String[] args) {
        String[] ds = "UUUDDUUDU".split("D");
        System.out.println(ds.length);

        System.out.println("abc".indexOf("a"));


        List<Integer> anagrams = findAnagrams("cbaebabacd", "abc");

    }
}
