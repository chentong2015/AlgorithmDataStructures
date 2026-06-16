package leetcode5;

import java.util.ArrayList;
import java.util.List;

// Summary Ranges
// You are given a sorted unique integer array nums.
// A range [a,b] is the set of all integers from a to b (inclusive).
// Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
//
// 0 <= nums.length <= 20
// -2^31 <= nums[i] <= 2^31 - 1
// All the values of nums are unique.
// nums is sorted in ascending order
public class SummaryRanges {

    // TODO. 双while循环确定查找的区间
    // nums = [0,1,2,4,5,7]
    // output = ["0->2","4->5","7"]
    //
    // O(N)
    // O(N)
    public List<String> summaryRanges(int[] nums) {
        List<String> ranges = new ArrayList<>();
        if (nums.length == 0) {
            return ranges;
        }

        int index = 0;
        while (index < nums.length) {
            int left = nums[index];
            int right = nums[index];

            // continue and move to right value
            int rightIndex = index + 1;
            while (rightIndex < nums.length && right + 1 == nums[rightIndex]) {
                right = nums[rightIndex];
                rightIndex++;
            }

            if (left == right) {
                ranges.add(String.valueOf(left));
            } else {
                ranges.add(left + "->" + right);
            }
            index = rightIndex;
        }

        return ranges;
    }
}
