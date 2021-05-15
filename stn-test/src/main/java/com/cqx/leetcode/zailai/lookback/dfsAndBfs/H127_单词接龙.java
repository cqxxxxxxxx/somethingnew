package com.cqx.leetcode.zailai.lookback.dfsAndBfs;

import java.util.*;

/**
 *https://leetcode-cn.com/problems/word-ladder/
 */
public class H127_单词接龙 {

    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        final HashSet<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(beginWord) || !wordSet.contains(endWord)) {
            return 0;
        }
        wordSet.remove(beginWord);
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int step = 1;
        while (!queue.isEmpty()) {
            final int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                final String word = queue.poll();
                final char[] wordChars = word.toCharArray();
                //尝试修改每个字符
                for (int j = 0; j < wordChars.length; j++) {
                    // 一轮以后应该重置，否则结果不正确
                    char originChar = wordChars[j];
                    for (char k = 'a'; k <= 'z'; k++) {
                        wordChars[j] = k;
                        String nextWord = String.valueOf(wordChars);
                        if (wordSet.contains(nextWord)) {
                            if (endWord.equals(nextWord)) {
                                return step + 1;
                            }
                            if (!visited.contains(nextWord)) {
                                visited.add(nextWord);
                                queue.add(nextWord);
                            }
                        }
                    }
                    wordChars[j] = originChar;
                }
            }
            step++;
        }
        return step;
    }

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
}
