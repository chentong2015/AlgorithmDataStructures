package master.prefix_sum.subarray_sums;

import java.util.HashMap;

// Subarray Sum Equals K
// Given an array of integers nums and an integer k,
// return the total number of subarrays whose sum equals to k.
//
// A subarray is a contiguous non-empty sequence of elements within an array.
//
// 1 <= nums.length <= 2 * 10^4
// -1000 <= nums[i] <= 1000
// -10^7 <= k <= 10^7
public class SumSubarrayEqualsK {

    // TODO. 前缀和金典案例: 累加求和 + 统计剩余的差值
    // [1,1,1], k = 2 -> 2
    // [1,2,3], k = 3 -> 2
    // [-1,-1,1], k = 0 -> 1
    //
    // 统计有多少个diff差值，也将能组成多少个和为k值的子数组
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> subNumMap = new HashMap<>();
        subNumMap.put(0, 1);

        int total = 0;
        int count = 0;
        for (int n : nums) {
            total += n;

            // 累计符合条件的"余数值"
            if (subNumMap.containsKey(total - k)) {
                count += subNumMap.get(total - k);
            }

            int baseCount = subNumMap.getOrDefault(total, 0);
            subNumMap.put(total, baseCount + 1);
        }
        return count;
    }
}
