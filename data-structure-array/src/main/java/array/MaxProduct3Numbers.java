package array;

// Maximum Product of Three Numbers
// Given an integer array nums, find three numbers
// whose product is maximum and return the maximum product.
//
// 3 <= nums.length <= 104
//-1000 <= nums[i] <= 1000
public class MaxProduct3Numbers {

    // TODO. 只能有0个或2个负数(绝对值最大)
    // nums = [1,2,3] -> 6
    // nums = [1,2,3,4] -> 24
    // nums = [-1,-2,-3] -> -6
    //
    // O(n)
    // O(1)
    public int maximumProduct(int[] nums) {





        return 0;
    }









    // TODO: 如何转换问题，观察数据的特点
    //  在什么条件下结果值可能最大 ? 要么有两个最小的值(负数)*最大值，或者直接是数据中最大的3个值的乘积 !!
    // Maximum Product of Three Numbers
    // Given an integer array nums, find three numbers whose product is maximum and return the maximum product
    // [0, 1, 2, 3]           -> 6
    // [-12, -7, -1, 11, 17]  -> (-12)*(-7)*17
    public int maximumProductXX(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n <= min1) {
                min2 = min1;
                min1 = n;
            } else if (n <= min2) {     // n lies between min1 and min2
                min2 = n;
            }
            if (n >= max1) {            // n is greater than max1, max2 and max3
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n >= max2) {     // n lies between max1 and max2
                max3 = max2;
                max2 = n;
            } else if (n >= max3) {     // n lies between max2 and max3
                max3 = n;
            }
        }
        // 假设这里的乘积结果不会越界
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }
}
