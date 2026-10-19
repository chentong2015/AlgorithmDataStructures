package array;

import java.util.HashSet;
import java.util.Set;

// Maximum Number of Integers to Choose From a Range I.
// You are given an integer array banned and two integers n and maxSum.
// You are choosing some number of integers following the below rules:
//
// - The chosen integers have to be in the range [1, n].
// - Each integer can be chosen at most once.
// - The chosen integers should not be in the array banned.
// - The sum of the chosen integers should not exceed maxSum.
// Return the maximum number of integers you can choose following the mentioned rules.
//
// 1 <= banned.length <= 10^4
// 1 <= banned[i], n <= 10^4
// 1 <= maxSum <= 10^9
public class MaxNumRangeArray {

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