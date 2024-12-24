package arrays.array.sum_subarray;

// Sum of Subarray Minimums
// Given an array of integers arr, find the sum of min(b),
// where b ranges over every (contiguous) subarray of arr.
// Since the answer may be large, return the answer modulo 10^9 + 7.
//
// arr = [3,1,2,4] -> Output: 17
// Subarrays [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
// Minimums  3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
//
// 1 <= arr.length <= 3 * 10^4
// 1 <= arr[i] <= 3 * 10^4
public class SumSubarrayMinimums {

    public static void main(String[] args) {
        int[] arr = {3,1,2,4};
        System.out.println(sumSubarrayMins(arr));
    }

    // TODO. 使用Stack[]数组存储Index位置前面所有它小的坐标
    //  在Stack添加和取值的时候，通过判断来计算最后的结果
    //
    // 3  1  2     4      7         3
    //    1  1+1'  2+1+1' 4+2+1+1'  3+3+2+1+1'
    //
    public static int sumSubarrayMins(int[] arr) {
        int sumMin = 0;
        int countIndexBefore = 0;
        int[] stack = new int[arr.length];
        for (int index=0; index < arr.length; index++) {
            sumMin += arr[index];

            while (countIndexBefore > 0 && arr[stack[countIndexBefore - 1]] > arr[index]) {
                sumMin += arr[index];
                countIndexBefore--;
            }
            for (int j = 0; j < countIndexBefore; j++) {
                sumMin += arr[stack[j]];
                if (j==0) {
                    sumMin += arr[stack[j]] * stack[j]; // 最小值之前的统计
                }
            }

            stack[countIndexBefore] = index;
            countIndexBefore++;
        }
        return sumMin;
    }
}
