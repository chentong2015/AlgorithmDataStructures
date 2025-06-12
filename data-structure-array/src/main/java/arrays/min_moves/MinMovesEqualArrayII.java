package arrays.min_moves;

import java.util.Arrays;

// Minimum Moves to Equal Array Elements II
// Given an integer array nums of size n,
// return the minimum number of moves required to make all array elements equal.
// Test cases are designed so that the answer will fit in a 32-bit integer.
//
// In one move, you can increment or decrement an element of the array by 1.
//
// n == nums.length
// 1 <= nums.length <= 10^5
// -10^9 <= nums[i] <= 10^9
public class MinMovesEqualArrayII {

    // TODO. 将数据往Median中位数进行靠拢
    //  中位数表示位于数组中50%位置的数据
    //  平均数(不一定在数组中)不能作为目标值
    // nums = [1,2,3] -> 2
    // [1,2,3] => [2,2,3] => [2,2,2]
    //
    // nums = [1,3,5] -> 4
    // [1,3,5] => [2,3,5] => [3,3,5] => [3,3,4] => [3,3,3]
    //
    // nums = [1,10,2,9] -> 16
    //
    // nums = [1,0,0,8,6] -> 14
    public int minMoves2(int[] nums) {
        // 排序后直接取出中位数
        Arrays.sort(nums);
        int mid = nums[nums.length / 2];

        int operations = 0;
        for (int num: nums) {
            operations += Math.abs(mid - num);
        }
        return operations;
    }
}
