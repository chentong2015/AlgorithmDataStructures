package amazonTop50.subarrays_sum_total;

import java.util.ArrayDeque;
import java.util.Deque;

// Sum of Total Strength of Wizards
// Since the answer may be very large, return it modulo 10^9 + 7.
//
// 1 <= strength.length <= 10^5
// 1 <= strength[i] <= 10^9
public class SumSubarrayMultiMin {

    // TODO. 两个核心工具的使用 + 补充取模MOD计算
    // 单调栈: 确定哪些subarray归A[i]管, 对结果的贡献
    // 二重前缀和: 快速算这些subarray的sum加起来是多少
    //
    //                  单调栈
    //                    ↓
    //            找当前最小值的左右边界
    //          ┌─────────┴─────────┐
    //      leftCount            rightCount
    //          └─────────┬─────────┘
    //                 二重前缀和
    //           所有相关subarray的sum × A[i]
    //                  answer
    //
    public int totalStrength(int[] strength) {
        int n = strength.length;
        long[] prefixSum = new long[n + 1];
        long[] prefixSum2 = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = (prefixSum[i] + strength[i]);
            prefixSum2[i + 1] = (prefixSum2[i] + prefixSum[i + 1]);
        }

        long resultTotal = 0;
        Deque<Integer> stack = new ArrayDeque<>(); // Monotonic Stack

        for (int right = 0; right <= n; right++) { // right==n 用于清空单调栈
            while (!stack.isEmpty() && (right == n || strength[stack.peek()] > strength[right])) {
                int minIndex = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek(); // 左侧第一个更小值坐标

                long leftPrefixSum = left >= 0 ? prefixSum2[left] : 0;
                long leftPrefixSumTotal = prefixSum2[minIndex] - leftPrefixSum;
                long rightPrefixSumTotal = prefixSum2[right] - prefixSum2[minIndex];

                long leftCount = minIndex - left;
                long rightCount = right - minIndex;
                long sumOfAllSubarraySums = leftCount * rightPrefixSumTotal - rightCount * leftPrefixSumTotal;

                resultTotal += strength[minIndex] * sumOfAllSubarraySums;
            }
            if (right < n) {
                stack.push(right);
            }
        }
        return (int) resultTotal;
    }
}