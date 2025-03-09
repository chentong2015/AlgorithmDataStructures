package arrays.prefix_sum.subarray_sums;

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

    // TODO. Prefix Sum前缀和：金典区间片段计算
    // [1,1,1], k = 2 -> 2
    // [1,2,3], k = 3 -> 2
    // [-1,-1,1], k = 0 -> 1
    public int subarraySum(int[] nums, int k) {
        // PrefixSum -> Count 哈希映射值为前缀和的统计
        // 0 -> 1 初始化前缀和为0的统计为1
        HashMap<Integer, Integer> subNum = new HashMap<>();
        subNum.put(0, 1);

        int total = 0;
        int count = 0;
        for (int n : nums) {
            total += n;
            if (subNum.containsKey(total - k)) {
                count += subNum.get(total - k);
            }

            int baseCount = subNum.getOrDefault(total, 0);
            subNum.put(total, baseCount + 1);
        }
        return count;
    }

    // TODO. 统计的数据全部为正数的情况
    // [1,1,1], k = 2 -> 2
    // [1,2,3], k = 3 -> 2
    public int subarraySum2(int[] nums, int k) {
        int result = 0;
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] == k) {
                result++;
            } else if (nums[index] < k) {
                int value = nums[index];
                int left = index - 1;
                while (left >= 0) {
                    value += nums[left];
                    if (value < k) {
                        left--;
                        continue;
                    }

                    // 一旦和为k，则无需再往前移动，sum和会越来越大
                    if (value == k) {
                        result++;
                    }
                    break;
                }
            }
        }
        return result;
    }
}
