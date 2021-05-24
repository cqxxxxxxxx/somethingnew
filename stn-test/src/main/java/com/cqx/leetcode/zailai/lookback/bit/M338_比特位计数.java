package com.cqx.leetcode.zailai.lookback.bit;

/**
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * <p>
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 * <p>
 * 示例 2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * <p>
 * 进阶:
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 * <p>
 * 链接：https://leetcode-cn.com/problems/counting-bits
 */
public class M338_比特位计数 {


    /**
     * 规律
     * 奇数的1个数为前一个偶数+1 （因为末尾是1，偶数末尾是0）
     * 偶数的1个数跟除以2后的偶数 个数一样， 因为 偶数末尾是0，除以2，相当于右移1位，末尾是0，所以右移不变
     * <p>
     * dp[i] 值为i时候 比特位为1的数量
     * i为奇数 dp[i] = dp[i-1] + 1  (i-1即为偶数)
     * i为偶数 dp[i] = dp[i/2]
     *
     * @param num
     * @return
     */
    public int[] countBits0(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 1; i <= num; i++) {
            //奇数
            if (i % 2 == 1) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = dp[i / 2];
            }
        }
        return dp;
    }


//=================暴力 不好===================+

    /**
     * 暴力
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] r = new int[num];
        for (int i = 0; i < num; i++) {
            r[i] = count(i);
        }
        return r;
    }

    private int count(int val) {
        int c = 0;
        for (int i = 0; i < 32; i++) {
            int tmp = 1 << i;
            if ((tmp & val) == tmp) {
                c++;
            }
            if (tmp > val) {
                break;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        M338_比特位计数 a = new M338_比特位计数();
        System.out.println(a.count(2));
        System.out.println(a.count(1));
    }
}
