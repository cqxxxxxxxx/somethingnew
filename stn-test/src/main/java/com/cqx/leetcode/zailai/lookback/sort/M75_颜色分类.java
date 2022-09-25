package com.cqx.leetcode.zailai.lookback.sort;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 *  
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * <p>
 * 链接：https://leetcode-cn.com/problems/sort-colors
 */
public class M75_颜色分类 {

    /**
     * 指针，2次遍历
     *
     * @param nums
     */
    public void sortColors0(int[] nums) {
        int p = 0;
        //第一次遍历把0都移到头部， p代表头部指针位置
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int tmp = nums[i];
                nums[i] = nums[p];
                nums[p] = tmp;
                p++;
            }
        }
        for (int i = p; i < nums.length; i++) {
            if (nums[i] == 1) {
                int tmp = nums[i];
                nums[i] = nums[p];
                nums[p] = tmp;
                p++;
            }
        }
    }

//============================= 快排 =======================

    /**
     * 要求原地排序，那就快排 时间复杂度NlogN
     * 归并排序要N的额外空间
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public static void quickSort(int[] array, int begin, int end) {
        if (begin >= end ) {
            return;
        }
        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot - 1);
        quickSort(array, pivot + 1, end);
    }

    private static int partition(int[] array, int begin, int end) {
        int pivot = end;
        int counter = begin;
        for (int i = begin + 1; i < end; i++) {
            if (array[i] < array[pivot]) {
                int tmp = array[counter];
                array[counter] = array[i];
                array[i] = tmp;
                counter++;
            }
        }
        int tmp = array[counter];
        array[counter] = array[pivot];
        array[pivot] = tmp;
        return counter;
    }


    public static void main(String[] args) {
        int[] a = new int[]{2, 0, 2, 1, 1, 0};
//        int[] a = new int[]{1,2,0};
        M75_颜色分类 o = new M75_颜色分类();
        o.sortColors(a);
    }
}
