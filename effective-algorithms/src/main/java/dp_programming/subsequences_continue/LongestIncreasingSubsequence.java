package dp_programming.subsequences_continue;

import java.util.Arrays;
import java.util.Collections;

// Longest Increasing Subsequence (LIS)
// An integer array nums, return the length of the longest strictly increasing subsequence
// Can you come up with an algorithm that runs in O(n log(n)) time complexity?
//
// 1 <= nums.length <= 2500
// -10^4 <= nums[i] <= 10^4
public class LongestIncreasingSubsequence {

    // TODO. 针对DP数组进行二分查找，存储从低到高的序列片段
    // [10,9,2,5,3,7,101,18]
    // [2,3,7,101]
    //
    // [0,1,0,3,2,3]
    // [0,1,2,3]
    // 
    // [0, 8, 4, 12, 2]
    // dp: [0]
    // dp: [0, 8]
    // dp: [0, 4]
    // dp: [0, 4, 12] 
    // dp: [0, 2, 12] 存储的不是正确排序值，但是数组的长度是最终的答案
    //
    // O(n * log(n)) 二分查找时间复杂度
    // O(n)          DP数组可能存储全部数据
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int length = 0;
        for (int num : nums) {
            // If not found, return (-(insertion point) - 1)
            int index = Arrays.binarySearch(dp, 0, length, num);

            // 找到插入的位置进行替换，如果在末尾则扩容
            if (index < 0) {
                index = -(index + 1);
            }
            dp[index] = num;

            // TODO. 如果是在最后坐标插入，自然往后延长一个坐标
            if (index == length) {
                length++;
            }
        }
        return length;
    }
}
