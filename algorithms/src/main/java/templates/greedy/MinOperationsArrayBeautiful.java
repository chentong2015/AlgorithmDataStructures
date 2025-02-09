package templates.greedy;

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
public class MinOperationsArrayBeautiful {

    // TODO. Greedy贪心算法: 每读取一个新数据时，累计前面三个差值中最小的一个(最优解)
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
    // [7,7,2,7], k = 9
    // [7,9,2,7]
    //  2 2 7
    //       2+2
    //
    // [43,31,14,4], k = 73
    // [43,73,14,4]
    public long minIncrementOperations(int[] nums, int k) {
        int length = nums.length;
        long[] dp = new long[length];
        dp[0] = Math.max(0, k - nums[0]);
        dp[1] = Math.max(0, k - nums[1]);
        dp[2] = Math.max(0, k - nums[2]);

        // TODO. DP不断地累加前面历史最优的结果
        for(int i = 3; i < length; i++) {
            long minBefore = Math.min(dp[i-1], dp[i-2]);
            minBefore = Math.min(minBefore, dp[i-3]);
            dp[i] = minBefore + Math.max(0, k-nums[i]);
        }

        // 最后选择三个累计结果最小值
        long result = Math.min(dp[length-1], dp[length-2]);
        return Math.min(result, dp[length-3]);
    }
}
