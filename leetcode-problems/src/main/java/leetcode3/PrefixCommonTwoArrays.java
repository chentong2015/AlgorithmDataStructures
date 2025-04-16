package leetcode3;

import java.util.HashSet;
import java.util.Set;

// Find the Prefix Common Array of Two Arrays
// You are given two 0-indexed integer permutations A and B of length n.
// A prefix common array of A and B is an array C
// C[i] is equal to the count of numbers that are present at or before the index i in both A and B.
// Return the prefix common array of A and B.
//
// 1 <= A.length == B.length == n <= 50
// 1 <= A[i], B[i] <= n
//
// TODO. 数据的特殊限制: 数组中的值保证[1-N]，且数据不重复
// It is guaranteed that A and B are both a permutation of n integers.
public class PrefixCommonTwoArrays {

    // TODO. 使用Set<>存储出现过的数据，使用DP存储之前的累积统计
    // A = [1,3,2,4] -> [0,2,3,4]
    // B = [3,1,2,4]
    // At i = 0: no number is common, so C[0] = 0.
    // At i = 1: 1 and 3 are common in A and B, so C[1] = 2.
    // At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.
    // At i = 3: 1, 2, 3, and 4 are common in A and B, so C[3] = 4.
    //
    // A = [2,3,1] -> [0,1,3]
    // B = [3,1,2]
    // At i = 0: no number is common, so C[0] = 0.
    // At i = 1: only 3 is common in A and B, so C[1] = 1.
    // At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.
    //
    // O(N+N)
    // O(N+N)
    public int[] findThePrefixCommonArray(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int index = 0; index < nums1.length; index++) {
            int num1 = nums1[index];
            int num2 = nums2[index];

            int addCommon = 0;
            if (num1 == num2) {
                // 如果相等则增加一个共同前缀
                addCommon = 1;
            } else {
                if (set2.contains(num1)) {
                    addCommon++;
                }
                if (set1.contains(num2)) {
                    addCommon++;
                }
            }

            if (index == 0) {
                result[index] = addCommon;
            } else {
                // 累计前一轮的统计值
                result[index] = result[index - 1] + addCommon;
            }
            set1.add(num1);
            set2.add(num2);
        }
        return result;
    }

    // TODO. 将[1-N]数据值转换成坐标，统计并计算频率
    public int[] findThePrefixCommonArrayPlus(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];
        int[] freq = new int[n + 1];

        int count = 0;
        for (int i = 0; i < n; i++) {
            freq[A[i]]++;
            if (freq[A[i]] == 2) {
                count++;
            }
            freq[B[i]]++;
            if (freq[B[i]] == 2) {
                count++;
            }
            result[i] = count;
        }
        return result;
    }
}
