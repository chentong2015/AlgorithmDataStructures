package basic.iteration;

import java.util.List;

// Sorting Three Groups
// You are given an integer array nums. Each element in nums is 1, 2 or 3.
// In each operation, you can remove an element from nums
//
// Return the minimum number of operations to make nums non-decreasing.
//
// 1 <= nums.length <= 100
// 1 <= nums[i] <= 3
public class SortingThreeGroups {

    // TODO. 递归推理: 让每一步的计算结果最优
    // [2,1,3,2,1] -> [1,2] -> 3
    // the optimal solutions is to remove nums[0], nums[2] and nums[3].
    //
    // [1,3,2,1,3,3] -> [1,1,3,3] -> 2
    // the optimal solutions is to remove nums[1] and nums[2].
    //
    // 推导sort1:
    //   如果x == 1，可以保留它，不需要增加删除次数
    //   如果x != 1，必须删除它，删除次数加一
    // 推导sort12:
    //   如果x != 2，最优结果可以来自sort1对应的方案
    //   如果x == 2，最优结果可以来自sort12对应的方案
    // 推导sort123:
    //   如果x != 3，最优结果可以来自sort12对应的方案
    //   如果x == 3，最优结果可以来自sort123对应的方案
    //
    public int minimumOperations(List<Integer> nums) {
        int sort1 = 0;    // 全部变成1的操作数
        int sort12 = 0;   // 全部变成1,2的操作数
        int sort123 = 0;  // 全部变成1,2,3的操作数
        for (int x: nums) {
            sort1 = sort1 + (x == 1 ? 0 : 1);
            sort12 = Math.min(sort1, sort12 + (x == 2 ? 0 : 1));
            sort123 = Math.min(sort12, sort123 + (x == 3 ? 0 : 1));
        }
        return sort123;
    }
}
