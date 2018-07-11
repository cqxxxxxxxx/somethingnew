package com.cqx.sort.mergeSort;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

/**
 * Created by BG307435 on 2017/9/29.
 */
public class MergeTask extends RecursiveTask<Integer[]> {

    private Integer[] arrays;
    private int start, end;
    private int length;

    public MergeTask(Integer[] arrays, int start, int end) {
        this.arrays = arrays;
        this.length = end - start;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer[] compute() {
        Integer[] result = null;
        MergeSortUtil<Integer> mergeSortUtil = new MergeSortUtil<>();
        if (length > 100) {
            int mid = length / 2 + start;
            MergeTask task1 = new MergeTask(Arrays.copyOfRange(arrays, start, mid), 0, mid);
            MergeTask task2 = new MergeTask(Arrays.copyOfRange(arrays, mid, end), 0, end - mid);
            invokeAll(task1, task2);
            try {
                Integer[] result1 = task1.get();
                Integer[] result2 = task2.get();
                result = mergeSortUtil.mergeWithExtraStorage(result1, result2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            result = mergeSortUtil.sort(arrays);
        }
        return result;
    }
}
