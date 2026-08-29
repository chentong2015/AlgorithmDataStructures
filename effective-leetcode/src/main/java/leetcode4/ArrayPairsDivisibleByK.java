package leetcode4;

import java.util.HashMap;
import java.util.Map;

// Check If Array Pairs Are Divisible by k
// Given an array of integers arr of even length n and an integer k.
// We want to divide the array into exactly n / 2 pairs
// such that the sum of each pair is divisible by k
//
// Return true If you can find a way to do that or false otherwise.
//
// arr.length == n
// 1 <= n <= 10^5
// n is even.
// -10^9 <= arr[i] <= 10^9
// 1 <= k <= 10^5
public class ArrayPairsDivisibleByK {

    // TODO. 在遍历过程中消除数据 => 52ms 时间复杂度一般，最优空间复杂度
    // [1,2,3,4,5,10,6,7,8,9], k = 5 -> true
    // (1,9),(2,8),(3,7),(4,6) and (5,10).
    // (1,4),(2,3),(3,2),(4,1) 计算余数
    //
    // [1,2,3,4,5,6], k = 7 -> true
    // (1,6),(2,5) and(3,4)
    //
    // [1,2,3,4,5,6], k = 10 -> false
    // (4,6)...
    //
    // [-1,1,-2,2,-3,3,-4,4], k = 3 -> true
    // (-1,-2),(1,2),(-3,3),(-4,4) 注意负数的情况
    //
    // TODO. 存储余数值的统计 => 使用int[K]数组来存储和遍历
    // [-4,-7,5,2,9,1,10,4,-8,-3], k = 3 -> true
    //  -1 -1 2 2 0 1  1 1 -2 0 注意存储重复的余数值
    //  -1 ->2
    //  2  ->2
    //  1  ->3
    //  -1 ->1
    //
    // O(N) 只遍历一遍数据
    // O(K) 最差情况下会存储k级别的余数
    public boolean canArrange(int[] arr, int k) {
        int countDivisible = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num: arr) {
            if (num % k == 0) {
                countDivisible++;
            } else {
                // TODO. 找三种能够补全余数的判断
                int left = num % k;
                if (checkRemainingValue(countMap, k - left)
                || checkRemainingValue(countMap, -k - left)
                || checkRemainingValue(countMap, - left)) {
                    continue;
                }
                // 累计存储重复的余数
                int baseCount = countMap.getOrDefault(left, 0);
                countMap.put(left, baseCount + 1);
            }
        }
        // 必须有双数的整除数据，剩余的数据两两成对
        return countDivisible % 2 == 0 && countMap.isEmpty();
    }

    private boolean checkRemainingValue(Map<Integer, Integer> countMap, int remainingValue) {
        int count = countMap.getOrDefault(remainingValue, 0);
        if (count == 0) {
            return false;
        } else if (count == 1) {
            countMap.remove(remainingValue);
        } else {
            // 抵消掉一个数字的组合
            countMap.put(remainingValue, count - 1);
        }
        return true;
    }
}
