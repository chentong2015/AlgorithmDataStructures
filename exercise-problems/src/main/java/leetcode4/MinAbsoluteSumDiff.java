package leetcode4;

import java.util.*;

// Minimum Absolute Sum Difference
// You are given two positive integer arrays nums1 and nums2, both of length n.
// The absolute sum difference of arrays nums1 and nums2 is defined as
// the sum of |nums1[i] - nums2[i]| for each 0 <= i < n (0-indexed).
//
// You can replace at most one element of nums1 with any other element in nums1
// to minimize the absolute sum difference.
//
// Return the minimum absolute sum difference
// after replacing at most one element in the array nums1
// Since the answer may be large, return it modulo 109 + 7.
//
// n == nums1.length
// n == nums2.length
// 1 <= n <= 10^5
// 1 <= nums1[i], nums2[i] <= 10^5
public class MinAbsoluteSumDiff {

    // TODO. 找出最大的能够节省ABS差值的位置，并计算出节省的差值
    // nums1 = [1,7,5], nums2 = [2,3,5] -> 3
    // => [1,1,5] or [1,5,5]
    // 1 + 2 + 0 = 3
    //
    // nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
    // => [10,10,4,4,2,7]
    // 1 + 7 + 1 + 3 + 5 + 3 = 20
    //
    // nums1 = [1,28,21], nums2 = [9,21,20] -> 9
    // => [1,22,21] 替换第二大的位置
    // 8 + 0 + 1
    //
    // nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10] -> 0
    // => 无需任何替换
    //
    // O(N + N*logN + N*logN + N*logN) 排序和二分查找的复杂度
    // O(N)                            存储所有差值的数据
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        // 使用list存储差值和index坐标位置
        List<int[]> list = new ArrayList<>();

        long sumAbs = 0;
        for (int index = 0; index < nums1.length; index++) {
            int abs = Math.abs(nums1[index] - nums2[index]);
            list.add(new int[] {abs, index});
            sumAbs += abs;
            sumAbs %= 1000000007;
        }
        if (sumAbs == 0) {
            return 0;
        }

        // 根据ABS绝对值差值从小到大排序
        list.sort(Comparator.comparingInt(a -> a[0]));
        Arrays.sort(nums1);

        // 统计可以节省的最大差值的大小
        int maxDiffAbs = 0;
        for (int index = list.size() - 1; index >= 0; index--) {
            int baseAbs = list.get(index)[0];
            int targetIndex = list.get(index)[1];

            int minAbs = findMinAbs(nums1, nums2[targetIndex]);
            if (baseAbs > minAbs) {
               maxDiffAbs = Math.max(maxDiffAbs, baseAbs - minAbs);
            }
        }

        sumAbs = (sumAbs - maxDiffAbs) % 1000000007;
        return (int) (sumAbs + 1000000007) % 1000000007;
    }

    // TODO. 只取二分差值中最接近位置的三个差值坐标
    private int findMinAbs(int[] nums1, int targetNum) {
        int foundIndex = Arrays.binarySearch(nums1, targetNum);
        if (foundIndex < 0) {
            foundIndex = -(foundIndex + 1);
        }

        // 二分法差结果index坐标范围[0, length]闭合区间
        int minAbs = Integer.MAX_VALUE;
        if (foundIndex > 0) {
            int abs = Math.abs(nums1[foundIndex - 1] - targetNum);
            minAbs = Math.min(minAbs, abs);
        }
        if (foundIndex < nums1.length - 1) {
            int abs = Math.abs(nums1[foundIndex + 1] - targetNum);
            minAbs = Math.min(minAbs, abs);
        }
        if (foundIndex < nums1.length) {
            int abs = Math.abs(nums1[foundIndex] - targetNum);
            minAbs = Math.min(minAbs, abs);
        }
        return minAbs;
    }
}
