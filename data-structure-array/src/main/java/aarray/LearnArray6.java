package aarray;

import java.util.*;

// Maximum Number of Integers to Choose From a Range I.
// You are given an integer array banned and two integers n and maxSum.
// You are choosing some number of integers following the below rules:
//
// - The chosen integers have to be in the range [1, n].
// - Each integer can be chosen at most once.
// - The chosen integers should not be in the array banned.
// - The sum of the chosen integers should not exceed maxSum.
// Return the maximum number of integers you can choose following the mentioned rules.
// 1 <= banned.length <= 10^4
// 1 <= banned[i], n <= 10^4
// 1 <= maxSum <= 10^9
public class LearnArray6 {

    // 1 5 6
    // banned = [1,6,5], n = 5, maxSum = 6 -> [2, 4], 2
    // banned = [1,2,3,4,5,6,7], n = 8, maxSum = 1 -> 0
    // banned = [11], n = 7, maxSum = 50 -> [1, 2, 3, 4, 5, 6, 7], 7

    // TODO. 不开辟额外的内存空间: 注意banned数组中可能存在重复数据
    // O(k*logk + n)
    // O(1)
    public static int maxCount(int[] banned, int n, int maxSum) {
        Arrays.sort(banned);
        int currentSum = 0;
        int count = 0;
        int iBanned = 0;
        int index=1;
        while (index <= n) {
            if (iBanned < banned.length && index == banned[iBanned]) {
                // 排除banned数组中连续的相同数据
                iBanned++;
                while (iBanned < banned.length && index == banned[iBanned]) {
                    iBanned++;
                }
                index++;
            } else {
              if (currentSum + index > maxSum) {
                  break;
              }
              currentSum += index;
              count++;
              index++;
            }
        }
        return count;
    }

    // TODO. 通过Set<>集合来降低时间复杂度
    // O(k + n)
    // O(k)
    public int maxCount2(int[] banned, int n, int maxSum) {
        int currentSum = 0;
        int count = 0;
        Set<Integer> set = new HashSet<>();
        for(int i: banned) {
            set.add(i);
        }

        for (int index=1; index<=n; index++) {
            if (set.contains(index)) {
                continue;
            }
            if (currentSum + index <= maxSum) {
                currentSum += index;
                count++;
            }
            if (currentSum >= maxSum) {
                break;
            }
        }
        return count;
    }
}
