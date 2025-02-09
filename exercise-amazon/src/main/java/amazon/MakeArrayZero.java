package amazon;

import java.util.HashSet;
import java.util.Set;

public class MakeArrayZero {

    // TODO. 判断结果和数据的“什么特征”有关系，直接使用特征来得出结果(和数据多少以及大小无关)
    // Make Array Zero by Subtracting Equal Amounts
    // You are given a non-negative integer array nums. In one operation, you must:
    // - Choose a positive integer x
    //   such that x is less than or equal to the smallest non-zero element in nums
    // - Subtract x from every positive element in nums.
    //
    // Return the minimum number of operations to make every element in nums equal to 0.
    //
    // nums = [1,5,0,3,5] -> 3
    // choose x = 1. Now, nums = [0,4,0,2,4].
    // choose x = 2. Now, nums = [0,2,0,0,2].
    // choose x = 2. Now, nums = [0,0,0,0,0].
    //
    // 每次选择nums中最小的数据，才能用最少的次数将所有的数据清0
    // 最终需要操作的number次数 = 不同数据的个数，有多少中不同的数据则需要消除多少次
    //
    // 1 2 3 4 6
    // 0 1 2 3 5
    // 0 0 1 2 4
    // 0 0 0 1 3
    // 0 0 0 0 2
    // 0 0 0 0 0
    //
    // 1 3 5 5
    // 0 2 4 4
    // 0 0 2 2
    // 0 0 0 0
    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            if (num != 0 && !set.contains(num)) {
                set.add(num);
            }
        }
        return set.size();
    }
}
