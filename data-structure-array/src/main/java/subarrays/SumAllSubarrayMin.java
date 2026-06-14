package subarrays;

// Sum of Subarray Minimums
// Given an array of integers arr, find the sum of min(b),
// where b ranges over every (contiguous) subarray of arr.
// Since the answer may be large, return the answer modulo 10^9 + 7.
//
// arr = [3,1,2,4] -> Output: 17
// Subarrays
// [3], [1], [2], [4],
// [3,1], [1,2], [2,4],
// [3,1,2], [1,2,4],
// [3,1,2,4].
// 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
//
// 1 <= arr.length <= 3 * 10^4
// 1 <= arr[i] <= 3 * 10^4
public class SumAllSubarrayMin {

    // TODO. 只积累当前值位置前一个更小值(有用值), 且逐步累加结果
    // Input: 3   1   2    4
    // Stack: -1  -1  1    2    统计前面更小值的Index坐标位置
    // Sum[]: 3   2   2+2  4+4  利用数组已经计算的前面结果
    //
    // O(n+n+n) 最佳的时间复杂度
    // O(n)     对等的空间复杂度, 栈数组中存储的坐标
    public static int sumSubarrayMins(int[] nums) {
        int[] smallIndexBefore = new int[nums.length];
        // 第一个元素前面没有更小值的坐标
        smallIndexBefore[0] = -1;

        for (int i = 1; i < nums.length; i++) {
            int left = i - 1;
            while (left >= 0 && nums[left] >= nums[i]) {
                left = smallIndexBefore[left];
            }
            smallIndexBefore[i] = left;
        }

        int[] sum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 考虑Gap间隔，统计当前nums[i]能够被当作几次最小值
            int gap = i - smallIndexBefore[i];
            sum[i] = nums[i] * gap;

            // 累加前面更小值的Index位置的统计, 如果-1则不需要累加
            if (smallIndexBefore[i] >= 0) {
                sum[i] += sum[smallIndexBefore[i]];
            }
        }

        long result = 0;
        for (int i = 0; i < nums.length; i++) {
            result += sum[i];
        }
        return (int) (result % 1_000_000_007);
    }
}
