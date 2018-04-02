package com.cqx.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by cqx on 2018/3/26.
 */
public class DAY3 {

    /**
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * <p>
     * The matching should cover the entire input string (not partial).
     * <p>
     * The function prototype should be:
     * bool isMatch(const char *s, const char *p)
     * <p>
     * Some examples:
     * isMatch("aa","a") → false
     * isMatch("aa","aa") → true
     * isMatch("aaa","aa") → false
     * isMatch("aa", "*") → true
     * isMatch("aa", "a*") → true
     * isMatch("ab", "?*") → true
     * isMatch("aab", "c*a*b") → false
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (s.equals(p)) {
            return true;
        }
        int targetIndex = 0;
        char[] chars = new char[1024];
        char[] pArray = p.toCharArray();
        for (int i = 0; i < pArray.length; i++) {
            if (pArray[i] == '?') {
                char[] r1 = "([\\w\\W])".toCharArray();
                System.arraycopy(r1, 0, chars, targetIndex, r1.length);
                targetIndex += 8;
                continue;
            }
            if (pArray[i] == '*') {
                char[] r1 = "([\\w\\W]*)".toCharArray();
                System.arraycopy(r1, 0, chars, targetIndex, r1.length);
                targetIndex += 9;
                continue;
            }
            chars[targetIndex++] = pArray[i];
        }
        Pattern pattern = Pattern.compile(new String(Arrays.copyOf(chars, targetIndex)));
        return pattern.matcher(s).find();
    }


    /**
     * https://leetcode.com/problems/replace-words/description/
     * trie tree https://blog.csdn.net/jijianshuai/article/details/72455736
     * @param dict
     * @param sentence
     * @return
     */
    public String replaceWords(List<String> dict, String sentence) {
        String[] tokens = sentence.split(" ");
        TrieNode trie = buildTrie(dict);
        return replaceWords(tokens, trie);
    }

    private String replaceWords(String[] tokens, TrieNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String token : tokens) {
            stringBuilder.append(getShortestReplacement(token, root));
            stringBuilder.append(" ");
        }
        return stringBuilder.substring(0, stringBuilder.length()-1);
    }

    private String getShortestReplacement(String token, final TrieNode root) {
        TrieNode temp = root;
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : token.toCharArray()) {
            stringBuilder.append(c);
            if (temp.children[c - 'a'] != null) {
                if (temp.children[c - 'a'].isWord) {
                    return stringBuilder.toString();
                }
                temp = temp.children[c - 'a'];
            } else {
                return token;
            }
        }
        return token;
    }

    private TrieNode buildTrie(List<String> dict) {
        TrieNode root = new TrieNode(' ');
        for (String word : dict) {
            TrieNode temp = root;
            for (char c : word.toCharArray()) {
                if (temp.children[c - 'a'] == null) {
                    temp.children[c - 'a'] = new TrieNode(c);
                }
                temp = temp.children[c - 'a'];
            }
            temp.isWord = true;
        }
        return root;
    }

    public class TrieNode {
        char val;
        TrieNode[] children;
        boolean isWord;

        public TrieNode(char val) {
            this.val = val;
            this.children = new TrieNode[26];
            this.isWord = false;
        }
    }

    @Test
    public void test1() {
        System.out.println(isMatch("aaa", "a*"));
        Pattern matcher = Pattern.compile("a([\\w\\W]*)");
        boolean a = matcher.matcher("aaa").find();
        System.out.println(a);
    }

    @Test
    public void test2() {
        String[] strings = new String[]{"cat", "bat", "rat"};
        String result = replaceWords(Arrays.asList(strings), "the cattle was rattled by the battery");
        System.out.println(result);
    }
}
