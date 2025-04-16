package leetcode4;

// Sum of Beauty in the Array
// You are given a 0-indexed integer array nums.
// For each index i (1 <= i <= nums.length - 2) the beauty of nums[i] equals:
// 2, if nums[j] < nums[i] < nums[k],
//    for all 0 <= j < i
//    for all i < k <= nums.length - 1
// 1, if nums[i - 1] < nums[i] < nums[i + 1], and the previous condition is not satisfied.
// 0, if none of the previous conditions holds.
//
// Return the sum of beauty of all nums[i] where 1 <= i <= nums.length - 2.
// 3 <= nums.length <= 10^5
// 1 <= nums[i] <= 10^5
public class SumOfBeautyArray {

    // TODO. 利用辅助数组提前统计前后的最值，在遍历时直接判断
    // nums = [1,2,3] -> 2 只有一个位置的值
    // 1 < 2 < 3
    //
    // nums = [2,4,6,4] -> 1 + 0 = 1 统计两个位置的值
    // 2 < 4 < 6
    //
    // nums = [3,2,1] -> 0
    //
    // O(N+N+N) 三次循环数据
    // O(N+N)   以空间换取时间复杂度
    public int sumOfBeauties(int[] nums) {
        int max = nums[0];
        int[] maxBefore = new int[nums.length];
        for (int index = 0; index < nums.length - 1; index++) {
            max = Math.max(max, nums[index]);
            maxBefore[index + 1] = max;
        }

        int min = nums[nums.length -1];
        int[] minAfter = new int[nums.length];
        for (int index = nums.length - 1; index > 0; index--) {
            min = Math.min(min, nums[index]);
            minAfter[index - 1] = min;
        }

        int sumBeauty = 0;
        for (int index = 1; index < nums.length - 1; index++) {
            // 判断index位置值是否符合前后大小约束
            int num = nums[index];
            if (maxBefore[index] < num && num < minAfter[index]) {
                sumBeauty += 2;
            } else if (nums[index - 1] < num && num < nums[index + 1]) {
                sumBeauty += 1;
            }
        }
        return sumBeauty;
    }
}
