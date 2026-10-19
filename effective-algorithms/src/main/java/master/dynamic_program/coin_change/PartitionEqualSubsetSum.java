package master.dynamic_program.coin_change;

// Partition Equal Subset Sum
// Given an integer array nums,
// return true if you can partition the array into two subsets such that
// the sum of the elements in both subsets is equal or false otherwise.
//
// 1 <= nums.length <= 200
// 1 <= nums[i] <= 100
public class PartitionEqualSubsetSum {

    // TODO. 从数组中取一定的数据，使其和为目标值
    //
    // [1,5,11,5] -> [1,5,5] [11] -> true
    // [1,2,3,5]  -> false
    // [1,1,2,2]  -> true
    // [1,2,5]    -> false
    //
    // 1  5  11  5, target=11
    // F  F  F   F
    //
    // 0 1 2 3 4 5 6 7 8 9 10 11
    // T T
    //           T T
    //                         T -> OK
    //
    public boolean canPartition(int[] nums) {
        // TODO. Sum不能是奇数值, 且目标值一定是Sum一半
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        int targetSum = (int) (sum / 2);

        // TODO. 只能从后往前更新DP值，只要构成targetSum目标位置
        boolean[] dp = new boolean[targetSum + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int currSum = targetSum; currSum >= num; currSum--) {
                // 判断前面(currSum - num)位置是否可构成
                dp[currSum] = dp[currSum] || dp[currSum - num];
                if (dp[targetSum]) {
                    return true;
                }
            }
        }
        return dp[targetSum];
    }
}