package data_structure.array;

import java.util.*;

public class LearnArray6 {

    // [87,193,85,55,14,69,26,133,171,180,4,8,29,121,182,78,
    // 157,53,26,7,117,138,57,167,8,103,32,110,15,190,139,16,49,138,68,69,92,89,140,
    // 149,107,104,2,135,193,87,21,194,192,9,161,188,73,84,83,31,86,33,
    // 138,63,127,73,114,32,66,64,19,175,108,80,176,52,124,94,33,55,130,147,39,
    // 76,22,112,113,136,100,134,155,40,170,144,37,43,151,137,82,127,73]
    public static void main(String[] args) {
        int[] banned = {87,193,85,55,14,69,26,133,171,180,4,8,29,121,182,78,157,53,26,7,
                117,138,57,167,8,103,32,110,15,190,139,16,49,138,68,69,92,89,140,149,107,
                104,2,135,193,87,21,194,192,9,161,188,73,84,83,31,86,33,138,63,127,73,114,
                32,66,64,19,175,108,80,176,52,124,94,33,55,130,147,39,76,22,112,113,136,100,
                134,155,40,170,144,37,43,151,137,82,127,73};
        System.out.println(maxCount(banned, 1079, 87));
    }

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
    //
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
