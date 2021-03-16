package com.cqx.leetcode.zailai.lookback.recursion;

/**
 * https://leetcode-cn.com/problems/word-search/
 */
public class M79_单词搜索 {

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i <= board.length - 1; i++) {
            for (int j = 0; j <= board[0].length - 1; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (recursion(board, i, j, word, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean recursion(char[][] board, int i, int j, String word, int index) {
        if (i < 0 || i > board.length - 1
                || j < 0 || j > board[0].length - 1
                || board[i][j] == '#') {
            return false;
        }
        if (board[i][j] == word.charAt(index)) {
            if (index == word.length() - 1) {
                return true;
            }
            board[i][j] = '#';
            //dfs
            if (recursion(board, i + 1, j, word, index + 1)
                    || recursion(board, i, j + 1, word, index + 1)
                    || recursion(board, i - 1, j, word, index + 1)
                    || recursion(board, i, j - 1, word, index + 1)
            ) {
                return true;
            }
            board[i][j] = word.charAt(index);
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] chars = {
                {'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}
        };
        String word = "SEE";
        M79_单词搜索 app = new M79_单词搜索();
        boolean exist = app.exist(chars, word);
        return;
    }
}
