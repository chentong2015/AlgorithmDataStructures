package collections.stack.next_smaller;

import java.util.Stack;

// TODO. 借助Stack来暂存Index位置后面第一个比它小的数据来计算
public class NextSmallerDiscount {

    // Final Prices With a Special Discount in a Shop
    // You are given an integer array prices where prices[i] is the price of the i^th item in a shop.
    //
    // There is a special discount for items in the shop.
    // If you buy the i^th item, then you will receive a discount equivalent to prices[j]
    // where j is the minimum index such that j > i and prices[j] <= prices[i].
    // Otherwise, you will not receive any discount at all.
    //
    // Return an integer array answer where answer[i] is the final price
    // you will pay for the ith item of the shop, considering the special discount.
    //
    // prices = [8,4,6,2,3] -> [4,2,4,2,3]
    // prices = [1,2,3,4,5] -> [1,2,3,4,5]
    // prices = [10,1,1,6] -> [9,0,1,6]
    //
    // O(n) O(n) 在遍历时判断i位置后面的j位置的值(如果存在j位置的话)
    private static int[] getFinalDiscountPrices(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        for (int index=prices.length - 1; index >= 0; index--) {
            while (!stack.empty() && stack.peek() > prices[index]) {
                stack.pop();
            }
            int oldValue = prices[index];
            if (!stack.empty()) {
                // If there is a small price after index, then calculate discount
                // 不能从stack从移除，这个后面更小的数据可能后续还能用于计算
                prices[index] -= stack.peek();
            }
            stack.push(oldValue);
        }
        return prices;
    }

    public static void main(String[] args) {
        int[] prices1 = {8,4,6,2,3};
        int[] prices2 = {1,2,3,4,5};
        int[] prices3 = {10,1,1,6};

        int[] result1 = getFinalDiscountPrices(prices1);
        int[] result2 = getFinalDiscountPrices(prices2);
        int[] result3 = getFinalDiscountPrices(prices3);

        for (int i: result1) {
            System.out.println(i);
        }
        System.out.println("---");
        for (int i: result2) {
            System.out.println(i);
        }
        System.out.println("---");
        for (int i: result3) {
            System.out.println(i);
        }
    }
}
