package subarrays.sums;

// Subarray Sum Equals K
// Given an array of integers nums and an integer k,
// return the total number of subarrays whose sum equals to k.
// A subarray is a contiguous non-empty sequence of elements within an array.
//
// 1 <= nums.length <= 2 * 10^4
// -1000 <= nums[i] <= 1000
// -10^7 <= k <= 10^7
public class SumSubarrayEqualsK {

    // TODO. 数字包含正负数，判断index移动的通解是什么?
    // [1,1,1], k = 2 -> 2
    // [1,2,3], k = 3 -> 2
    // [-1,-1,1], k = 0 -> 1
    //
    // 2 1 -2 4 2 -3 2 4, k = 2
    //
    //
    // O(n ~ n*n) 最差情况是一直往前累加，while循环次数为n级
    // O(1)
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] == k) {
                result++;
            } else if (nums[index] < k) {
                int value = nums[index];
                int left = index - 1;
                while (left >= 0) {
                    value += nums[left];
                    if (value < k) {
                        left--;
                        continue;
                    }
                    if (value == k) {
                        result++;
                    }
                    break;
                }
            }
        }
        return result;
    }
}
