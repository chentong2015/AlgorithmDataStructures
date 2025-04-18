package hash_map;

import java.util.HashMap;
import java.util.Map;

// Two Sum
// Given an array of integers nums and an integer target
// Return indices of the two numbers such that they add up to target
//
// 2 <= nums.length <= 10^4
// -10^9 <= nums[i] <= 10^9
// -10^9 <= target <= 10^9
// Only one valid answer exists.
public class TwoSumTarget {

    // TODO: 使用HashMap<num, index>存储值和对应的index位置
    // 数组中的元素只能使用一次，有且仅有一个解
    // nums = [3,2,4], target = 6 -> [1,2]
    // nums = [3,3],   target = 6 -> [0,1]
    //
    // O(n)
    // O(n)
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No solution");
    }
}
