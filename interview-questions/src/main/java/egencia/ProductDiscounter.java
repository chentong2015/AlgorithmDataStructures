package egencia;

public class ProductDiscounter {

    // TODO. 只需要一次遍历所有的商品，不需要遍历两次
    // 所有的商品中，只有最贵的价格可以享受折扣discount
    // 当有多个相同的最贵价格的商品时，只能计算折扣一次
    // 2 4 5 6 9 9 4 5 6
    // maxPrice = 9
    public static int countTotalPrice(int[] prices, int discount) {
        if (prices.length == 1) {
            return fireDiscount(prices[0], discount);
        }
        int maxPrice = prices[0];
        int totalPrice = 0;
        for (int price: prices) {
            if (price > maxPrice) {
                maxPrice = price;
            }
            totalPrice += price;
        }
        // 最后只需要调整计算的总价
        return totalPrice - maxPrice + fireDiscount(maxPrice, discount);
    }

    // 折扣的约束, 注意考虑边界值: 0 <= discount <= 100
    private static int fireDiscount(int price, int discount) {
        if (discount == 0) {
            return price;
        } else if (discount == 100) {
            return 0;
        } else {
            // 地板函数: 计算中出现浮点小数时，向下取整数
            return (int) Math.floor(price * (100 - discount) / 100);
        }
    }
}
