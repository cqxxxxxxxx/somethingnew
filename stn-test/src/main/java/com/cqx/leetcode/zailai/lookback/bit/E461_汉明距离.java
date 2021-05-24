package com.cqx.leetcode.zailai.lookback.bit;

/**
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * <p>
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * <p>
 * 注意：
 * 0 ≤ x, y < 231.
 * <p>
 * 示例:
 * <p>
 * 输入: x = 1, y = 4
 * <p>
 * 输出: 2
 * <p>
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * ↑   ↑
 * <p>
 * 上面的箭头指出了对应二进制位不同的位置。
 * <p>
 * 链接：https://leetcode-cn.com/problems/hamming-distance
 */
public class E461_汉明距离 {

    /**
     * 异或后统计二进制位上为1的数量
     *
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        int i = x ^ y;
        int r = 0;
        for (int j = 0; j < 32; j++) {
            if ((1 << j & i) == 1 << j) {
                r++;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(1 << 0);
        System.out.println(1 << 1);
    }
}
