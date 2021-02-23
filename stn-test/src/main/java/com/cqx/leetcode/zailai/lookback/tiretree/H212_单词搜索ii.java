package com.cqx.leetcode.zailai.lookback.tiretree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/word-search-ii/
 */
public class H212_单词搜索ii {

    /**
     * 字典树 + 回溯算法 +二维数组的DFS（类似海岛题目DFS）
     *
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        final TrieNode root = buildTree(words);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(board, root, i, j, result);
            }
        }
        return result;
    }

    private void dfs(char[][] board, TrieNode node, int i, int j, List<String> result) {
        //terminator
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return;
        }
        char c = board[i][j];
        if (c == '#' || node.next[c - 'a'] == null) {
            return;
        }

        TrieNode nextNode = node.next[c - 'a'];
        //process
        if (nextNode.word != null) {
            result.add(nextNode.word);
            nextNode.word = null; //避免第二次加入
        }

        //drill down
        char tmp = board[i][j];
        board[i][j] = '#';
        dfs(board, nextNode, i + 1, j, result);
        dfs(board, nextNode, i, j + 1, result);
        dfs(board, nextNode, i - 1, j, result);
        dfs(board, nextNode, i, j - 1, result);
        board[i][j] = tmp;
    }

    private TrieNode buildTree(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode p = root;
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) {
                    p.next[i] = new TrieNode();
                }
                p = p.next[i];
            }
            p.word = word;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }
}
