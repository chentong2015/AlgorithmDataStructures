package amazon.Interviews;

// Maximum Length of Subarray With Positive Product
// Given an array of integers nums, find the maximum length of a subarray
// where the product of all its elements is positive.
//
// A subarray of an array is a consecutive sequence of zero or more values taken out of that array.
//
// Return the maximum length of a subarray with positive product.
// 1 <= nums.length <= 10^5
// -10^9 <= nums[i] <= 10^9
public class MaxLengthSubarrayPositive {

    // TODO. 只考虑输入数据和结果的关系：中间乘积计算数据不重要，也不需要将乘积计算后比较
    //  最长子SubArray的条件: 子数组中没有或者有even偶数数目的负数
    // [1,-2,-3,4]    -> 4
    // [0,1,-2,-3,-4] -> 3
    // [-1,-2,-3,0,1] -> [-1,-2] or [-2,-3] -> 2
    // [-1,2,2,2]     -> 3 考虑及其特殊的情况
    // [1000000000]   -> 1
    // [0,8,0,2,-3]   -> 1
    //
    // O(n + n) 最差情况下index和right都将会移动n次 (例如片段数据中只有一个负数出现在最后)
    // O(1)     没有空间复杂度的开销
    public int getMaxLen(int[] nums) {
         int maxLength = 0;
         int index = 0;
         while (index < nums.length) {
             if (nums[index] == 0) {
                 index++;
                 continue;
             }
             int countNegative = nums[index] > 0 ? 0: 1;
             int right = index + 1;
             while (right < nums.length && nums[right] != 0) {
                 if (nums[right] < 0) {
                     countNegative++;
                 }
                 // 在循环的过程中直接统计负数的个数
                 if (countNegative % 2 == 0) {
                     maxLength = Math.max(maxLength, right - index + 1);
                 }
                 right++;
             }

             // 注意跳出while循环的条件: right==nums.length或nums[right]==0
             if (countNegative % 2 == 0) {
                 // 特殊情况: 只有index一个位置数据时
                 maxLength = Math.max(maxLength, right - index);
             } else {
                 // 奇数情况: 移除第一个出现的负数来计算最长长度
                 while (index < right) {
                     if (nums[index] < 0) {
                         maxLength = Math.max(maxLength, right - 1 - index);
                         break;
                     }
                     index++;
                 }
             }

             // 无论right跳出的条件，这里都需要往后移动一步
             index = right + 1;
         }
         return maxLength;
    }
}
