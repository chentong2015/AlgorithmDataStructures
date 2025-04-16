package prefix_sum.subarray_sums;

import java.util.HashMap;

// Subarray Sum Equals K
// Given an array of integers nums and an integer k,
// return the total number of subarrays whose sum equals to k.
// A subarray is a contiguous non-empty sequence of elements within an array.
//
// 1 <= nums.length <= 2 * 10^4
// -1000 <= nums[i] <= 1000
// -10^7 <= k <= 10^7
public class SumSubarrayEqualsK {

    // TODO. Prefix Sum 前缀和：金典区间片段计算
    // [1,1,1], k = 2 -> 2
    // [1,2,3], k = 3 -> 2
    // [-1,-1,1], k = 0 -> 1
    public int subarraySum(int[] nums, int k) {
        // PrefixSum -> Count 哈希映射值为前缀和的统计
        HashMap<Integer, Integer> numSumMap = new HashMap<>();

        // 0 -> 1 初始化前缀和为0的统计为1
        numSumMap.put(0, 1);

        int total = 0;
        int count = 0;
        for (int n : nums) {
            total += n;

            // TODO. 统计有多少个diff差值，也将能组成多少个和为k值的子数组
            int diff = total - k;
            count += numSumMap.getOrDefault(diff, 0);

            int baseCount = numSumMap.getOrDefault(total, 0);
            numSumMap.put(total, baseCount + 1);
        }
        return count;
    }
}
