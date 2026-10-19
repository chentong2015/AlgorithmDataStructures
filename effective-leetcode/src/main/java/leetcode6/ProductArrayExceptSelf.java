package leetcode6;

// Product of Array Except Self
// Given an integer array nums, return an array answer such that
// answer[i] is equal to the product of all the elements of nums except nums[i].
// The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
//
// You must write an algorithm that runs in O(n) time and without using the division operation.
// Can you solve the problem in O(1) extra space complexity ? The output array does not count.
//
// 2 <= nums.length <= 10^5
// -30 <= nums[i] <= 30
public class ProductArrayExceptSelf {

    // TODO. left乘积 * right乘积 = index结果
    // nums = [1,2,3,4] -> [24,12,8,6]
    // nums = [-1,1,0,-3,3] -> [0,0,9,0,0]
    //
    // 提前将左右两侧的乘积算出来
    //       1   2   3  4
    // left  1   1   2  6
    // right 24  12  4  1
    // =     24  12  8  6
    //
    // O(N+N) 只需要两遍循环
    // O(N)   存储结果的数组
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int product = 1;
        for (int index = n - 1; index >= 0; index--) {
            result[index] = product;
            product *= nums[index]; // right product
        }

        product = 1;
        for (int index = 0; index < n; index++) {
            result[index] = product * result[index]; // Left Product * Right Product
            product *= nums[index]; // left product
        }
        return result;
    }
}
