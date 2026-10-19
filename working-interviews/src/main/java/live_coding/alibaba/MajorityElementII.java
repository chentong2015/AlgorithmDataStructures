package live_coding.alibaba;

import java.util.ArrayList;
import java.util.List;

// Majority Element II
// Given an integer array of size n, find all elements that appear more than [ n/3 ] times.
//
// 1 <= nums.length <= 5 * 10^4
// -10^9 <= nums[i] <= 10^9
public class MajorityElementII {

    // TODO. 多数元素的特征：1/3的数也是大多数，一组数据中超过1/3的数不可能超过2个
    // nums = [3,2,3] -> 3
    // nums = [1] -> 1
    // nums = [1,2] -> 1,2
    //
    // O(n) O(n) 使用HashMap<Integer, Integer>统计数据频率
    // O(n) O(1) 使用投票算法来降低空间复杂度
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;
        int candidate1 = 0;
        int candidate2 = 1;
        int count1 = 0;
        int count2 = 0;

        // 同时统计第一频率和第二频率的数据
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // 判断第一和第二频率的数据是否超过一定的比例
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }
        if (count1 > n / 3) res.add(candidate1);
        if (count2 > n / 3) res.add(candidate2);
        return res;
    }
}
