package com.cqx.leetcode.zailai.lookback.sort;

/**
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 */
public class H4_寻找两个正序数组的中位数 {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merge = merge(nums1, nums2);
        //取余
        if (merge.length % 2 == 0) {
            return (merge[merge.length / 2 - 1] + merge[merge.length / 2]) / 2d;
        }
        return merge[merge.length / 2];
    }

    private static int[] merge(int[] nums1, int[] nums2) {
        int[] tmp = new int[nums1.length + nums2.length];
        int r1 = nums1.length - 1, r2 = nums2.length - 1;
        int i = 0, j = 0, k = 0;
        while (i <= r1 && j <= r2) {
            tmp[k++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }
        while (i <= r1) {
            tmp[k++] = nums1[i++];
        }
        while (j <= r2) {
            tmp[k++] = nums2[j++];
        }
        return tmp;
    }

    /**
     *  作者：powcai
     *     链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/shuang-zhi-zhen-by-powcai/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1>n2)
            return findMedianSortedArrays1(nums2, nums1);
        int k = (n1 + n2 + 1)/2;
        int left = 0;
        int right = n1;
        while(left < right){
            int m1 = left +(right - left)/2;
            int m2 = k - m1;
            if (nums1[m1] < nums2[m2-1])
                left = m1 + 1;
            else
                right = m1;
        }
        int m1 = left;
        int m2 = k - left;
        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1-1],
                m2 <= 0 ? Integer.MIN_VALUE : nums2[m2-1]);
        if ((n1 + n2) % 2 == 1)
            return c1;
        int c2 = Math.min( m1 >= n1 ? Integer.MAX_VALUE :nums1[m1],
                m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);
        return (c1 + c2) * 0.5;

    }



    public static void main(String[] args) {
        System.out.println(3/2);
        System.out.println(3D/2);
        findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
    }
}
