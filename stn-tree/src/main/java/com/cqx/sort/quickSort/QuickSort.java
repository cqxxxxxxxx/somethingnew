package com.cqx.sort.quickSort;

/**
 * Created by cqx on 2018/6/9.
 */
public class QuickSort {

    public void sort(int[] array, int left, int right) {
        int ref = array[left];
        int uLeft = left + 1;
        int uRight = right;
        while (uLeft != uRight) {
            while (array[uRight] > ref && uRight > uLeft) {
                uRight--;
            }
            while (array[uLeft] < ref && uRight > uLeft) {
                uLeft++;
            }

            if (uLeft < uRight) {
                int tmpLeft = array[uLeft];
                array[uLeft] = array[uRight];
                array[uRight] = tmpLeft;
            }
        }
        array[left] = array[uLeft];
        array[uLeft] = ref;
        sort(array, left, uLeft - 1);
        sort(array, uLeft + 1, right);
    }


    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
        /**
         * 0123456789
         * PAYPALISHIRING
         * P   A   H   N
         * A P L S I I G
         * Y   I   R
         *
         */

//        int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1, 8 };
//        QuickSort quickSort = new QuickSort();
//        quickSort.sort(a, 0, a.length - 1);
//        Arrays.stream(a).forEach(System.out::println);
    }

    public static String convert(String s, int numRows) {
        int mid = numRows - 2;
        char[] ss = s.toCharArray();
        String r = "";
        for (int i = 0; i <= numRows; i++) {
            int skip = 2 * (numRows - i - 1);
            if (skip > 0) {
                for (int j = 0; i + j * skip < ss.length; j++) {
                    if (i + j * skip > ss.length) {
                        break;
                    }
                    r = r + ss[i + j * skip];
                }
            }
            if (skip == 0) {
                skip = 2 * numRows - 2;
                for (int j = 0; i + j * skip < ss.length; j++) {
                    if (i + j * skip > ss.length) {
                        break;
                    }
                    r = r + ss[i + j * skip];
                }
            }
        }
        return r;
    }
}
