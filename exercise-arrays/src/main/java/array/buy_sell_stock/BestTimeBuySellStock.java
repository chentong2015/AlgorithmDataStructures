package array.buy_sell_stock;

import java.util.Arrays;

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

    // TODO. 最多执行两笔交易: Buy时借钱，Sell时计算收益，再从收益中取购买第二次
    // Best Time to Buy and Sell Stock III
    // You are given an array prices where prices[i] is the price of a given stock on the ith day.
    // Find the maximum profit you can achieve.
    //
    // You may complete at most two transactions.
    // You may not engage in multiple transactions simultaneously
    // You must sell the stock before you buy again.
    //
    // [1,4,2] -> [1,4] -> 3
    // [1,2,3,4,5] -> [1,2] + [3,4] = 1+1=2
    // [3,3,5,0,0,3,1,4] -> [0,3] + [1,4] = 3+3=6
    // [1,2,4,2,5,7,2,4,9,0] -> [1,7] + [2,9] = 6+7=13
    public int maxProfit3(int[] prices) {
        int buy1 = Integer.MIN_VALUE;
        int sell1 = 0;
        int buy2 = Integer.MIN_VALUE;
        int sell2 = 0;

        // 不计算价格之间的差额，而是直接当作买入和卖出的投资
        for (int price : prices) {
            buy1 = Math.max(buy1, -price); // 借钱后余额
            sell1 = Math.max(sell1, buy1 + price); // 开始收益
            buy2 = Math.max(buy2, sell1 - price);  // 利用之前投资收益购买
            sell2 = Math.max(sell2, buy2 + price); // 获取新的收益
        }
        return sell2;
    }

    // TODO. 最多可以买卖K次: 迭代K次买入卖出的数据结果
    // Best Time to Buy and Sell Stock IV
    // You are given an integer array prices
    // where prices[i] is the price of a given stock on the ith day, and an integer k.
    //
    // You may complete at most k transactions:
    // you may buy at most k times and sell at most k times.
    // You may not engage in multiple transactions simultaneously
    //
    // prices = [2,4,1], k = 2 -> 2
    // prices = [3,2,6,5,0,3], k = 2 -> 4+3=7
    public int maxProfit(int k, int[] prices) {
        int[] profit = new int[k];
        int[] cost = new int[k];
        Arrays.fill(cost, Integer.MAX_VALUE);

        // 迭代花费和收益，使得Cost花费最小且Profit收益最大
        for(int currPrice : prices){
            cost[0] = Math.min(cost[0], currPrice);
            profit[0] = Math.max(profit[0], currPrice - cost[0]);
            for(int j = 1;  j < k; j++){
                cost[j] = Math.min(cost[j], currPrice - profit[j-1]);
                profit[j] = Math.max(profit[j], currPrice - cost[j]);
            }
        }
        return profit[k-1];
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
