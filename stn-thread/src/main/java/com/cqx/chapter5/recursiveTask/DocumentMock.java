package com.cqx.chapter5.recursiveTask;

import java.util.Random;

/**
 * Created by cqx on 2017/2/24.
 */
public class DocumentMock {

    private String[] words = {"the", "hello", "goodbye", "packt", "java", "thread", "pool", "random", "class", "main"};

    /**
     * 根据words 随即生成模拟的document 并打印出目标单词出现的次数
     * @param numLines   行数
     * @param numWords  一行中单词数
     * @param word      查找的目标单词
     * @return
     */
    public String[][] generateDocument(int numLines, int numWords, String word){
        int counter = 0;
        String document[][] = new String[numLines][numWords];
        Random random = new Random();
        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < numWords; j++) {
                int index = random.nextInt(words.length);
                document[i][j] = words[index];
                if (document[i][j].equals(word)){   //如果生成的单词跟目标的一样 则计数器++
                    counter ++;
                }
            }
        }
        System.out.printf("DocumentMock: The word appears %d times in the document", counter);
        return document;
    }
}
