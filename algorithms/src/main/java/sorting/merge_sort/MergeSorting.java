package sorting.merge_sort;

import java.util.Arrays;

public class MergeSorting {

    public void mergeSort(int[] input) {
        if (input.length > 1) {
            int middle = input.length / 2;
            int[] leftList = Arrays.copyOfRange(input, 0, middle);
            int[] rightList =Arrays.copyOfRange(input, middle, input.length);

            // 对左右差分的数组进行归并(递归)排序
            mergeSort(leftList);
            mergeSort(rightList);

            // 在合并有序数组时进行排序和位置移动
            mergeSortedArray(input, leftList, rightList);
        }
    }

    // TODO: 有序数组合并的"标准解法"，三个index坐标移动
    public void mergeSortedArray(int[] result, int[] left, int[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                result[resultIndex++] = left[leftIndex++];
            } else {
                result[resultIndex++] = right[rightIndex++];
            }
        }
        while (leftIndex < left.length) {
            result[resultIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
        }
    }
}
