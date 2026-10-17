package aarray;

public class LearnArray3 {

    // TODO: 左边总值 + 中间值 + 右边总值 = 数组和总值
    // Find Pivot Index
    // Given an array of integers nums, calculate the pivot index of this array
    // 数组的pivot左边和右边数的和相等，第一位置左边和最后一个位置的右边和都是0
    // Return the leftmost pivot index. If no such index exists, return -1
    // nums = [1,7,3,6,5,6] -> pivot = 3
    // nums = [2,1,-1]      -> pivot = 0
    //
    // O(2n)=O(n) O(1) 去掉辅助的数组所开辟的空间复杂度
    public int pivotIndex(int[] nums) {
        // 使用两遍循环，先计算出总值，右边的总和通过总值来计算
        int sum = 0;
        int leftSum = 0;
        for (int x : nums) {
            sum += x;
        }
        for (int i = 0; i < nums.length; ++i) {
            int rightSum = sum - leftSum - nums[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    // TODO: Array数组在初始化的时候，每个位置的初始值都是0，可以直接利用 !!
    // Plus One
    // Given a non-empty array of decimal digits representing a non-negative integer, increment one to the integer
    // Each element in the array contains a single digit
    // The integer does not contain any leading zero, except the number 0 itself
    // digits = [4,3,2,1] -> [4,3,2,2]  值的范围特点 0 <= digits[i] <= 9
    public int[] plusOne(int[] digits) {
        // 测试理解：1. 在从后往前+1的过程中，可能会涉及到进位，需要更改前面的值，最后可能比原始值多出一个长度 999 -> 1000
        //            O(n) O(n) 由于数组是定长的，所以需要额外的空间保证多多出来的最高位能够存储
        int index = digits.length - 1;
        int value = digits[index] + 1;
        while (value == 10) {
            digits[index] = 0;
            index--;
            if (index < 0) {
                int[] results = new int[digits.length + 1];
                results[0] = 1;
                // System.arraycopy(digits, 0, results, 1, digits.length); 不需要copy，因为能够进位到最高点，则必然后面都是0 !!
                return results;
            } else {
                value = digits[index] + 1;
            }
        }
        digits[index] = digits[index] + 1; // 在指定的非10的index位置plus one，这个index一定在有效的位置范围中 !!
        return digits;
    }

    // Sort Colors
    // Given an array nums with n objects colored red, white, or blue
    // Sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue
    // Integers 0, 1, and 2 to represent the color red, white, and blue, respectively
    // nums = [2,0,2,1,1,0] -> [0,0,1,1,2,2]
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int left = 0;                // left位置的左边一定是0
        int right = nums.length - 1; // right位置的右边一定是2
        for (int index = 0; index <= right; index++) {
            if (nums[index] == 0 && left < index) {
                swap(nums, left, index);
                index--;             // 交换过后需要退一步位置，判断交换过来的值 !!
                left++;
            }
            if (nums[index] == 2) {
                swap(nums, index, right);
                index--;
                right--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
