package arrays.min_moves;

// Minimum Moves to Equal Array Elements
// Given an integer array nums of size n,
// return the minimum number of moves required to make all array elements equal.
//
// In one move, you can increment n - 1 elements of the array by 1.
//
// n == nums.length
// 1 <= nums.length <= 10^5
// -10^9 <= nums[i] <= 10^9
// The answer is guaranteed to fit in a 32-bit integer.
public class MinMovesEqualArray {

    // TODO. 使用最小值来配平移动: 最终值 - 初始最小值 = 变化次数
    // nums = [1,2,3] -> 3
    // [1,2,3] => [2,3,3] => [3,4,3] => [4,4,4]
    //
    // 最终的结果是将最小值2变化到相等值6
    // nums = [2,3,5] -> 4
    // [2,3,5] => [3,4,5] => [4,5,5] => [5,6,5] => [6,6,6]
    //
    // nums = [1,3,5] -> 6
    // [1,3,5] => [2,4,5] => [3,5,5] => [4,6,5] => [5,6,6] => [6,7,6] => [7,7,7]
    public int minMoves(int[] nums) {
        int minValue = nums[0];
        for (int num : nums) {
            minValue = Math.min(minValue, num);
        }

        int result = 0;
        for (int num: nums) {
            result += (num - minValue);
        }
        return result;
    }
}
