package arrays.array.sum_subarray;

// Sum of Subarray Minimums
// Given an array of integers arr, find the sum of min(b),
// where b ranges over every (contiguous) subarray of arr.
// Since the answer may be large, return the answer modulo 10^9 + 7.
//
// arr = [3,1,2,4] -> Output: 17
// Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
// Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
//
// 1 <= arr.length <= 3 * 10^4
// 1 <= arr[i] <= 3 * 10^4
public class SumSubarrayMinimums {

    // 存储index位置前面比它小的所有index位置
    // 3  1  2    4
    //    1  1+1  1+1+1
    //
    // 1  2  3  4
    //
    public int sumSubarrayMins(int[] arr) {
        int minSum = 0;
        int minBefore = arr[0];
        for (int index =0; index < arr.length; index++) {
            minSum += arr[index];
            if (arr[index] < minBefore) {
                minBefore = arr[index];
            }
        }


        return 0;
    }
}
