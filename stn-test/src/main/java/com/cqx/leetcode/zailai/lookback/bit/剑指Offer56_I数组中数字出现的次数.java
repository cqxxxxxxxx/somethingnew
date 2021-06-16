package com.cqx.leetcode.zailai.lookback.bit;

/**
 * 与 或 非 异或
 * &  |  ^ ^=
 * <p>
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * <p>
 * 示例 1：
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 * <p>
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
 */
public class 剑指Offer56_I数组中数字出现的次数 {

    /**
     * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/solution/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-by-leetcode/
     * 异或运算
     * 一样异或为0 ，不一样为1
     *
     * 除了一个数字外，其他数字都出现了2次，那么如何找这个数字？ => 全体异或一次，结果就是那个数
     * 同理这题，如果可以分组，1.两个只出现一次的数分到两个组，2.相同数字分到一个组 那么结果就呼之欲出了。
     * 全体异或一次，然后随便找一个结果中位为1的位置，以这个位置为标准，划分成两个组
     *
     *
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        int ret = 0;
        for (int n : nums) {
            ret ^= n;
        }
        int div = 1;
        while ((div & ret) == 0) {
            div <<= 1;
        }
        int a = 0, b = 0;
        for (int n : nums) {
            if ((div & n) != 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }
        return new int[]{a, b};
    }
}
