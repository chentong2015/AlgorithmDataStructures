package leetcode3;

// Maximum Strength of a Group
// You are given a 0-indexed integer array nums representing the score of students in an exam.
// The teacher would like to form one non-empty group of students with maximal strength,
// where the strength of a group of students of indices i0, i1, i2, ... , ik
// is defined as nums[i0] * nums[i1] * nums[i2] * ... * nums[ik].
// Return the maximum strength of a group the teacher can create.
//
// 1 <= nums.length <= 13
// -9 <= nums[i] <= 9
public class MaximumStrengthOfGroup {

    // TODO. 只有当负数的数量为偶数时才能保证最大结果
    //  所有的正数都相成，最多只遗留一个最大的负数，0不考虑
    // [3,-1,-5,2,5,-9]
    // 3 * (-5) * 2 * 5 * (-9) = 1350
    //
    // [-4,-5,-4]
    // (-4) * (-5) = 20
    //
    // [0,-1] -> 0
    // [3,-2] -> 3
    // [0,-5,0] -> 0
    // [0,0,0,0,0,0] -> 0
    public long maxStrength(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        long result = 1;
        int maxValueNegative = Integer.MIN_VALUE;
        int countNegative = 0;
        int countPositive = 0;
        boolean hasZero = false;

        // 在遍历时统计出最后的乘积
        for (int num: nums) {
            if (num > 0) {
                countPositive++;
                result *= num;
            } else if (num < 0) {
                countNegative++;
                maxValueNegative = Math.max(maxValueNegative, num);
                result *= num;
            } else {
                hasZero = true;
            }
        }

        // 特殊情况: 当没有正数且只有一个负数时，返回0作为最大值
        if (countPositive == 0) {
            if (countNegative <= 1 && hasZero) {
                return 0;
            }
        }

        // 普通情况: 排除最大的负数值，即为最后的结果
        if (countNegative % 2 == 1) {
            result /= maxValueNegative;
        }
        return result;
    }
}
