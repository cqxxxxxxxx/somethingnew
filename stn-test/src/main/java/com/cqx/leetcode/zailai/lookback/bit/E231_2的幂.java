package com.cqx.leetcode.zailai.lookback.bit;

/**
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
 *  
 * <p>
 * 示例 1：
 * 输入：n = 1
 * 输出：true
 * 解释：20 = 1
 * <p>
 * 链接：https://leetcode-cn.com/problems/power-of-two
 */
public class E231_2的幂 {

    /**
     * 2的幂次 二进制表示 只有一个 1
     * 将n二进制位中最低位的1换成0，然后看结果是否==0,0则是true
     * n & (n - 1)
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
