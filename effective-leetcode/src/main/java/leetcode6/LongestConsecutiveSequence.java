package leetcode6;

import java.util.HashSet;
import java.util.Set;

// Longest Consecutive Sequence
// Given an unsorted array of integers nums,
// return the length of the longest consecutive elements sequence.
// You must write an algorithm that runs in O(n) time.
//
// 0 <= nums.length <= 10^5
// -10^9 <= nums[i] <= 10^9
public class LongestConsecutiveSequence {

    // TODO. 通过Set来快速判断相邻的值是否存在, 自动排除重复元素
    //
    // [100,4,200,1,3,2] -> [1, 2, 3, 4] -> 4
    // [0,3,7,2,5,8,4,6,0,1] -> 9
    // [1,0,1,2] -> 3
    //
    // O(N+N)
    // O(N)
    public int longestConsecutive(int[] nums) {
        if (nums.length < 2) {
            return nums.length; // 0 or 1
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int result = 0;
        for (int num : nums) {
            if (set.contains(num)) { // 从点往两侧移动, 不增加复杂度
                int length = 1;
                int temp = num;
                while (set.contains(temp + 1)) { // go up
                    set.remove(temp + 1);
                    length++;
                    temp++;
                }

                temp = num;
                while (set.contains(temp - 1)) { // go down
                    set.remove(temp - 1);
                    length++;
                    temp--;
                }

                set.remove(num);
                result = Math.max(result, length);
            }
        }
        return result;
    }
}
