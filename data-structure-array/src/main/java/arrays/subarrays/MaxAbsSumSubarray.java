package arrays.subarrays;

// Maximum Absolute Sum of Any Subarray
// You are given an integer array nums.
// The absolute sum of a subarray [numsl, numsl+1, ..., numsr-1, numsr]
//  is abs(numsl + numsl+1 + ... + numsr-1 + numsr).
// Return the maximum absolute sum of any (possibly empty) subarray of nums.
//
// 1 <= nums.length <= 10^5
// -10^4 <= nums[i] <= 10^4
public class MaxAbsSumSubarray {

    // TODO. ABS绝对值由"最大正值SUM"和"最小负值SUM"决定
    // [1, -3, 2, 3, -4] -> [2,3] -> 5
    //  1  -2  2  5  -1
    //
    // [2, -5, 1, -4, 3, -2]
    //  2  -3  1  -3  3  -1  maxBefore
    //  2  -5  -4 -8  -5 -7  minBefore 只记录小于等于0的数据
    //
    // [-1,5] -> 5
    //
    // O(N+N) 两遍循环数组
    // O(N)
    public int maxAbsoluteSum(int[] nums) {
        if (nums.length == 1) {
            return Math.abs(nums[0]);
        }

        int[] sumBefore = new int[nums.length];
        sumBefore[0] = nums[0];

        // 只保留正数往上累加 => Maximum Subarray
        int maxAbsSum = sumBefore[0];
        for (int index = 1; index < nums.length; index++) {
            sumBefore[index] = nums[index];
            if (sumBefore[index - 1] > 0) {
                sumBefore[index] += sumBefore[index - 1];
            }
            maxAbsSum = Math.max(maxAbsSum, sumBefore[index]);
        }

        // 只保留负数往下累加 => Minimum Subarray
        sumBefore[0] = nums[0];
        for (int index = 1; index < nums.length; index++) {
            sumBefore[index] = nums[index];
            if (sumBefore[index - 1] <= 0) {
                sumBefore[index] += sumBefore[index - 1];
            }
            int absSum = Math.abs(sumBefore[index]);
            maxAbsSum = Math.max(maxAbsSum, absSum);
        }
        return maxAbsSum;
    }
}
