package iteration;

// Maximum Subarray
// Given an integer array nums, find the subarray with the largest sum, and return its sum.
//
// If you have figured out the O(n) solution,
// try coding another solution using the divide and conquer approach, which is more subtle.
//
// 1 <= nums.length <= 10^5
// -10^4 <= nums[i] <= 10^4
public class MaximumSubarray {

    // TODO. 直接在原始数组上迭代计算Sum的值，判断是否继续累加
    // nums = [-2,1,-3,4,-1,2,1,-5,4] -> 6
    // The subarray [4,-1,2,1] has the largest sum 6.
    //
    // nums = [1] -> 1
    // nums = [5,4,-1,7,8] -> 23
    //
    // O(N)
    // O(1)
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        for (int index = 1; index < nums.length; index++){
            // 只有当累计Sum为正才继续累加(否则累加出来的Sum不可能最大)
            if (nums[index - 1] > 0) {
                nums[index] += nums[index-1];
            }
            max = Math.max(max, nums[index]);
        }
        return max;
    }
}
