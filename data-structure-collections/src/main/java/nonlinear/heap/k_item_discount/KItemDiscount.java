package nonlinear.heap.k_item_discount;

import java.util.PriorityQueue;

public class KItemDiscount {

    // 先通过优先级队列对数据进行排序，然后应用K次折扣，最后再统计结果
    // Complexity:
    // • Building the heap: O(n).
    // • Applying m coupons: O(m log n). 最佳优先级
    // • Summing the remaining prices: 0(n).
    private static int findMinPriceWithKDiscount(int[] prices, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        for (int price: prices) {
            maxHeap.add(price);
        }

        while (k > 0) {
            int highestPrice = maxHeap.poll();
            int discountedPrice = highestPrice / 2;

            // 如果折扣后的价格为0，则无需支付
            if (discountedPrice > 0) {
                maxHeap.add(discountedPrice);
            }
            k--;
        }

        int totalCost = 0;
        while (!maxHeap.isEmpty()) {
            totalCost += maxHeap.poll();
        }
        return totalCost;
    }
}
