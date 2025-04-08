package arrays.buy_sell_stock;

import java.util.Arrays;

// TODO. 股票交易数学模型: 当前的买卖必须基于之前买卖的结果
public class BestTimeBuySellStock2 {

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
            buy1 = Math.max(buy1, -price);         // 借钱后余额
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

        // 循环迭代K次，使得花费最小且收益最大
        for(int price : prices){
            cost[0] = Math.min(cost[0], price);
            profit[0] = Math.max(profit[0], price - cost[0]);
            for(int j = 1; j < k; j++){
                cost[j] = Math.min(cost[j], price - profit[j-1]);
                profit[j] = Math.max(profit[j], price - cost[j]);
            }
        }
        return profit[k-1];
    }
}
