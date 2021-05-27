package com.cqx.leetcode.zailai.lookback.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/course-schedule
 */
public class M207_课程表 {

    /**
     * 思想： 有向图、 拓扑排序、 查看最终有没有环，环的数量
     * [[1,2], [2,3],[2,4]] => 1课程依赖2 2->1这样一个图
     * 入度
     * 1的入度是1
     * 2的入度是2
     * 3的入度是0
     * 4的入度是0
     * <p>
     * 邻接表
     * 1->2
     * 2->3->4
     * 3->null
     * 4->null
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //0. 构建邻接表
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        //1. 构建入度数组
        int[] indegrees = new int[numCourses];
        for (int[] cp : prerequisites) {
            //入度++
            indegrees[cp[0]]++;
            //链接表
            adjacency.get(cp[1]).add(cp[0]);
        }
        //2.BFS遍历，先把入度为0的塞到queue中
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Integer pre = q.poll();
                numCourses--;
                //把该删除的节点邻接表拿出来，然后把里面的元素的入度-1
                for (Integer next : adjacency.get(pre)) {
                    indegrees[next] -= 1;
                    //如果某个元素入度-1后变0了，那么可以放到queue里面
                    if (indegrees[next] == 0) {
                        q.add(next);
                    }
                }
            }
        }
        return numCourses == 0;
    }
}
