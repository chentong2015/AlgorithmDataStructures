package data_structure.dynamic_program.one_dimension;

// Minimum Increment Operations to Make Array Beautiful
// You are given a 0-indexed integer array nums having length n, and an integer k.
// You can perform the following increment operation any number of times (including zero):
// Choose an index i in the range [0, n - 1], and increase nums[i] by 1.
//
// An array is considered beautiful if:
// Any subarray with a size of 3 or more, its maximum element is greater than or equal to k.
//
// Return minimum number of increment operations needed to make nums beautiful.
// 3 <= n == nums.length <= 10^5
// 0 <= nums[i] <= 10^9
// 0 <= k <= 10^9
public class MakeArrayBeautiful {

    // TODO. DP算法: 每次新增一个值时, 从前面记录中取最小差值, 继续推导
    // [2,3,0,0,2], k = 4
    // [2,4,0,0,2]
    // [2,4,0,0,3]
    // [2,4,0,0,4]
    //  2 1 4
    //       1+4
    //         1+2=3
    //
    // [0,1,3,3], k = 5
    // [0,1,4,3]
    // [0,1,5,3]
    //  5 4 2
    //        2+2
    //
    // O(N)
    // O(N)
    public long minIncrementOperations(int[] nums, int k) {
        int length = nums.length;
        long[] dp = new long[length];
        dp[0] = Math.max(0, k - nums[0]);
        dp[1] = Math.max(0, k - nums[1]);
        dp[2] = Math.max(0, k - nums[2]);

        for(int i = 3; i < length; i++) {
            // 在前面最小差值的基础上，再记录index位置需要的差值
            long minBefore = Math.min(Math.min(dp[i-1], dp[i-2]), dp[i-3]);
            dp[i] = minBefore + Math.max(0, k-nums[i]);
        }

        // 从DP累计统计中找结果: 满足其中最小的一种
        return Math.min(Math.min(dp[length-1], dp[length-2]), dp[length-3]);
    }
}
