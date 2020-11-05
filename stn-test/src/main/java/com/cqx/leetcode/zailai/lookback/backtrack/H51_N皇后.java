package com.cqx.leetcode.zailai.lookback.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class H51_N皇后 {
    /**
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * <p>
     * <p>
     * <p>
     * 上图为 8 皇后问题的一种解法。
     * <p>
     * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
     * <p>
     * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     */


    /**
     * 51. N皇后
     *
     * @param n
     * @return
     */
    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        boolean[] visited = new boolean[n];
        //2*n-1个斜对角线
        boolean[] dia1 = new boolean[2 * n - 1];
        boolean[] dia2 = new boolean[2 * n - 1];

        fun(n, new ArrayList<String>(), visited, dia1, dia2, 0);

        return result;
    }

    private void fun(int n, List<String> list, boolean[] visited, boolean[] dia1, boolean[] dia2, int rowIndex) {
        if (rowIndex == n) {
            result.add(new ArrayList<String>(list));
            return;
        }

        for (int i = 0; i < n; i++) {
            //这一行、正对角线、反对角线都不能再放了，如果发现是true，停止本次循环
            if (visited[i] || dia1[rowIndex + i] || dia2[rowIndex - i + n - 1])
                continue;

            //init一个长度为n的一维数组，里面初始化为'.' 把这一行添加进list
            char[] charArray = new char[n];
            Arrays.fill(charArray, '.');
            charArray[i] = 'Q';
            String stringArray = new String(charArray);
            list.add(stringArray);

            //添加访问过的列，左右对角线
            visited[i] = true;
            dia1[rowIndex + i] = true;
            dia2[n - i + rowIndex - 1] = true;

            //递归下一行
            fun(n, list, visited, dia1, dia2, rowIndex + 1);

            //不行则回溯掉
            //reset 不影响回溯的下个目标
            list.remove(list.size() - 1);
            charArray[i] = '.';
            visited[i] = false;
            dia1[rowIndex + i] = false;
            dia2[rowIndex - i + n - 1] = false;
        }
    }
}
