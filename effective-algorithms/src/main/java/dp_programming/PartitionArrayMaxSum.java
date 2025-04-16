package dp_programming;

// Partition Array for Maximum Sum
// Given an integer array arr, partition the array into (contiguous) subArrays of length at most k.
// After partitioning, each subArray has their values become the maximum value of that subArray.
//
// Return the largest sum of the given array after partitioning.
// Test cases are generated so that the answer fits in a 32-bit integer.
//
// 1 <= arr.length <= 500
// 0 <= arr[i] <= 109
// 1 <= k <= arr.length
public class PartitionArrayMaxSum {

    // TODO. DP记录每个Index位置和的最大值结果
    // [1,15,7,9,2,5,10], k = 3  => Output: 84
    // [15,15,15,9,10,10,10]
    //
    // [1,4,1,5,7,3,6,1,9,9,3], k = 4  => Output: 83
    // [4,4,7,7,7,9,9,9,9,9,9] = 29 + 54 = 83
    //
    // 循环之前统计的历史记录，找出能组成的最大值
    // 1,2,3,3,4, k=3
    // 1
    //   1+2 4 = 4
    //     4+3 9 = 9
    //       1+9 4+6 = 10
    //         4+12 9+8 10+4 = 17
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        for (int index = 1; index < arr.length; index++) {
            int maxValue = arr[index];
            int width = 0;
            for (int left = index; left >= (index - k + 1) && left >= 0; left--) {
                // TODO. 从后往前保证来最大值的更新, 确定计算的宽度
                maxValue = Math.max(maxValue, arr[left]);
                width++;

                // 只有当left大于0时，才考虑dp[left - 1]前面的统计
                if (left == 0) {
                    dp[index] = Math.max(dp[index], width * maxValue);
                } else {
                    dp[index] = Math.max(dp[index], dp[left - 1] + width * maxValue);
                }
            }
        }
        return dp[arr.length - 1];
    }
}
