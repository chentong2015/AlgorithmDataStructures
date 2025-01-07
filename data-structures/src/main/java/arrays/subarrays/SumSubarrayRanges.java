package arrays.subarrays;

// Sum of Subarray Ranges
// You are given an integer array nums.
// The range of a subarray of nums is the difference between largest and smallest element in subarray.
// Return the sum of all subarray ranges of nums.
//
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
//
// Follow-up: Could you find a solution with O(n) time complexity ?
public class SumSubarrayRanges {

    // TODO. DP + Stack 暂存读取过的“有用特征数据“
    // 1   3   3
    // -1  0   0  = min id before
    // -1  -1  -1 = max id before
    // 0 + 2 + 2  = 4
    //
    // 1   2   3
    // -1  0   1  = min id before
    // -1  -1  -1 = max id before
    // 0 + 1 + 1+2 = 4
    //
    // 4,  -2,  -3,  4,   1
    // -1  -1   -1   2    2 = min id before
    // -1  0    1    -1   3 = max id before
    // 0 + 6+ 1+7+ 7+6+ 3+4+3+3
    public static long subArrayRanges(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] nums1 = {4, -2, -3, 4, 1};
        System.out.println(subArrayRanges(nums1));
    }
}
