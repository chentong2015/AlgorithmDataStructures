package master.prefix_sum.num_divisible_substrings;

import java.util.HashMap;
import java.util.Map;

// Number of Divisible Substrings & Extraordinary Substring
// Sum of the mapped values of each letter is divided by its length
// Count total num of non-empty substrings
public class NumDivisibleSubstrings {

    // TODO. 循环9种平均值: 累加时减去平均值, 判断整数倍的关系
    //
    // Time: O(10*n)
    // Space: O(n)
    public int countDivisibleSubstrings(String word) {
        int count = 0;
        for (int average = 1; average < 10; ++average) {
            Map<Integer, Integer> numSumMap = new HashMap<>();
            numSumMap.put(0, 1); // 初始化前缀和为0的统计

            int prefixSum = 0;
            for (char c : word.toCharArray()) {
                prefixSum += getMappedValue(c) - average;

                int baseCount = numSumMap.getOrDefault(prefixSum, 0);
                count += baseCount;

                numSumMap.put(prefixSum, baseCount + 1);
            }
        }
        return count;
    }

    // 只是将整数换成char, 使用映射的值而已
    private int getMappedValue(char c) {
        if (c == 'a' || c == 'b') {
            return 1;
        }
        return (c - 'b') / 3 + 2;
    }
}

