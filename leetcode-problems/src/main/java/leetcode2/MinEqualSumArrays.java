package leetcode2;

// Minimum Equal Sum of Two Arrays After Replacing Zeros
// You are given two arrays nums1 and nums2 consisting of positive integers.
//
// You have to replace all the 0's in both arrays with strictly positive integers
// such that the sum of elements of both arrays becomes equal.
//
// Return the minimum equal sum you can obtain, or -1 if it is impossible.
// 1 <= nums1.length, nums2.length <= 10^5
// 0 <= nums1[i], nums2[i] <= 10^6
public class MinEqualSumArrays {

    public static void main(String[] args) {
        int[] nums1 = {0,7,28,17,18};
        int[] nums2 = {1,2,6,26,1,0,27,3,0,30};

        MinEqualSumArrays instance = new MinEqualSumArrays();
        System.out.println(instance.minSum(nums1, nums2));
    }

    // TODO. 统计各“元素和差值”以及0个个数，判断区间范围是否有重叠
    // 70 + 2 + / 96 + 2 +
    //
    // [3,2,0,1,0], [6,5,0] -> equal sum of 12
    // [3,2,2,1,4], [6,5,1]
    //
    // [2,0,2,0], [1,4] -> 1
    public long minSum(int[] nums1, int[] nums2) {
        long sum1 = 0;
        int countZero1 = 0;
        for (int num: nums1) {
            if (num == 0) {
                countZero1++;
            } else {
                sum1 += num;
            }
        }

        long sum2 = 0;
        int countZero2 = 0;
        for (int num: nums2) {
            if (num == 0) {
                countZero2++;
            } else {
                sum2 += num;
            }
        }

        // 如果不存在0则无法替换，可以直接比较
        if (countZero1 == 0) {
            if (sum1 < sum2 + countZero2) {
                return -1;
            }
        }
        if (countZero2 == 0) {
            if (sum2 < sum1 + countZero1) {
                return -1;
            }
        }
        // 找到第一个能够相等的位置
        return Math.max(sum1 + countZero1, sum2 + countZero2);
    }
}
