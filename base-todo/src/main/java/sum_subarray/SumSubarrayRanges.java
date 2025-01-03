package sum_subarray;

// Sum of Subarray Ranges
// You are given an integer array nums.
// The range of a subarray of nums is the difference between largest and smallest element in subarray.
// Return the sum of all subarray ranges of nums.
//
// A subarray is a contiguous non-empty sequence of elements within an array.
// 1 <= nums.length <= 1000
// -10^9 <= nums[i] <= 10^9
//
// nums = [1,2,3] -> sum = 4
// [1,2], range = 2 - 1 = 1
// [2,3], range = 3 - 2 = 1
// [1,2,3], range = 3 - 1 = 2
//
// nums = [1,3,3] -> sum = 4
// nums = [4,-2,-3,4,1] -> sum = 59
public class SumSubarrayRanges {

    // TODO. 根据现有的Examples推倒出一般规律，找出计算结果的公式
    //  Follow-up: Could you find a solution with O(n) time complexity?
    // 每读取一个新值，判读它是否在[min, max]区间之内
    // 1  2  3
    //
    // 1  2  3  4
    //
    // 4  -2  -3  4  1
    //
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] nums1 = {4, -2, -3, 4, 1};
        System.out.println(subArrayRanges(nums1));
    }

    public static long subArrayRanges(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        return 0;
    }
}
