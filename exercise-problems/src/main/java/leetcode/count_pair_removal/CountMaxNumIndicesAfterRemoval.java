package leetcode.count_pair_removal;

import java.util.Arrays;

// Find the Maximum Number of Marked Indices
// You are given a 0-indexed integer array nums.
// Initially, all the indices are unmarked.
//
// Pick two different unmarked indices i and j, such that 2 * nums[i] <= nums[j], then mark i and j.
// Return the maximum possible number of marked indices
// in nums using the above operation any number of times.
//
// 1 <= nums.length <= 10^5
// 1 <= nums[i] <= 10^9
public class CountMaxNumIndicesAfterRemoval {

    // TODO. 最多消除数目的本质是: 划分两半，对半相消
    // [3,5,2,4] -> 2
    // [0,1,1,0] 2 * nums[2] <= nums[1]
    //
    // [9,2,5,4] -> 4
    // [1,0,0,1] 2 * nums[3] <= nums[0]
    // [1,1,1,1] 2 * nums[1] <= nums[2]
    //
    // [7,6,8] -> 0
    //
    // [9,10,10,14,15,16,17,17,24,28,29,31,32,  40,42,48,51,55,64,68,71,83,98,99,99,100]
    // left                                     right
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);

        int left = 0;
        for (int right = (nums.length + 1) / 2; right < nums.length; right++) {
            if (2 * nums[left] <= nums[right]) {
                // 只有left位置的值能够被消除，才移动坐标
                left++;
            }
        }

        // 统计坐标移动的步数
        return left * 2;
    }
}
