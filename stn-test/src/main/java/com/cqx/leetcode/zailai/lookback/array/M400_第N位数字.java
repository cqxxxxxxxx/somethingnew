package com.cqx.leetcode.zailai.lookback.array;

/**
 * https://leetcode-cn.com/problems/nth-digit/
 */
public class M400_第N位数字 {

    /**
     * 解法 https://leetcode-cn.com/problems/nth-digit/solution/xiang-jie-zhao-gui-lu-by-z1m/
     * 找规律
     *
     * @param n
     * @return
     */
    public int findNthDigit(int n) {

        long num=n;

        long size=1;
        long max=9;

        while(n>0){
            //判断不在当前位数内
            if(num-max*size>0){
                num=num-max*size;
                size++;
                max=max*10;
            }else{
                long count=num/size;
                long left=num%size;
                if(left==0){
                    return (int) (((long)Math.pow(10, size-1)+count-1)%10);
                }else{
                    return (int) (((long)Math.pow(10, size-1)+count)/((long)Math.pow(10, (size-left)))%10);
                }
            }
        }

        return 0;
    }


}
