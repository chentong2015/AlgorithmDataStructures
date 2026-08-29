package leetcode4;

// Minimum Elements to Add to Form a Given Sum
// You are given an integer array nums and two integers limit and goal.
// The array nums has an interesting property that abs(nums[i]) <= limit.
//
// Return the minimum number of elements you need to add to make the sum of the array equal to goal.
// The array must maintain its property that abs(nums[i]) <= limit.
// Note that abs(x) equals x if x >= 0, and -x otherwise.
//
// 1 <= nums.length <= 10^5
// 1 <= limit <= 10^6
// -limit <= nums[i] <= limit
// -10^9 <= goal <= 10^9
public class MinElementsToAddForGivenSum {

    // TODO. 计算SUM和Goal之间的差值，然后补齐
    // nums = [1,-1,1], limit = 3, goal = -4
    // 1 - 2 - 3 = -4
    //
    // nums = [1,-10,9,1], limit = 100, goal = 0
    // 1 - 1 = 0
    //
    // O(N)
    // O(1)
    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for (int num: nums) {
            sum += num;
        }
        if (sum == goal) {
            return 0;
        }

        // TODO. 补齐时直接计算倍数和余数, 无需使用任何循环
        long diffAbs = Math.abs(sum - goal);
        int offset = 0;
        if (diffAbs % limit != 0) {
            offset++;
        }
        return (int) (diffAbs / limit) + offset;
    }
}
