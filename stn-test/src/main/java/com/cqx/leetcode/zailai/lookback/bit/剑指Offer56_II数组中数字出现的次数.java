package com.cqx.leetcode.zailai.lookback.bit;

import java.util.HashMap;
import java.util.Map;

/**
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,3,3]
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof
 */
public class 剑指Offer56_II数组中数字出现的次数 {

    /**
     * 1 hashmap
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return 0;
    }


    /**
     * 统计所有数字在某一位置的和能不能被3整除，如果不能被3整除，说明那个只出现一次的数字的二进制在那个位置是1
     * @param nums
     * @return
     */
    public int singleNumber1(int[] nums) {
        //最终的结果值
        int res = 0;
        //int类型有32位，统计每一位1的个数
        for (int i = 0; i < 32; i++) {
            //统计第i位中1的个数
            int oneCount = 0;
            for (int j = 0; j < nums.length; j++) {
                oneCount += (nums[j] >>> i) & 1;
            }
            //如果1的个数不是3的倍数，说明那个只出现一次的数字
            //的二进制位中在这一位是1
            if (oneCount % 3 == 1)
                res |= 1 << i;
        }
        return res;
    }

}
