package prefix_sum.interval_distance;

// Sum of Absolute Differences in a Sorted Array
// You are given an integer array nums sorted in non-decreasing order.
// Build and return an integer array result with the same length as nums
//
// result[i] is equal to the summation of absolute differences
// between nums[i] and all the other elements in the array.
//
// In other words,
// result[i] is equal to sum(|nums[i]-nums[j]|)
// where 0 <= j < nums.length and j != i (0-indexed).
//
// 2 <= nums.length <= 10^5
// 1 <= nums[i] <= nums[i + 1] <= 10^4
public class SumAbsoluteDifferencesArray {

    // TODO. 每个数需要和其他任何数相互计算
    // nums = [2,3,5] -> [4,3,5]
    // result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4,
    // result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3,
    // result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5.
    //
    public int[] getSumAbsoluteDifferences(int[] nums) {

        return null;
    }
}
