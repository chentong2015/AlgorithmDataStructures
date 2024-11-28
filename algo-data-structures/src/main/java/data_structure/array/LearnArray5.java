package data_structure.array;

import java.util.HashSet;

public class LearnArray5 {

    // 找出int类型的数组中最接近0的值，如果有两个同距离的值，则返回正数
    // [-2, 3, 2, 5, -1] => -1
    // [-2, 3, 5] => -2
    //
    // 一个值记录比较的最小绝对值，一个值记录最后的结果
    // 只有两种条件下才会更新最后的result
    // O(n) O(1)
    static int closestToZero(int[] ints) {
        if (ints == null || ints.length == 0) {
            return 0;
        }
        int result = 0;
        int closestAbs = Integer.MAX_VALUE;
        for (int i : ints) {
            if (Math.abs(i) < closestAbs) {
                closestAbs = Math.abs(i);
                result = i;
            } else if (Math.abs(i) == closestAbs && i > 0) {
                result = i;
            }
        }
        return result;
    }

    // TODO. 循环数组：从其中框选k段的长度, 保证截取的长度中包含开头或者结尾index
    // Maximum Points You Can Obtain from Cards
    // The points are given in the integer array card Points
    // In one step, you can take one card from the beginning or from the end of the row.
    // You have to take exactly k cards.
    // Return the maximum score you can obtain.
    //
    // [1,2,3,4,5,6,1], k = 3 -> 1 + 6 + 5 = 12
    // O(k) O(1) 只需要在数组中循环k个位置的值
    public int maxScore(int[] cardPoints, int k) {
        int maxScore = 0;
        for (int index = 0; index <k; index++) {
            maxScore += cardPoints[index];
        }

        // 循环数组：每移动一个位置，重新计算累计的总和
        int left = k - 1;
        int index = 0;
        while (index < k) {
            int newScore = maxScore - cardPoints[left] + cardPoints[cardPoints.length - 1 - index];
            maxScore = Math.max(maxScore, newScore);
            left--;
            index++;
        }
        return maxScore;
    }

    // TODO: 数组金典案列: 数组Index坐标就是连接的正整数，判断正整数是否在对应的Index位置
    // First Missing Positive
    // Given an unsorted integer array nums, find the smallest missing positive integer
    // You must implement an algorithm that
    // - Runs in O(n) time
    // - Uses O(1) constant extra space
    //
    // nums = [1,2,0] -> 3
    // nums = [3,4,-1,1] -> 2    必须考虑负数的情况
    // nums = [7,8,9,11,12] -> 1 从最小的正数开始考虑
    public int firstMissingPositive(int[] nums) {
        // 第一遍调整值的位置，交换移动到index位置
        for (int index=0; index < nums.length; index++) {
            int value = nums[index];
            if (value < 1 || value > nums.length || value == index + 1) {
                continue;
            }
            // 如果目标位置满足条件，则无需再交换过去，避免Time Limit Exceeded
            int targetPosition = value - 1;
            if (nums[targetPosition] == value) {
                continue;
            }
            int temp = nums[targetPosition];
            nums[targetPosition] = value;
            nums[index] = temp;
            // Move back forwards of index
            index--;
        }

        // 第二遍值和index位置不匹配的点
        for (int index=0; index < nums.length; index++) {
            if (index != nums[index] - 1) {
                return index + 1;
            }
        }
        return nums.length + 1;
    }
}
