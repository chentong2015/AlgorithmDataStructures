package leetcode.count_pair_removal;

import java.util.List;

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
public class CountMinArrayLengthAfterRemoval {

    // TODO. 将数据对半划分成两侧，对两侧数据判断相消
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
    //
    // 由于数据严格的从小到大排序
    // 如果0位置和n/2+1位置的值无法消除，则[0,,,n/2+1]中的值全部相等
    // [0,,,,n/2, n/2+1,,,,n]
    public int minLengthAfterRemovals(List<Integer> nums) {
        int result = nums.size(); // 最大可能的剩余数目

        int length = nums.size();
        int left = 0;
        int index = (length + 1) / 2; // 从划分的后半段开始取值
        while (left < length / 2 && index < length) {
            if (nums.get(left) < nums.get(index)) {
                result -= 2;  // 两个值成一对，减少统计次数
            }
            left++;
            index++;
        }
        return result;
    }
}
