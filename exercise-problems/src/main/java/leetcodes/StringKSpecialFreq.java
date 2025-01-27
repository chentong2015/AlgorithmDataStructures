package leetcodes;

import java.util.Arrays;

// Minimum Deletions to Make String K-Special
// You are given a string word and an integer k.
// Word k-special if |freq(word[i]) - freq(word[j])| <= k for all indices i and j in string.
// Return the minimum number of characters you need to delete to make word k-special.
//
// 1 <= word.length <= 105
// 0 <= k <= 105
// word consists only of lowercase English letters.
public class StringKSpecialFreq {

    public static void main(String[] args) {
        StringKSpecialFreq stringKSpecialFreq = new StringKSpecialFreq();
        System.out.println(stringKSpecialFreq.minimumDeletions("uzzezzuzenzu", 0));
    }

    // TODO. 一定要基于26个字符范围来循环，而非基于统计频率的N值
    //  只考虑最终结果的区间范围，不关心删除的字符以及位置
    // dabdcbdcdcd, k=2 -> 2
    // a b c d
    // 1 2 3 5
    //
    // vvnowvov, k=2 -> 1 特殊情况最终区间为[1,3]
    // 4 2 1 1
    //
    // uzzezzuzenzu, k=0 -> 6
    // 1 2 3 6
    //
    // O(n)   最差情况是移动n次区间范围
    // O(26)  没有空间复杂度
    public int minimumDeletions(String word, int k) {
        int[] freqArray = new int[26];
        for (char c: word.toCharArray()) {
            freqArray[c - 'a']++;
        }
        Arrays.sort(freqArray);

        int minSteps = Integer.MAX_VALUE;
        int countDeletionLeft = 0;
        for (int i = 0; i < 26; i++) {
            if (freqArray[i] == 0) {
                continue;
            }
            // 统计右侧需要删除的字符数量
            int countDeletionRight = 0;
            for(int j = i+1; j < 26; j++) {
                if (freqArray[j] > freqArray[i] + k) {
                    countDeletionRight += freqArray[j] - (freqArray[i] + k);
                }
            }

            // TODO. [freq, freq+k] 统计区间之外左右两侧的删除数量
            minSteps = Math.min(minSteps, countDeletionLeft + countDeletionRight);

            // 累计左侧低频率的统计, 低频率在区间的移动中只能全部被删除
            countDeletionLeft += freqArray[i];
        }
        return minSteps == Integer.MAX_VALUE ? 0: minSteps;
    }
}
