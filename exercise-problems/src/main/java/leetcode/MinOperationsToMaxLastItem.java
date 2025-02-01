package leetcode;

// Minimum Operations to Maximize Last Elements in Arrays
// You are given two 0-indexed integer arrays, nums1 and nums2, both having length n.
// In an operation
//  - select an index i in the range [0, n - 1]
//  - swap the values of nums1[i] and nums2[i].
// Find the minimum number of operations required to satisfy the following conditions:
//  - nums1[n - 1] = max(nums1[0], nums1[1], ..., nums1[n - 1]).
//  - nums2[n - 1] = max(nums2[0], nums2[1], ..., nums2[n - 1]).
//
// Return an integer denoting the minimum number of operations needed to meet both conditions,
// or -1 if it is impossible to satisfy both conditions.
//
// 1 <= n == nums1.length == nums2.length <= 1000
// 1 <= nums1[i] <= 10^9
// 1 <= nums2[i] <= 10^9
public class MinOperationsToMaxLastItem {

    // TODO. 问题本质: 抓住最后两值的特征(一个最值，另一个值必须大于等于任意位置上的其中一个数)
    //
    // 判断有多少个小于等于n的数据存在，补全剩余的数据的Min数量
    // [1 .... n]
    // [n+1 ... 2n]
    //
    // [1,2,7]
    // [4,5,3]
    //
    // [1,2,3]
    // [4,5,7]
    //
    // [2,3,4,5,9]
    // [8,8,4,4,4]
    //
    // [2,3,4,5,4]
    // [8,8,4,4,9]
    //
    // [2,3,4,4,4]
    // [8,8,4,5,9]
    //
    // [17,13,19,9,6,14]
    // [17,14,15,1,19,19]
    public int minOperations(int[] nums1, int[] nums2) {
        int length = nums1.length;
        int middleValue = Math.min(nums1[length - 1], nums2[length - 1]);
        int maxValue = Math.max(nums1[length - 1], nums2[length - 1]);

        int countLessNum1 = 0;
        int countLessNum2 = 0;
        for (int index = 0; index < length - 1; index++) {
            if (nums1[index] > maxValue || nums2[index] > maxValue) {
                return -1; // The max value must be at the end
            }
            if (nums1[index] > middleValue && nums2[index] > middleValue) {
                return -1; // Not possible to switch the value
            }

            if (nums1[index] <= middleValue) {
                countLessNum1++;
            }
            if (nums2[index] <= middleValue) {
                countLessNum2++;
            }
        }

        // TODO. 在一定能够成立的条件下，往需要移动次数较少的Nums数组移动
        if (countLessNum1 > countLessNum2) {
            // 往更接近最少交换次数的方向交换
            if (middleValue == nums1[length - 1]) {
                return length - 1 - countLessNum1;
            }
            return length - 1 - countLessNum1 + 1;
        } else {
            if (middleValue == nums2[length -1]) {
                return length - 1 - countLessNum2;
            }
            return length - 1 - countLessNum2 + 1;
        }
    }
}
