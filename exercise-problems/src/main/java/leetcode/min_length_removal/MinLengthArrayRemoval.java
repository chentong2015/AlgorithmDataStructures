package leetcode.min_length_removal;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// Minimum Array Length After Pair Removals
// Given an integer array num sorted in non-decreasing order.
//
// Perform the following operation any number of times:
// - Choose two indices, i and j, where nums[i] < nums[j].
// - Remove the elements at indices i and j from nums.
// The remaining elements retain their original order, and the array is re-indexed.
//
// Return the minimum length of nums after applying the operation zero or more times.
//
// 1 <= nums.length <= 10^5
// 1 <= nums[i] <= 10^9
// nums is sorted in non-decreasing order.
public class MinLengthArrayRemoval {

    // TODO. 将数据对半划分成两侧，对两侧数据判断相消即是最优解 ！
    // nums = [1,2,3,4] -> 0
    // nums = [1,1,2,2,3,3] -> 0
    // nums = [10,10] -> 2
    // nums = [2,3,4,4,4] -> [4] -> 1
    //
    // 1,1,1,1,2, 2,3,3,3,3
    // 1 1 1 2  3 3 3 3
    // 1 1 2  3 3 3
    // 1 2   3 3
    // 2   3
    public int minLengthAfterRemovals(List<Integer> nums) {
        int length = nums.size();
        int result = nums.size(); // 最大可能的剩余数目

        int left = 0;
        int index = (length + 1) / 2; // 从划分的后半段开始取值
        while (left < length / 2 && index < length) {
            if (nums.get(left) < nums.get(index)) {
                result -= 2;  // 相消后减少统计次数, 两个值成一对
            }
            left++; // 再同时往右边移动
            index++;
        }
        return result;
    }
}
