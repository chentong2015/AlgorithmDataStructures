package subarrays.min_max;

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
public class SumSubarrayMax {

    public static void main(String[] args) {
        int[] nums = {3,1,2,4};
        SumSubarrayMax sumSubarrayMax = new SumSubarrayMax();
        int sum = sumSubarrayMax.sunSubarrayMax(nums);
        System.out.println(sum);
    }

    public int sunSubarrayMax(int[] nums) {
        int[] stack = new int[nums.length];
        stack[0] = -1;

        // 找到之前更大值的坐标，如果前面没有更大值则为-1
        for (int index=1; index<nums.length; index++) {
            int beforeIndex = index -1;
            while (beforeIndex >= 0 && nums[beforeIndex] < nums[index]) {
                beforeIndex--;
            }

            stack[index] = beforeIndex;
        }

        // 使用单独的数组来保持每个位置计算的Sum
        int[] sums = new int[nums.length];
        for (int index=0; index < nums.length; index++) {
            int beforeIndex = stack[index];
            sums[index] = nums[index] * (index - beforeIndex);

            if (beforeIndex >= 0) {
                sums[index] += sums[beforeIndex];
            }
        }

        int sumResult = 0;
        for (int sum: sums) {
            sumResult += sum;
        }
        return sumResult;
    }
}
