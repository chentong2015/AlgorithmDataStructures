package templates.dp_programming;

import java.util.Arrays;

public class PerfectSquares {

    // Perfect Squares
    // Given an integer n, return the "the least number" of perfect square numbers that sum to n
    // A perfect square is an integer that is the square of an integer: 1 4 9 16 25 ...
    // Input: n = 12 -> 12 = 4 + 4 + 4 -> 3 最少需要3个perfect square数值组成
    //
    //   1 2 3 4 5 6 7 8 9 10 11 12
    // * * * * * * * * * *  *  *  *
    // 0
    // 0 1
    // 0 1 2
    // 0 1 2 3
    // 0 1 2 3 1 => i-j*j 计算可以完全除尽，因此存储的结果为0+1
    //         j/i
    public int numSquares2(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        // 初始位置为0是为了(i - j * j)刚好整除的时候，取基础值
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            // 每一个index位置只有两种拆分可能
            // 1. 从它的前一个位置记录的值+1组合而来
            // 2. 从它的前面平方根记录的位置+1
            for (int j = 1; j * j <= i; j++) {  // TODO. 找到第二层遍历坐标的方式
                int beforeIndex = i - j * j;
                dp[i] = Math.min(dp[i], dp[beforeIndex] + 1);
            }
        }
        return dp[n];
    }
}
