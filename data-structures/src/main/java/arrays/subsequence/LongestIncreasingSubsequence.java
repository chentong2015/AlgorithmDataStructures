package arrays.subsequence;

import java.util.Arrays;

// Longest Increasing Subsequence
// An integer array nums, return the length of the longest strictly increasing subsequence
// Can you come up with an algorithm that runs in O(n log(n)) time complexity?
//
// 1 <= nums.length <= 2500
// -10^4 <= nums[i] <= 10^4
public class LongestIncreasingSubsequence {

    // TODO. 每读取一个数据进行二分查找，存储从低到高的序列片段
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
    // dp: [0, 2, 12] 这里存储的不是正确的排序值，但是数组的长度是最终的答案
    //
    // O(nlog(n)) 二分查找时间复杂度
    // O(n)       DP数组可能存储全部数据
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int length = 0;
        for (int num : nums) {
            // Returns index of search key
            // Return (-(insertion point) - 1) if not found
            int index = Arrays.binarySearch(dp, 0, length, num);
            if (index < 0) {
                index = -(index + 1); // 重新计算要插入的点，可能直接追加到最后
            }

            dp[index] = num;          // 将读取值添加到dp数组中指定位置"insertion point"
            if (index == length) {    // 如果是插入到最后，则需要延申长度
                length++;
            }
        }
        return length; // length比插入的index坐标位置要多1
    }
}
