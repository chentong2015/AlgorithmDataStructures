package arrays.sum_questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 4Sum
// Given an array nums of n integers
// Return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]]
// such that:
//   0 <= a, b, c, d < n
//   a, b, c, and d are distinct
//   nums[a] + nums[b] + nums[c] + nums[d] == target
// You may return the answer in any order.
//
// 1 <= nums.length <= 200
// -10^9 <= nums[i] <= 10^9
// -10^9 <= target <= 10^9
public class Four4Sum {

    // TODO. 排序后降维计算不同值的组合
    // [1,0,-1,0,-2,2], target = 0
    // [-2,-1,1,2]
    // [-2,0,0,2]
    // [-1,0,0,1]
    //
    // [0,0,0,5,-3,-4,1], target = -6
    // [[-4,-3,0,1]]
    //
    // [5,0,2,-5,-5,4,-5,1,-1],  target = -5
    // [[-5,-5,0,5],[-5,-5,1,4],[-5,-1,0,1]]
    //
    // -5 -5 -5 -1 0 1 2 4 5
    // a  b        c       d
    // a  b          c   d
    // a        b  c d
    //
    // O(N*logN + N*N*N)
    // O(?)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        for (int a = 0; a < nums.length; a++) {
            // nums[a]移动到下一个新值
            if (a > 0 && nums[a-1] == nums[a]) {
               continue;
            }

            int b = a + 1;
            while (b < nums.length) {
                // 内层是一个双指针的查找算法
                int c = b + 1;
                int d = nums.length - 1;
                while (c < d) {
                    // TODO. 注意SUM和值可能越界而变成负数-294967296
                    long sum = (long) nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum < (long) target) {
                        c++;
                    } else if (sum > (long) target) {
                        d--;
                    } else {
                        result.add(List.of(nums[a], nums[b], nums[c], nums[d]));

                        // nums[c]移动到下一个新值
                        c++;
                        while (c < d && nums[c-1] == nums[c]) {
                            c++;
                        }
                    }
                }

                // nums[b]移动到下一个新值
                b++;
                while (b < nums.length && nums[b-1] == nums[b]) {
                    b++;
                }
            }
        }

        return result;
    }
}
