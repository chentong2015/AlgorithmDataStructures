package subarrays;

// Maximum Subarray
// Given an integer array nums, find the subarray with the largest sum, and return its sum.
//
// If you have figured out the O(n) solution,
// try coding another solution using the divide and conquer approach, which is more subtle.
//
// 1 <= nums.length <= 10^5
// -10^4 <= nums[i] <= 10^4  滑动窗口不适合负数
public class MaxSumSubarray {

    // TODO. 利用原始数组迭代的Sum值，判断是否继续累加
    // nums = [-2,1,-3,4,-1,2,1,-5,4] -> 6
    // [4,-1,2,1] has the largest sum 6.
    //
    // nums = [1] -> 1
    // nums = [5,4,-1,7,8] -> 23
    //
    // O(N) O(1)
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        for (int index = 1; index < nums.length; index++){
            // 只有当前面累计Sum为正才叠加, 否则保持index位置值不变(新区间初始值)
            if (nums[index - 1] > 0) {
                nums[index] += nums[index-1];
            }
            max = Math.max(max, nums[index]);
        }
        return max;
    }
}
