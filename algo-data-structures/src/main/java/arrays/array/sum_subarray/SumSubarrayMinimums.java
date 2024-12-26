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
        int[] nums = {11,81,94,43,3};
        System.out.println(sumSubarrayMins(nums));
    }

    // TODO. Stack[]数组存储Index位置前面所有更小值的坐标
    //  [first ... middle ... right .. Index] 记录的之前坐标需要分三段计算
    // 3  1  2  4 -
    // 3 0 0 0
    // 1 0 0 0
    // 1 2 0 0
    // 1 2 4 0
    public static int sumSubarrayMins(int[] arr) {
        int sumMin = 0;
        int indexMin = -1;
        int[] stackMinBefore = new int[arr.length];
        for (int index=0; index < arr.length; index++) {
            sumMin += arr[index];
            while (indexMin > -1 && arr[stackMinBefore[indexMin]] >= arr[index]) {
                indexMin--;
            }

            if (indexMin == -1) {
                sumMin += arr[index] * index;
            } else {
                int lastIndex = stackMinBefore[indexMin];
                sumMin += arr[lastIndex] * (index - lastIndex);

                for (int middle = indexMin; middle >= 1; middle--) {
                    int rightIndex = stackMinBefore[middle];
                    int leftIndex = stackMinBefore[middle -1];
                    sumMin += arr[leftIndex] * (rightIndex - leftIndex);
                }

                int firstIndex = stackMinBefore[0];
                sumMin += arr[firstIndex] * (firstIndex);
            }
            stackMinBefore[++indexMin] = index;
        }
        return sumMin;
    }
}
