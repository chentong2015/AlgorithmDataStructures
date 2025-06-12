package array;

// Move Zeroes
// Given an integer array nums, move all 0's to the end of it while
// maintaining the relative order of the non-zero elements.
//
// 1 <= nums.length <= 104
// -2^31 <= nums[i] <= 2^31 - 1
public class MoveZeroes {

    // 直接使用原数组移动，最后补齐末尾的0
    // nums = [0,1,0,3,12] -> [1,3,12,0,0]
    // nums = [6,1,7,3,12] -> [6,1,7,3,12]
    public static void moveZeros1(int[] nums) {
        int left = 0;
        for (int index=0; index < nums.length; index++) {
            if (nums[index] != 0) {
                nums[left] = nums[index];
                left++;
            }
        }
        for (int index = left; index < nums.length; index++) {
            nums[index] = 0;
        }
    }
}
