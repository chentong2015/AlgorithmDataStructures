package leetcode;

import java.util.Arrays;

// Minimum Number Game
// Alice and Bob decided to play a game where in every round Alice and Bob will do one move.
// The rules of the game are as follows:
//
// 1. first Alice will remove the minimum element from nums, and then Bob does the same.
// 2. first Bob will append the removed element in the array arr, and then Alice does the same.
// 3. The game continues until nums becomes empty.
// Return the resulting array arr.
//
// 2 <= nums.length <= 100
// 1 <= nums[i] <= 100
// nums.length % 2 == 0
public class MinimumNumberGame {

    // TODO. 对数组进行的排序后再颠倒相邻位置的数据
    //
    // [5,4,2,3] -> [3,2,5,4]
    // [2,5] -> [5,2]
    //
    // O(n*log(n))
    // O(1)
    public int[] numberGame(int[] nums) {
        Arrays.sort(nums);
        int index = 0;
        while (index < nums.length - 1) {
            int temp = nums[index];
            nums[index] = nums[index +1];
            nums[index +1] = temp;
            index += 2;
        }
        return nums;
    }
}
