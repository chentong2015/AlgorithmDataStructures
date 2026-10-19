package master.dynamic_program;

import java.util.Arrays;

// Maximum Number of Jumps to Reach the Last Index
// You are given a 0-indexed array nums of n integers and an integer target.
// You are initially positioned at index 0
// In one step, you can jump from index i to any index j such that:
//   0 <= i < j < n
//   -target <= nums[j] - nums[i] <= target
//
// Return the maximum number of jumps you can make to reach index n - 1.
// If there is no way to reach index n - 1, return -1.
//
// 2 <= nums.length == n <= 1000
// -10^9 <= nums[i] <= 10^9
// 0 <= target <= 2 * 10^9
public class MaxNumJumpSteps {

    // TODO. "递归判断"能够跳跃到终点的最长线路
    // [1,3,6,4,1,2], target=0
    // [1,3,6,4,1,2], target=3
    // [1,3,6,4,1,2], target=2
    //
    //  1  3  6   4   1  2   target=2
    //  0  -1 -1  -1  -1 -1
    //     1      2      3
    //            只有4能跳到最后位置
    //
    public int maximumJumps(int[] nums, int target) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        dp[0] = 0; // 设置启动起始点(能跳跃)

        for (int index = 1; index < nums.length; index++) {
            for (int left = 0; left < index; left++) {
                // 判断能否从前面位置跳到index位置，且取最长步骤
                if (Math.abs(nums[left] - nums[index]) <= target) {
                    if (dp[left] != -1) {
                        dp[index] = Math.max(dp[index], dp[left] + 1);
                    }
                }
            }
        }
        return dp[nums.length - 1];
    }
}