package templates.sorting.merge_sort;

import java.util.Arrays;
import java.util.List;

public class MergeSortingQuestion {

    // Count of Smaller Numbers After Self
    // Given an integer array nums, return an integer array counts
    // where counts[i] is the number of smaller elements to the right of nums[i].
    //
    // nums = [5,2,6,1] -> [2,1,1,0]
    // nums = [-1,-1] -> [0,0]
    // nums = [-1] -> [0]
    //
    // - 无法使用Stack存储 & 无法先排序全部数据再处理
    // - 利用空间复杂度来降低时间复杂度(原始复杂度O(n*n))
    public List<Integer> countSmaller(int[] nums) {

        // val: 5 2 6 1
        // idx: 0 1 2 3
        //
        //

        return null;
    }


    // 使用自定义类型Pair，记录index-> value的对应关系
    public int[] countSmaller2(int[] nums) {
        int[] result = new int[nums.length];
        NumPair[] arr = new NumPair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new NumPair(i, nums[i]);
        }

        mergeSort(arr, result);
        return result;
    }

    private void mergeSort(NumPair[] arr, int[] result) {
        if (arr.length > 1) {
            int pivot = arr.length / 2;
            NumPair[] left = Arrays.copyOfRange(arr, 0, pivot);
            NumPair[] right = Arrays.copyOfRange(arr, pivot, arr.length);
            mergeSort(left, result);
            mergeSort(right, result);

            for (int i = 0, j = 0; i < left.length || j < right.length; ) {
                if (i < left.length && j < right.length && left[i].val > right[j].val) {
                    arr[i + j] = left[i];
                    result[left[i].index] += j;
                    i++;
                } else {
                    arr[i + j] = right[j];
                    j++;
                }
            }
        }
    }

    static class NumPair {
        public int index;
        public int val;

        public NumPair(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }
}
