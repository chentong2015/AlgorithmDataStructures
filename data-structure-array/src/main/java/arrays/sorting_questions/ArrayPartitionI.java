package arrays.sorting_questions;

import java.util.Arrays;

// Array Partition I
// Given an integer array nums of 2n integers,
// group these integers into n pairs (a1, b1), (a2, b2), ..., (an, bn)
// The sum of min(ai, bi) for all i is maximized, Return the maximized sum
//
// 1 <= n <= 104
// nums.length == 2 * n
// -10^4 <= nums[i] <= 10^4
public class ArrayPartitionI {

    // TODO: 判断什么情况下适合给数组排序，选择牺牲掉一定的复杂度
    // nums = [1,4,3,2] -> 4 备用数组的数据必须是双数，可以实现完全分组
    // (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
    // (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
    // (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4  ===> 所有分组可能中的最小值
    //
    // O(N*logN)
    // O(1)
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);

        int sum = 0;
        for (int index = 0; index < nums.length; index += 2) {
            sum += nums[index];
        }
        return sum;
    }
}
