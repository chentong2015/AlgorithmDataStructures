package leetcode3;

import java.util.Arrays;

// Maximize Greatness of an Array
// You are given a 0-indexed integer array nums.
// You are allowed to permute nums into a new array perm of your choosing.
// We define the greatness of nums be the number of indices 0 <= i < nums.length
// for which perm[i] > nums[i].
// Return the maximum possible greatness you can achieve after permuting nums
//
// 1 <= nums.length <= 10^5
// 0 <= nums[i] <= 10^9
public class MaxGreatnessArray {

    // TODO. Sorting + Two Indexes 排序加双指针的判断
    // [1,3,5,2,1,3,1] -> 4
    // [2,5,1,3,3,1,1]
    //
    // [1,2,3,4] -> 3
    // [2,3,4,1]
    //
    // 1 1 1 2 3 3 5
    // 2 3 3 5
    //
    // O(N * logN + N)
    // O(1)
    public int maximizeGreatness(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            if (nums[left] < nums[right]) {
                left++;
            }
        }
        // 只需要考虑移动的距离
        return left;
    }
}
