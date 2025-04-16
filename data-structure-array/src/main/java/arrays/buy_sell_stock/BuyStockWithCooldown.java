package arrays.buy_sell_stock;

// TODO. 股票交易数学模型: 买卖之间添加一天冻结
public class BuyStockWithCooldown {

    // Best Time to Buy and Sell Stock with Cooldown
    // You are given an array prices where prices[i] is the price of a given stock on the ith day.
    //
    // Find the maximum profit you can achieve. You may complete as many transactions as you like
    // (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
    // After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
    // You may not engage in multiple transactions simultaneously
    //
    // 1 <= prices.length <= 5000
    // 0 <= prices[i] <= 1000
    // [1,2,3,0,2] -> 3
    // [buy, sell, cooldown, buy, sell]
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        int[] sell = new int[len];
        int[] buy  = new int[len];
        buy[0] = - prices[0]; // 购买第一个必然负数
        buy[1] = - Math.min(prices[0], prices[1]); // 取两个中更小的值来购买
        sell[0] = 0; // 还没有卖出，必然为0
        sell[1] = Math.max(0, buy[0] + prices[1]); // 只能一买一出，或者不买卖

        for (int i = 2; i < len; i++){
            // 判断是否应该维持不变，还是从前面买卖后+冻结+再买入
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);

            // 判断是否应该维持不变，还是前面买入后+再卖出
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[len - 1];
    }
}
