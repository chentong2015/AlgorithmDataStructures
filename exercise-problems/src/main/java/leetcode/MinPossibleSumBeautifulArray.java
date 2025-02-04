package leetcode;

// Find the Minimum Possible Sum of a Beautiful Array
// You are given positive integers n and target.
// An array nums is beautiful if it meets the following conditions:
//  nums.length == n.
//  nums consists of pairwise distinct positive integers.
//  There doesn't exist two distinct indices, i and j, nums[i] + nums[j] == target.
// Return the minimum possible sum that a beautiful array could have modulo 10^9 + 7.
//
// 1 <= n <= 109
// 1 <= target <= 109
public class MinPossibleSumBeautifulArray {

    // TODO. 分析普遍的规律，将数据划分成两个部分进行求和计算(等差数列)
    //  target约束低位的数据如何取，n约束剩余的高位数据如何取
    // n = 3, target = 3
    // [1,3,4]
    //
    // n = 1, target = 1
    // [1]
    //
    // n = 4, target = 6
    // 1 2 3 4
    // 1 2 3   5
    // 1 2 3     6
    //
    // n = 4, target = 7 目标值约束它能取值的数目
    // 1 2 3 4
    // 1 2 3
    // 1 2 3       7 ..... 补充剩余(n-4)的数据
    //
    // n = 16, target = 6 -> 162
    // 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
    // 1 2 3 4   6 7 8 9 10 11 12 13 14 15 16 17
    // 1 2 3     6 7 8 9 10 11 12 13 14 15 16 17 18
    public int minimumPossibleSum(int n, int target) {
        // 没有两个index可取，只能返回1
        if (n == 1) {
            return 1;
        }

        long minSum = 0;
        if (n <= target / 2) {
            minSum = ((long) n * (n + 1)) / 2 ;
        } else {
            // 统计初始部分的数据
            int middle = target / 2;
            minSum = ((long) middle * (middle + 1)) / 2;

            // 补充后续剩余的和数, 注意rightValue值可能超过int上限
            int leftCount = n - middle;
            long rightValue = target + leftCount - 1;
            minSum += ((target + rightValue) * leftCount) / 2;
        }

        return (int) (minSum % 1000000007);
    }
}
