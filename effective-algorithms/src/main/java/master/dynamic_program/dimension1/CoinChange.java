package master.dynamic_program.dimension1;

import java.util.Arrays;

// Coin Change
// An integer array coins representing coins
// An integer amount representing a total amount of money
//
// Return the fewest number of coins that you need to make up that amount
// If that amount of money cannot be made up by any combination of the coins, return -1.
//
// You may assume that you have an infinite number of each kind of coin.
// 1 <= coins.length <= 12
// 1 <= coins[i] <= 2^31 - 1
// 0 <= amount <= 104
public class CoinChange {

    // TODO. DP从前面所有计算过的(i-coin)位置推理出i位置的最小交换方式
    // {2, 5}, 6 -> Expected: 3 (2)
    // 0 1 2 3 4 5 6
    // 0 7 1 7 2 1 3
    //
    // coins = {1, 3, 4, 5}, target = 7 -> Expected: 2 (3, 4)
    // 0 1 2 3 4 5 6 7
    // 0 1 2 1 1 1 2 2
    //
    // O(N*A) A is amount value
    // O(A)
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);

        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i-coin] + 1);
                }
            }
        }

        // 判断dp[amount]是否被有效的修改过
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
