package master.dynamic_program;

// Partition Array for Maximum Sum
// Given an integer array arr, partition the array into (contiguous) subArrays of length at most k.
// After partitioning, each subArray has their values become the maximum value of that subArray.
//
// Return the largest sum of the given array after partitioning.
// Test cases are generated so that the answer fits in a 32-bit integer.
//
// 1 <= arr.length <= 500
// 0 <= arr[i] <= 109
// 1 <= k <= arr.length
public class PartitionArrayMaxSum {

    // TODO. DP记录每个Index位置和的最大值结果
    // [1,15,7,9,2,5,10], k = 3  => Output: 84
    // [15,15,15,9,10,10,10]
    //
    // [1,4,1,5,7,3,6,1,9,9,3], k = 4  => Output: 83
    // [4,4,7,7,7,9,9,9,9,9,9] = 29 + 54 = 83
    //
    // 从后往前保证来最大值的更新, 确定计算的宽度
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int[] dpSum = new int[arr.length];
        dpSum[0] = arr[0]; // 设置dp第一个初始值

        for (int index = 1; index < arr.length; index++) {
            int maxValue = arr[index];
            int leftmost = index - k + 1;

            for (int left = index; left >= leftmost && left >= 0; left--) {
                maxValue = Math.max(maxValue, arr[left]);
                int sum = (index-left) * maxValue;
                if (left == 0) {
                    dpSum[index] = Math.max(dpSum[index], sum);
                } else {
                    int sumBefore = dpSum[left-1]; // 累计左侧之前的和
                    dpSum[index] = Math.max(dpSum[index], sumBefore + sum);
                }
            }
        }
        return dpSum[arr.length - 1]; // 存储全部的累计和
    }
}