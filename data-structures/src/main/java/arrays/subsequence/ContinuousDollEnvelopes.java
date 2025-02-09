package arrays.subsequence;

import java.util.Arrays;

public class ContinuousDollEnvelopes {

    // TODO: 本质上是一个"连续梯度上升"的问题，通过排序将这一个特征显现出来 !!
    //  通过DP + Binary解决连续梯度上升特征的问题 !! Longest Increasing Subsequence
    // Russian Doll Envelopes
    // envelopes[i] = [wi, hi] represents the width and the height of an envelope
    // One envelope can fit into another if and only if both the width and height of one envelope are greater than the other
    // Return the maximum number of envelopes you can Russian doll
    // 1. 作用范围，必须是长宽严格大于才能放入
    // 2. 同时不能够旋转envelope(把长当作宽来使用)
    // envelopes = [[5,4],[6,4],[6,7],[2,3]] -> [2,3] => [5,4] => [6,7] -> 3
    // sorted    = [[2,3],[5,4],[6,4],[6,7]]
    public int maxEnvelopesDP(int[][] envelopes) {
        // 自定义比较器, 提供Lambda作为比较器的实现 ==> 二分法搜索时判断是否找到的比较条件 !!
        Arrays.sort(envelopes, (arr1, arr2) -> {
            if (arr1[0] == arr2[0]) return arr2[1] - arr1[1];
            else return arr1[0] - arr2[0];
        });

        int dp[] = new int[envelopes.length];
        int maxLength = 0;
        for (int[] envelope : envelopes) {
            int index = Arrays.binarySearch(dp, envelope[1]);
            if (index < 0) index = -(index + 1);
            dp[index] = envelope[1];
            if (index == maxLength) maxLength++;
        }
        return maxLength;
    }
}
