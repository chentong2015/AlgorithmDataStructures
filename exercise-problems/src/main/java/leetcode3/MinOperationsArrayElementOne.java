package leetcode3;

// Minimum Number of Operations to Make All Array Elements Equal to 1
// You are given a 0-indexed array nums consisting of positive integers.
// You can do the following operation on the array any number of times:
//  - Select an index i such that 0 <= i < n - 1
//  - Replace either of nums[i] or nums[i+1] with their gcd value.
// The gcd of two integers is the greatest common divisor of the two integers.
//
// Return the minimum number of operations to make all elements of nums equal to 1.
// If it is impossible, return -1.
//
// 2 <= nums.length <= 50
// 1 <= nums[i] <= 10^6
public class MinOperationsArrayElementOne {

    // TODO. 找到最快减到1的两个数据作为开始位置 + 所有的数目
    //  Try finding the shortest subArray with a gcd equal to 1.
    // [2,6,3,4] -> 4
    // i = 2 and replace nums[2] with gcd(3,4) = 1. Now we have nums = [2,6,1,4].
    // i = 1 and replace nums[1] with gcd(6,1) = 1. Now we have nums = [2,1,1,4].
    // i = 0 and replace nums[0] with gcd(2,1) = 1. Now we have nums = [1,1,1,4].
    // i = 2 and replace nums[3] with gcd(1,4) = 1. Now we have nums = [1,1,1,1].
    //
    // [2,10,6,14] -> -1
    //
    // [6,10,15] -> 1 + 3
    public int minOperations(int[] nums) {
        int g = 0;
        int one = 0;
        for (int num : nums) {
            if (num == 1) {
                one++;
            }
            // Compute gcd of all elements
            g = gcd(g, num);
        }
        // If Gcd of all elements is not 1, then we cannot produce
        if (g != 1) {
            return -1;
        }
        if (one > 0) {
            return nums.length - one;
        }

        // TODO. 关于Number Theory数论的计算逻辑 ?
        int ans = 100000;
        for (int i = 0; i < nums.length; i++) {
            g = 0;
            for (int j = i; j >= 0; j--) {
                g = gcd(g, nums[j]);
                // If GCD of the subarray becomes 1,
                // count the number of operations (i-j) + remaining elements (n-1)
                if (g == 1) {
                    ans = Math.min(ans, i-j + nums.length-1);
                    break;
                }
            }
        }
        return ans;
    }

    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}
