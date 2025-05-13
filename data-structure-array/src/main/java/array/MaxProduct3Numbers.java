package array;

import java.util.Arrays;

// Maximum Product of Three Numbers
// Given an integer array nums, find three numbers
// whose product is maximum and return the maximum product.
//
// 3 <= nums.length <= 104
// -1000 <= nums[i] <= 1000
public class MaxProduct3Numbers {

    // TODO. 只有两种可能的乘积是最大值
    // Product = (-Max1) * (-Max2) * MaxValue1
    // Product = MaxValue3 * MaxValue2 * MaxValue1
    //
    // 1,2,3 -> 6
    // 1,2,3,4 -> 24
    // -1,-2,-3 -> -6
    // -1,-2,-3,-4 -> -6
    // 0,-1,-2,-4 -> 0
    // -5,-3,-2,0,1,4,5 -> 75
    // -10,-8,-1,2,3,4 -> 320
    public int maximumProduct(int[] nums) {
        if (nums.length == 3) {
            return nums[0] * nums[1] * nums[2];
        }
        Arrays.sort(nums);

        int result = Integer.MIN_VALUE;
        int length = nums.length;
        if (nums[0] < 0 && nums[1] < 0 && nums[length - 1] > 0) {
            result = Math.max(result, nums[0] * nums[1] * nums[nums.length - 1]);
        }
        result = Math.max(result, nums[length - 3] * nums[length - 2] * nums[length - 1]);

        return result;
    }

    // TODO. 遍历时只需要知道两个最小值 + 三个最大值
    public int maximumProductGood(int[] nums) {
        // 初始化起始位置的最值情况
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n <= min1) { //
                min2 = min1;
                min1 = n;
            } else if (n <= min2) { // n lies between min1 and min2
                min2 = n;
            }

            if (n >= max1) {  // n is greater than max1, max2 and max3
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n >= max2) { // n lies between max1 and max2
                max3 = max2;
                max2 = n;
            } else if (n >= max3) { // n lies between max2 and max3
                max3 = n;
            }
        }
        // 假设这里的乘积结果不会越界
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }
}
