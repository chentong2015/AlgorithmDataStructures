package data_structure.dynamic_program.one_dimension;

import java.util.Arrays;

// Coin Change
// An integer array coins representing coins
// An integer amount representing a total amount of money
// Return the fewest number of coins that you need to make up that amount
// 只用最少的零钱数目来凑出指定的值, 假设每种零钱的数目足够
public class CoinChange {

    // TODO. 累计前面每一个金额的最佳划分, 最后amount划分时取其中最小可能
    // coins = {2, 5}, target = 6 -> Expected: 3 (2)
    //     1 2 3 4 5 6
    //   0 7 1 7 2 1
    //
    // coins = {1, 3, 4, 5}, target = 7 -> Expected: 2 (3, 4)
    //     1 2 3 4 5 6 7
    //   0 1 2 1 1 1 2 2
    //
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // 每个位置初始值设置amount+1
        dp[0] = 0; // 当i-coin整除时的基础值

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    // 推倒过程: 扣除coin后的钱 + 1
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
