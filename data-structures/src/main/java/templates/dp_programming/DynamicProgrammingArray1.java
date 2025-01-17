package templates.dp_programming;

public class DynamicProgrammingArray1 {

    // TODO. 动态编程核心: 保存之前历史遍历的有效数据
    // 输入一个数组，可以在任意位置买入或者卖出，计算最大收益值 & 最少亏损值
    // - 只能买卖一次
    // - 数组中均为整数，且至少有两个数
    // [3, 5, 4, 6, 3, 2, 9, 6, 9]  => 7 直接计算每个位置能够获取的最大收益
    private static int calculateMaxGain(int[] nums) {
        int minBefore = nums[0];
        int maxGain = Integer.MIN_VALUE;
        for (int index = 1; index < nums.length; index++) {
            maxGain = Math.max(maxGain, nums[index] - minBefore);
            if (nums[index] < minBefore) {
                minBefore = nums[index];
            }
        }
        return maxGain;
    }

    // TODO. 当允许多次买入和卖出时，该问题变成数学统计问题
    // Best Time to Buy and Sell Stock II
    // An array prices where prices[i] is the price of a given stock on the ith day
    // Find the maximum profit you can achieve，You must sell the stock before you buy again
    // For example: prices = [7,1,5,3,6,4] -> (5-1) + (6-3) -> 7
    //
    // 1. 穷举所有可能的transactions交易的可能，(递归法)提取出最大的值
    // 2. 峰谷法：将所有的山峰和山谷之间的差值累加(数学公式)
    // 3. "Simple One Pass": 本质上收益的总值(极限值)是等于所有增长的部分的和
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            // 本质上只要有上升就会有收益
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    // TODO. 问题的本质：动态编程，需要统计出格子中到达每一个位置的最短path值
    // Minimum Path Sum
    // Given a m x n grid filled with non-negative numbers,
    // find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
    // Note: You can only move either down or right at any point in time.
    //
    // O(n & m) O(1)
    // 1 3 1     1 4 5
    // 1 5 1 =>  2 7 6
    // 4 2 1     6 8 7
    public static int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                    // do nothing 起始位置没有必要做任何计算
                } else if (i > 0 && j == 0) {
                    grid[i][j] += grid[i-1][j];
                } else if (i == 0) {
                    grid[i][j] += grid[i][j-1];
                } else {
                    // 每一个位置只能从它的左边或者上面而来，每个位置都需要计算
                    grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
                }
            }
        }
        return grid[rows-1][cols-1];
    }

    public static void main(String[] args) {
        int[][] grid = {{1,3,1}, {1,5,1}, {4,2,1}};
        System.out.println(minPathSum(grid));
    }
}
