package arrays.sorting_questions;

import java.util.Arrays;

public class MinDiffElements {

    // Find minimum difference between any two elements (pair)
    // Given an array of integers, find minimum difference of two elements in array
    //
    // [2, 5, 9, 4, 10] -> 1
    //
    // O(n*log(n)) 只有在排序之后，才能通过一次遍历迅速的找到结果
    // O(1)
    public int minDiffElements(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);

        int minDiff = Integer.MAX_VALUE;
        for (int index = 0; index < nums.length - 1; index++) {
            int diff = nums[index + 1] - nums[index];
            minDiff = Math.min(diff, minDiff);
        }
        return minDiff;
    }
}
