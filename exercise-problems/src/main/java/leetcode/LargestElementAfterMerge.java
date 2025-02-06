package leetcode;

// Largest Element in an Array after Merge Operations
// You are given a 0-indexed array nums consisting of positive integers.
//
// Do the following operation on the array any number of times:
// - Choose an integer i such that 0 <= i < nums.length - 1 and nums[i] <= nums[i + 1]
// - Replace the element nums[i + 1] with nums[i] + nums[i + 1]
// - Delete the element nums[i] from the array.
// Return the value of the largest element that you can possibly obtain in the final array.
//
// 1 <= nums.length <= 105
// 1 <= nums[i] <= 106
public class LargestElementAfterMerge {

    // TODO. 从后往前读取，记录累积的最大值(最后的结果)
    // [2,3,7,9,3] -> 21
    // [5,7,9,3]
    // [5,16,3]
    // [21,3]
    //
    // [5,3,3] -> 11
    // [5,6]
    // [11]
    //
    // 5 2 3 5 6 3 -> 21
    // 5 16 3
    // 21 3
    //
    // 5 4 3 2 1 -> 5
    public long maxArrayValue(int[] nums) {
        long sumRight = nums[nums.length - 1];
        for (int index = nums.length-2; index >= 0; index--) {
            if (nums[index] <= nums[index + 1]) {
                sumRight += nums[index];
            } else {
                if (nums[index] <= sumRight) {
                    sumRight += nums[index];
                } else {
                    // 替换成新的当前的最大值
                    sumRight = nums[index];
                }
            }
        }
        return sumRight;
    }
}

