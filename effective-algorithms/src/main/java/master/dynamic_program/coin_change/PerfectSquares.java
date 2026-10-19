package master.dynamic_program.coin_change;

import java.util.Arrays;

// Perfect Squares
// Given an integer n, return the "the least number" of perfect square numbers that sum to n
// A perfect square is an integer that is the square of an integer:
// 1 4 9 16 25 ...
public class PerfectSquares {

    // TODO. 从一堆整数平方值中取出来构成目标值
    //
    // n = 12 -> 4 + 4 + 4 -> 3
    // n = 13 -> 4 + 9 -> 2
    //
    // 0 1 2 3 4 5 6 7 8 9 10 11 12
    // 0 1
    //     2
    //       3
    //         1
    //         ...    1+1       1+1+1
    //
    public int numSquares2(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // 0的位置始终可达，不需要值

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                // 推理是否能从前面index坐标j*j到达，取最短到达步数
                int indexBefore = i - j * j;
                dp[i] = Math.min(dp[indexBefore] + 1, dp[i]);
            }
        }
        return dp[n];
    }
}
