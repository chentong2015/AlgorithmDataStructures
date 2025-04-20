package arrays.sum_questions;

import java.util.*;

// 3Sum Question
// return all the triplets [nums[i], nums[j], nums[k]]
// Such that i!=j, i!=k, and j!=k, and nums[i]+nums[j]+nums[k] == 0
// Solution set must not contain duplicate triplets
//
// 3 <= nums.length <= 3000
// -10^5 <= nums[i] <= 10^5
public class Three3SumII {

    // TODO: 由于需要返回不同值的组合结果，必须排序
    //  然后固定一个值，将3Sum问题降维到2Sum问题
    // [-1,0,1,2,-1,-4] -> [[-1,-1,2],[-1,0,1]]
    // [0,1,1] -> []
    // [0,0,0] -> [0,0,0]
    //
    // -4 -1 -1 0 1 2
    // i  j         k
    //   i      j k
    //
    // O(logN + N*N)  降维后的复杂度
    // O(N/2)         最多可能有n/2种不同的结果组合
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // i循环到下一个不同值，以找到不同值的组合
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            // TODO. 内层本质是twoSumSorted问题: 双指针算法
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int total = nums[i] + nums[j] + nums[k];
                if (total > 0) {
                    k--;
                } else if (total < 0) {
                    j++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));

                    // j循环到下一个不同值，以找到不同值的组合
                    j++;
                    while (nums[j] == nums[j-1] && j < k) {
                        j++;
                    }
                }
            }
        }
        return res;
    }
}
