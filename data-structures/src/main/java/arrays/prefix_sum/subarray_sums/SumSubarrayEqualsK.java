package arrays.prefix_sum.subarray_sums;

import java.util.HashMap;

// TODO. 统计的数据可能有正，也可能有负数的情况
// Subarray Sum Equals K
// Given an array of integers nums and an integer k,
// return the total number of subarrays whose sum equals to k.
// A subarray is a contiguous non-empty sequence of elements within an array.
//
// [1,1,1], k = 2 -> 2
// [1,2,3], k = 3 -> 2
// [-1,-1,1], k = 0 -> 1
//
// 1 <= nums.length <= 2 * 10^4
// -1000 <= nums[i] <= 1000
// -10^7 <= k <= 10^7
public class SumSubarrayEqualsK {

    public static void main(String[] args) {
        int[] nums = {-1,1,-1,1,-1,1,1,-1};
        SumSubarrayEqualsK equalsK = new SumSubarrayEqualsK();
        System.out.println(equalsK.subarraySum(nums, 2));
    }

    // TODO. 利用Total总值减去片段区间，从而组合出所有Subarray区间的和
    //  sum(i,j)= sum(0,j) - sum(0,i)
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> subNum = new HashMap<>();

        // TODO. 保证total==k时hashmap中key=0有值
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
