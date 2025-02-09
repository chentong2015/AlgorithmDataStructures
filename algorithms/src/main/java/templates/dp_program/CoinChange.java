package templates.dp_program;

import java.util.Arrays;

public class CoinChange {

    // Coin Change 只用最少的零钱数目来凑出指定的值, 假设每种零钱的数目是足够的
    // An integer array coins representing coins
    // An integer amount representing a total amount of money
    // Return the fewest number of coins that you need to make up that amount
    // coins = {2, 5}       target = 6   ->  Expected: 3 (2)
    //     1 2 3 4 5 6
    //   0 7 1 7 2 1
    // 2
    // 5
    //
    // coins = {1, 3, 4, 5} target = 7   ->  Expected: 2 (3, 4)
    //     1 2 3 4 5 6 7
    //   0 1 2 1 1 1 2 2 => 最后推导到指定数据的位置
    // 1   ?
    // 3
    // 4
    // 5 提供的coins的数据可以映射到dp数组的坐标，通过坐标反推之前的位置 ！
    public static int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // 设置一个最大值，用于最小值的判断
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) { // TODO. 找到第二层遍历坐标的方式
                if (coin <= i) {
                    int beforeIndex = i - coin;
                    dp[i] = Math.min(dp[i], dp[beforeIndex] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
