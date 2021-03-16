package com.cqx.leetcode.zailai.lookback.greedyAlgorithm;


import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/task-scheduler/
 */
public class M621_任务调度器 {

    /**
     * 填桶
     * https://leetcode-cn.com/problems/task-scheduler/solution/tian-tong-si-lu-you-tu-kan-wan-jiu-dong-by-mei-jia/
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> taskMap = new HashMap<>();
        int max = 0;
        for (char task : tasks) {
            Integer count = taskMap.get(task);
            if (count == null) {
                count = 1;
            } else {
                count = count + 1;
            }
            taskMap.put(task, count + 1);
            max = Math.max(count, max);
        }
        int cpuTime = (max - 1) * (n + 1) + 1;
        int maxCount = 0;
        for (Map.Entry<Character, Integer> entry : taskMap.entrySet()) {
            if (entry.getValue() == max) {
                maxCount++;
            }
        }
        cpuTime = cpuTime + maxCount - 1;
        return Math.max(cpuTime, tasks.length);
    }
}
