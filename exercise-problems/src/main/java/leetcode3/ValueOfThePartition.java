package leetcode3;

import java.util.Arrays;

// Find the Value of the Partition
// You are given a positive integer array nums.
// Partition nums into two arrays, nums1 and nums2, such that:
// - Each element of the array nums belongs to either the array nums1 or the array nums2.
// - Both arrays are non-empty.
// - The value of the partition is minimized.
//
// The value of the partition is |max(nums1) - min(nums2)|.
// - max(nums1) denotes the maximum element of the array nums1,
// - min(nums2) denotes the minimum element of the array nums2.
//
// Return the integer denoting the value of such partition.
// 2 <= nums.length <= 10^5
// 1 <= nums[i] <= 10^9
public class ValueOfThePartition {

    // TODO. 算法本质上是找两个最接近的数字
    //  不需要考虑如何划分和结果数组是什么，只考虑返回值的特征
    // [1,3,2,4] -> 1
    // nums1 = [1,2] and nums2 = [3,4].
    //
    // [100,1,10] -> 9
    // nums1 = [10] and nums2 = [100,1].
    //
    // 50 40 30 31 100 5
    // 5 30, 31 40 50 100 排序后的数据一定满足划分条件
    public int findValueOfPartition(int[] nums) {
        Arrays.sort(nums);
        int result = Integer.MAX_VALUE;
        for (int index =1; index<nums.length; index++) {
            int partition = nums[index] - nums[index -1];
            result = Math.min(result, partition);
        }
        return result;
    }
}
