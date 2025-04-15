package arrays.prefix_sum;

import java.util.HashMap;
import java.util.List;

// Count of Interesting Subarrays
// You are given a 0-indexed integer array nums, an integer modulo, and an integer k.
// Find the count of subarrays that are interesting.
//
// 参数只是用于约束cnt的判断
// Let cnt be the number of indices i in the range [l, r]
// - nums[i] % modulo == k.
// - cnt % modulo == k.
// Return an integer denoting the count of interesting subarrays.
//
// 1 <= nums.length <= 10^5
// 1 <= nums[i] <= 10^9
// 1 <= modulo <= 10^9
// 0 <= k < modulo
public class CountInterestingSubarrays {

    // TODO. Prefix Sum 前缀和：金典差值统计
    // nums = [3,2,4], modulo = 2, k = 1 -> 3
    // [3]     -> 3%2=1, 1%2 == 1 -> cnt=1
    // [3,2]   -> 3%2=1, 1%2 == 1 -> cnt=1
    // [3,2,4] -> 3%2=1, 1%2 == 1 -> cnt=1
    //
    // nums = [3,1,9,6], modulo = 3, k = 0 -> 2
    // [3,1,9,6] -> nums[i]%3 = 0, 3%3 = 0 -> cnt = 3
    // [1]       -> 0%3 = 0, 0%3 = 0 -> cnt = 0
    //
    // 3  2  4  3  / 1, 3, 5, 7,,,, cnt
    // 1  1  1  2  = prefix sum
    //
    // Time O(n)
    // Space O(mod) 只需要存储module大小的统计
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        // Means the number of prefix array that have acc % mod == k.
        // initial count[0] = 1 for empty prefix subarray.
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        // Means the number of nums[i] % mod == k in i + 1 first elements.
        int prefixSum = 0;

        long result = 0;
        for (int num : nums) {
            // 只考虑计算cnt统计的余数
            int additionCnt = num % modulo == k ? 1 : 0;
            prefixSum = (prefixSum + additionCnt) % modulo;

            // TODO. 注意非0余数的计算
            int key = (prefixSum - k + modulo) % modulo;
            result += map.getOrDefault(key, 0);

            int baseCount = map.getOrDefault(prefixSum, 0);
            map.put(prefixSum, baseCount + 1);
        }
        return result;
    }
}
