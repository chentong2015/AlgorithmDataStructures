package leetcode6;

// Maximum Product Subarray
// Given an integer array nums,
// find a subarray that has the largest product, and return the product.
// The test cases are generated so that the answer will fit in a 32-bit integer.
//
// 1 <= nums.length <= 2 * 10^4
// -10 <= nums[i] <= 10
public class MaxProductSubarray {

    // TODO. DP存储极致的最大最小值, 遇到负数反转
    //
    // [2,3,-2,4] -> [2,3]
    // [-2,0,-1]  -> 0
    //
    //       2  3  -2   4  0  2  1
    // dpMin 2  3  -12 -48 0  0  0   存储极致的小值
    // dpMax 2  6  -2   4  0  2  2   存储极致的大值
    //            index
    // O(N)
    // O(1)
    public int maxProduct(int[] nums) {
        int result = nums[0];
        int dpMin = nums[0];
        int dpMax = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            int num = nums[i]; // 当前位置的值参与最值判断，为0这刷新dpMin=dpMax=0值
            int prevMin = dpMin;
            int prevMax = dpMax;
            if (num < 0) {
                dpMin = Math.min(prevMax * num, num); // 原来的最大值乘负数变成最小
                dpMax = Math.max(prevMin * num, num); //
            } else {
                dpMin = Math.min(prevMin * num, num); // 原来的最大值乘正数依然最大
                dpMax = Math.max(prevMax * num, num); //
            }
            result = Math.max(result, dpMax);
        }
        return result;
    }
}