package subsequence;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    // TODO: DP典型实例：结合二分法修改记录的历史数据，对dp数据进行扩充
    // 最长连续增长子序列，可以演变为最长满足条件的增长
    // Longest Increasing Subsequence
    // An integer array nums, return the length of the longest strictly increasing subsequence
    // nums  = [10,9,2,5,3,7,101,18] -> [2,3,7,101] 最长连续增长子序列, 数字之间可以不连续
    // steps = [1, 1,1,2,2,3,4  ,4]
    //
    // Dynamic Programming with Binary Search: O(nlog(n)) O(n)
    // Store increasing subsequence formed by including currently encountered element
    // input: [0, 8, 4, 12, 2] 数组需要预留足够的长度
    //    dp: [0]
    //    dp: [0, 8]
    //    dp: [0, 4]
    //    dp: [0, 4, 12]
    //    dp: [0, 2, 12] 这里存储的不是正确的排序值，但是数组的长度是最终的答案
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
