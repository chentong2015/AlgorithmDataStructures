package arrays.subarrays;

// Sum of Subarray Maximum
// Given an array of integers arr, find the sum of max(b),
// where b ranges over every (contiguous) subarray of arr.
// Since the answer may be large, return the answer modulo 10^9 + 7.
//
// arr = [3,1,2,4] -> Output: 30
// Subarrays
// [3], [1], [2], [4],
// [3,1], [1,2],
// [2,4], [3,1,2],
// [1,2,4], [3,1,2,4].
// Maximum  3, 1, 2, 4, 3, 2, 4, 3, 4, 4.
public class SumAllSubarrayMax {

    // TODO. 标记最值坐标 + 遍历每一个Item进行数学计算
    // O(n+n+n) 中间while循环最多只会执行n次
    // O(n)
    public int sunSubarrayMax(int[] nums) {
        int[] maxIndexBefore = new int[nums.length];
        maxIndexBefore[0] = -1;

        // 找到之前更大值的坐标, 使用记录的坐标去找坐标
        for (int index = 1; index < nums.length; index++) {
            int left = index - 1;
            while (left >= 0 && nums[left] < nums[index]) {
                left = maxIndexBefore[left];
            }
            maxIndexBefore[index] = left;
        }

        int[] sums = new int[nums.length];
        for (int index = 0; index < nums.length; index++) {
            int gap = index - maxIndexBefore[index];
            sums[index] = nums[index] * (index - gap);

            // 累加前面更大值的Index位置的统计, 如果-1则不需要累加
            if (maxIndexBefore[index] >= 0) {
                sums[index] += sums[maxIndexBefore[index]];
            }
        }

        int sumResult = 0;
        for (int sum: sums) {
            sumResult += sum;
        }
        return sumResult;
    }
}
