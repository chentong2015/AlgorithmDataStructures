package arrays.increments;

import java.util.Arrays;
import java.util.List;

// Sorting Three Groups
// You are given an integer array nums. Each element in nums is 1, 2 or 3.
// In each operation, you can remove an element from nums
//
// Return the minimum number of operations to make nums non-decreasing.
//
// Can you come up with an algorithm that runs in O(n) time complexity?
// 1 <= nums.length <= 100
// 1 <= nums[i] <= 3
public class SortingThreeGroups {

    // TODO. 等效于找到最长连续的Subsequence序列(非严格连续增长)
    // [2,1,3,2,1] -> 5-2=3
    // [1,1] [2,2] [1,3] [1,2]
    //
    // [1,3,2,1,3,3] -> 6-4=2
    // [1,1,3,3]
    //
    // [2,2,2,2,3,3] -> 6-6=0
    //
    // [1,2,3,2,1,3,2,1,3,2,1] -> 11-5=6
    //
    // O(n)
    // O(1)
    public static final int NUMBER_RANGE = 3;

    public int minimumOperations(List<Integer> nums) {
        int[] dp = new int[NUMBER_RANGE + 1];
        Arrays.fill(dp, nums.size());

        for(int num : nums){
            dp[num]--;
            dp[2] = Math.min(dp[1], dp[2]);
            dp[3] = Math.min(dp[2], dp[3]);
        }
        return dp[3];
    }
}
