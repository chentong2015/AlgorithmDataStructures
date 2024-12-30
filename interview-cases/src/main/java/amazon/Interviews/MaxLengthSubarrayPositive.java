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

    // TODO. 只关心输入的数据和结果的关系：中间的乘积计算数据不重要
    //  最长子SubArray的条件: 子数组中没有或者存在even numbers数目的负数
    // [1,-2,-3,4]    -> 4
    // [0,1,-2,-3,-4] -> 3
    // [-1,-2,-3,0,1] -> [-1,-2] or [-2,-3] -> 2
    // [-1,2,2,2]     -> 3 考虑及其特殊的情况
    // [1000000000]   -> 1
    // [0,8,0,2,-3]   -> 1
    //
    // O(n + n) 最差情况下index和right都将会移动n次
    // O(1)     没有空间复杂度的开销
    public int getMaxLen(int[] nums) {
         int maxLength = 0;
         int index = 0;
         while (index < nums.length) {
             if (nums[index] == 0) {
                 index++;
                 continue;
             }

             // TODO. 类似于Sliding Window但逻辑有所不同
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
             if (countNegative % 2 == 1) {
                 while (index < right) {
                     if (nums[index] < 0) {
                         maxLength = Math.max(maxLength, right - 1 - index);
                         break;
                     }
                     index++;
                 }
             } else {
                 // 这里的计算包括了只有一个正数的情况
                 maxLength = Math.max(maxLength, right - index);
             }

             // 无论right跳出的条件，这里都需要往后移动一步
             index = right + 1;
         }
         return maxLength;
    }
}
