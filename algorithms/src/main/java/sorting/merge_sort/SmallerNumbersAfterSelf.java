package sorting.merge_sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO. 归并排序进阶案例: 在归并排序过程中统计“右侧被甩到左侧的”更小数据
public class SmallerNumbersAfterSelf {

    // Count of Smaller Numbers After Self
    // Given an integer array nums, return an integer array counts
    // where counts[i] is the number of smaller elements to the right of nums[i].
    //
    // nums = [5,2,6,1] -> [2,1,1,0]
    // nums = [-1,-1] -> [0,0]
    // nums = [-1] -> [0]
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>(Collections.nCopies(n, 0));
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        mergeSort(nums, indices, result, 0, n - 1);
        return result;
    }

    private void mergeSort(int[] nums, int[] indices, List<Integer> result, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, indices, result, left, mid);
        mergeSort(nums, indices, result, mid + 1, right);

        merge(nums, indices, result, left, mid, right);
    }

    private void merge(int[] nums, int[] indices, List<Integer> result, int left, int mid, int right) {
        int[] tempIndices = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        int count = 0;

        // Merging two sorted halves and counting smaller elements
        while (i <= mid && j <= right) {
            if (nums[indices[i]] <= nums[indices[j]]) {
                result.set(indices[i], result.get(indices[i]) + count);
                tempIndices[k] = indices[i];
                i++;
            } else {
                count++;
                tempIndices[k] = indices[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements from left half
        while (i <= mid) {
            result.set(indices[i], result.get(indices[i]) + count);
            tempIndices[k] = indices[i];
            i++;
            k++;
        }

        // Copy remaining elements from right half
        while (j <= right) {
            tempIndices[k] = indices[j];
            j++;
            k++;
        }

        // Copy the sorted elements back to the original array
        System.arraycopy(tempIndices, 0, indices, left, tempIndices.length);
    }
}
