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
    // 统计index右侧比更小值的个数:
    // - 无法使用Stack存储 & 无法先排序全部数据再处理
    // - 利用空间复杂度来降低时间复杂度(原始复杂度O(n*n))
    //
    // 1 2 3 4 5
    //       0 0  O(n) 最差复杂度
    // 5 4 3 2 1
    //       1 0  O(1) 最优复杂度
    public List<Integer> countSmaller(int[] nums) {



        return null;
    }








    // Count of Smaller Numbers After Self
    // Counts array has the property where counts[i] is the number of smaller elements to the right of nums[i]
    // nums = [5,2,6,1] -> counts = [2,1,1,0] 统计每个位置的后面有多少的数字是比当前这个数字小的
    // O(nlog(n)) O(n) 由于归并排序造成的复杂度

    // The smaller numbers on the right of a number are exactly those that jump from its right to its left during a stable sort
    // So I do merge sort with added tracking of those right-to-left jumps
    // 1. 统计对于每一数字，它右边的数字移动到它左边的数目，在merge sort的过程中记录
    // 2. 对于排序(后)的每一个数字，需要找到它原始index位置，以便在指定的位置上记录统计数目，如果数字不重复，则可以使用HashMap<>
    private int[] counts;
    private int[] indexes;

    public int[] countSmaller1(int[] nums) {
        int length = nums.length;
        counts = new int[length];
        indexes = new int[length];
        for (int i = 0; i < length; i++) {
            indexes[i] = i;
        }
        mergesort(nums, 0, length - 1);
        return counts;
    }

    private void mergesort(int[] nums, int start, int end) {
        if (end <= start) return;
        int mid = (start + end) / 2;
        mergesort(nums, start, mid);
        mergesort(nums, mid + 1, end);
        merge(nums, start, end);
    }

    private void merge(int[] nums, int start, int end) {
        int mid = (start + end) / 2;
        int leftIndex = start;
        int rightIndex = mid + 1;
        int countRightToLeft = 0;

        int sortIndex = 0;
        int[] newIndexes = new int[end - start + 1]; // 排序后存储的是index的值
        while (leftIndex <= mid && rightIndex <= end) {
            if (nums[indexes[rightIndex]] < nums[indexes[leftIndex]]) {
                newIndexes[sortIndex] = indexes[rightIndex++];
                countRightToLeft++;                 // 右边有一个，则会累加一个移动的数量
            } else {
                newIndexes[sortIndex] = indexes[leftIndex];
                counts[indexes[leftIndex]] += countRightToLeft; // 找到数字的原始位置，进行统计
                leftIndex++;
            }
            sortIndex++;
        }
        while (leftIndex <= mid) {
            newIndexes[sortIndex++] = indexes[leftIndex++];
            counts[indexes[leftIndex]] += countRightToLeft;
        }
        while (rightIndex <= end) {
            newIndexes[sortIndex++] = indexes[rightIndex++];
        }
        for (int i = start; i <= end; i++) {       // 将排序好的数字，设置到indexes[i]原始标识数组
            indexes[i] = newIndexes[i - start];
        }
    }


    // 使用自定义类型Pair，记录index-> value的对应关系
    public int[] countSmaller2(int[] nums) {
        NumPair[] arr = new NumPair[nums.length];
        int[] smaller = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new NumPair(i, nums[i]);
        }
        mergeSort(arr, smaller);
        return smaller;
    }

    private NumPair[] mergeSort(NumPair[] arr, int[] smaller) {
        if (arr.length <= 1) {
            return arr;
        }
        int pivot = arr.length / 2;
        NumPair[] left = mergeSort(Arrays.copyOfRange(arr, 0, pivot), smaller);
        NumPair[] right = mergeSort(Arrays.copyOfRange(arr, pivot, arr.length), smaller);

        for (int i = 0, j = 0; i < left.length || j < right.length; ) {
            if (i < left.length && j < right.length && left[i].val > right[j].val) {
                arr[i + j] = left[i];
                smaller[left[i].index] += j;
                i++;
            } else {
                arr[i + j] = right[j];
                j++;
            }
        }
        return arr;
    }

    class NumPair {

        public int index;
        public int val;

        public NumPair(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }
}
