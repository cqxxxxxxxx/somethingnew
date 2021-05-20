package com.cqx.leetcode.zailai.lookback.bit;

/**
 * https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/
 */
public class M1442_形成两个异或相等数组的三元组数目 {

    /**
     * a ^ b = 0
     * a = arr[i] ^ arr[i+1] ... ^ arr[j-1]
     * b = arr[j] ^ arr[j+1] ... ^ arr[k]
     * 那么 arr[i] ^ arr[i+1] ... ^ arr[j-1] ^ arr[j] ^ arr[j+1] ... ^ arr[k] = 0
     * 即只要找到连续的部分 异或为0就好了,而且由于条件题目限制，长度至少为2 i<j<=k
     * 如果这样的数组元素有 n 个 那么有n-1个组合方式
     * @param arr
     * @return
     */
    public int solution2 (int[] arr) {
        //所有可能的组合
        int total = 0;
        int length = arr.length;
        //判断数组从i到j的元素异或结果是否是0
        for (int i = 0; i < length - 1; i++) {
            int xor = arr[i];
            for (int j = i + 1; j < length; j++) {
                xor ^= arr[j];
                //如果数组从i到j的异或结果是0，那么他们
                //可能的组合就是j-i
                if (xor == 0) {
                    total += (j - i);
                }
            }
        }
        return total;
    }
    /**
     * 1  暴力
     *
     * @param arr
     * @return
     */
    public int countTriplets(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int valLeft = 0, valRight = 0;
                for (int z = i; z < j; z++) {
                    valLeft = valLeft ^ arr[z];
                }
                for (int k = j; k < arr.length; k++) {
                    for (int z = j; z <= k; z++) {
                        valRight = valRight ^ arr[z];
                        if (valLeft == valRight) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(2 ^ 5 ^ 6);
        System.out.println(1 ^ 2 ^ 5 ^ 6);
        System.out.println(1 ^ 2);
        System.out.println(5 ^ 6);
    }
}
