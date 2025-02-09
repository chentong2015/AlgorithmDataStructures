package arrays.array.buy_sell_stock;

public class BestTimeBuySellStock {

    // TODO. 单次买卖: 判断每个位置能够获的最大收益
    // Best Time to Buy and Sell Stock
    // You are given an array prices where prices[i] is the price of a given stock on the ith day.
    // You want to maximize your profit by choosing a single day to buy one stock
    // and choosing a different day in the future to sell that stock.
    //
    // Return the maximum profit you can achieve from this transaction.
    // If you cannot achieve any profit, return 0.
    //
    // [7,1,5,3,6,4] -> [1,6] -> 5
    public int maxProfit(int[] prices) {
        int minBefore = prices[0];
        int maxGain = Integer.MIN_VALUE;
        for (int index = 1; index < prices.length; index++) {
            int currentPrice = prices[index];
            if (currentPrice > minBefore) {
                maxGain = Math.max(maxGain, currentPrice - minBefore);
            } else {
                minBefore = currentPrice;
            }
        }
        return maxGain == Integer.MIN_VALUE ? 0: maxGain;
    }

    // TODO. 任意次买卖: 数学统计有增值空间的大小, 本质上只要有上升就会有收益
    // Best Time to Buy and Sell Stock II
    // You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
    // On each day, you may decide to buy and/or sell the stock.
    //
    // You can only hold at most one share of the stock at any time.
    // However, you can buy it then immediately sell it on the same day.
    // Find and return the maximum profit you can achieve.
    //
    // [7,1,5,3,6,4] -> 4+3=7
    // [1,2,3,4,5] -> 1+1+1+1=4
    public int maxProfit2(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                // 将所有上升的收益累计到一起
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }
}
