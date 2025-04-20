package searching_binary.min_absolute;

import java.util.List;
import java.util.TreeSet;

// Minimum Absolute Difference Between Elements With Constraint
// You are given a 0-indexed integer array nums and an integer x.
// Find the minimum absolute difference between two elements in the array
// that are at least x indices apart.
//
// In other words, find two indices i and j such that
// abs(i - j) >= x and abs(nums[i] - nums[j]) is minimized.
//
// Return an integer denoting the minimum absolute difference
// between two elements that are at least x indices apart.
//
// 1 <= nums.length <= 10^5
// 1 <= nums[i] <= 10^9
// 0 <= x < nums.length
public class MinAbsoluteDiffConstraint {

    // TODO. 在循环过程中实时排序 + 二分查找(调用Sorted数据结构API)
    // nums = [4,3,2,4], x = 2 -> 0
    // nums = [5,3,2,10,15], x = 1 -> 1
    // nums = [1,2,3,4], x = 3 -> 3
    //
    // 在向后移动right坐标的时候，将前面的有效值加入集合进行排序
    // 1  3  4  5  6
    // [i    r]
    //
    // O(N*(logN + logN + logN)) 插入和查找都是logN时间复杂度
    // O(N-K) 只需存储剩余数据量
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        int minAbsolute = Integer.MAX_VALUE;
        TreeSet<Integer> sortedSet = new TreeSet<>();
        for (int right = x; right < nums.size(); right++) {
            sortedSet.add(nums.get(right - x));

            // 如果出现最小绝对值，无需再继续遍历
            int value = nums.get(right);
            if (sortedSet.contains(value)) {
                return 0;
            }

            // TODO. 必须判断查找出来的结果是否为null
            Integer lower = sortedSet.lower(value);
            if (lower != null) {
                minAbsolute = Math.min(minAbsolute, value - lower);
            }
            Integer higher = sortedSet.higher(value);
            if (higher != null) {
                minAbsolute = Math.min(minAbsolute, higher - value);
            }
        }
        return minAbsolute;
    }
}
