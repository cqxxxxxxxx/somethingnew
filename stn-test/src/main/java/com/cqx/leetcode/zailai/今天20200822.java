package com.cqx.leetcode.zailai;

import java.util.*;

import static java.lang.Math.max;

public class 今天20200822 {


    Set<Boolean> col = new HashSet<Boolean>(); //哪些列被占用
    Set<Boolean> diag1; //哪些左斜行被占用
    Set<Boolean> diag2;//哪些右斜行被占用

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


    //  ==================
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 102. 二叉树的层序遍历
     * 广度优先搜索
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> innerList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                final TreeNode node = queue.pop();
                innerList.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(innerList);
        }
        return result;
    }


    /**
     * 515. 在每个树行中找最大值
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                final TreeNode pop = queue.pop();
                max = max(max, pop.val);
                if (pop.left != null) {
                    queue.add(pop.left);
                }
                if (pop.right != null) {
                    queue.add(pop.right);
                }
            }
            result.add(max);
        }
        return result;
    }

//===================

    /**
     * 200. 岛屿数量
     * 二维网格的深度 广度优先遍历
     * [1 0 0 0]
     * [0 1 1 0]
     * [0 0 0 1]
     *
     * @param grid
     * @return
     */
    class Solution {
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int nr = grid.length; //行数
            int nc = grid[0].length;    //列数
            int num_islands = 0;
            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    if (grid[r][c] == '1') {
                        ++num_islands;
                        dfs(grid, r, c);
                    }
                }
            }

            return num_islands;
        }

        /**
         * 深度优先遍历 depth first search
         * 广度优先遍历 breadth first search
         *
         * @param grid
         * @param r
         * @param c
         */
        void dfs(char[][] grid, int r, int c) {
            int nr = grid.length;
            int nc = grid[0].length;

            //超出边界 || 遇到水 返回
            if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
                return;
            }

            //把岛屿沉没
            grid[r][c] = '0';

            //上下左右查找相邻的岛屿
            dfs(grid, r - 1, c);
            dfs(grid, r + 1, c);
            dfs(grid, r, c - 1);
            dfs(grid, r, c + 1);
        }
    }


    /**
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        wordSet.remove(beginWord);

        // 图的广度优先遍历，必须使用的队列和表示是否访问过的 visited （数组，哈希表）
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int wordLen = beginWord.length();
        // 包含起点，因此初始化的时候步数为 1
        int step = 1;
        while (!queue.isEmpty()) {

            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                // 依次遍历当前队列中的单词
                String word = queue.poll();
                char[] charArray = word.toCharArray();

                // 修改每一个字符
                for (int j = 0; j < wordLen; j++) {
                    // 一轮以后应该重置，否则结果不正确
                    char originChar = charArray[j];

                    for (char k = 'a'; k <= 'z'; k++) {
                        if (k == originChar) {
                            continue;
                        }

                        charArray[j] = k;
                        String nextWord = String.valueOf(charArray);

                        if (wordSet.contains(nextWord)) {
                            if (nextWord.equals(endWord)) {
                                return step + 1;
                            }

                            if (!visited.contains(nextWord)) {
                                queue.add(nextWord);
                                // 注意：添加到队列以后，必须马上标记为已经访问
                                visited.add(nextWord);
                            }
                        }
                    }
                    // 恢复
                    charArray[j] = originChar;
                }
            }
            step++;
        }
        return 0;
    }


    /**
     * 121. 买卖股票的最佳时机
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 0) {
            return 0;
        }
        int maxProfit = 0; //最大利润
        int curDiff = 0;

        int curNum = prices[0]; // 保存遍历过程中，最小的元素
        for (int index = 1; index < prices.length; index++) {   // 这个循环就是 贪心算法 的核心
            curDiff = prices[index] - curNum;
            if (curDiff > 0) {  // curNum < prices[index]
                maxProfit = max(maxProfit, curDiff);
            } else {    // curNum >= prices[index]
                curNum = prices[index];
            }
        }

        return maxProfit;
    }


    /**
     * 55. 跳跃游戏
     * 后往前贪心
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums == null) {
            return false;
        }
        int lastPosition = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            // 逐步向前递推
            if (nums[i] + i >= lastPosition) {
                lastPosition = i;
            }
        }
        return lastPosition == 0;
    }


    public boolean canJump1(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > k) return false;
            k = max(k, i + nums[i]);
        }
        return true;
    }


//========================= 柠檬水找零钱

    /**
     * 860. 柠檬水找零
     *
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> changeCountMap = new HashMap<Integer, Integer>() {{
            put(5, 0);
            put(10, 0);
        }};
        int remain = 0;
        for (int i = 0; i < bills.length; i++) {
            Integer fiveCount = changeCountMap.get(5);
            if (bills[i] == 5) {
                changeCountMap.put(5, fiveCount + 1);
            } else if (bills[i] == 10) {
                if (fiveCount < 1) {
                    return false;
                }
                changeCountMap.put(5, fiveCount - 1);
                changeCountMap.put(10, changeCountMap.get(10) + 1);
            } else if (bills[i] == 20) {
                Integer tenCount = changeCountMap.get(10);
                if (tenCount > 0 && fiveCount >= 1) {
                    changeCountMap.put(10, tenCount - 1);
                    changeCountMap.put(5, fiveCount - 1);
                } else if (fiveCount >= 3) {
                    changeCountMap.put(5, fiveCount - 3);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    //精简
    public boolean lemonadeChange2(int[] bills) {
        int fiveCount = 0;
        int tenCount = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                fiveCount++;
                continue;
            }

            if (bills[i] == 10) {
                if (fiveCount < 1) {
                    return false;
                }
                fiveCount--;
                tenCount++;
                continue;
            }

            if (bills[i] == 20) {
                if (tenCount > 0 && fiveCount >= 1) {
                    tenCount--;
                    fiveCount--;
                } else if (fiveCount >= 3) {
                    fiveCount -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

//============ 求平方根

    public int mySqrt(int x) {
        // 注意：针对特殊测试用例，例如 2147395599
        // 要把搜索的范围设置成长整型
        // 为了照顾到 0 把左边界设置为 0
        long left = 0;
        // # 为了照顾到 1 把右边界设置为 x / 2 + 1
        long right = x / 2 + 1;
        while (left < right) {
            // 注意：这里一定取右中位数，如果取左中位数，代码会进入死循环
            // long mid = left + (right - left + 1) / 2;
            long mid = (left + right + 1) >>> 1;
            long square = mid * mid;
            if (square > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        // 因为一定存在，因此无需后处理
        return (int) left;
    }


    /**
     * 367. 有效的完全平方数
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        int left = 1, right = num / 2;
        while (left <= right) {
            //避免int相加超过最大值
            int mid = left + ((right - left) >> 1);
            if ((long) mid * mid > num) {
                right = mid - 1;
            } else if ((long) mid * mid < num) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }


//  ================= 33. 搜索旋转排序数组

    public int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            // 当[0,mid]有序时,向后规约条件
            if (nums[0] <= nums[mid] && (target > nums[mid] || target < nums[0])) {
                lo = mid + 1;
                // 当[0,mid]发生旋转时，向后规约条件
            } else if (target > nums[mid] && target < nums[0]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo == hi && nums[lo] == target ? lo : -1;
    }


    public static void main(String[] args) {
        final int maxValue = Integer.MAX_VALUE;
        System.out.println(maxValue + maxValue);
        System.out.println(maxValue);
        System.out.println(maxValue + 1);
    }
}
