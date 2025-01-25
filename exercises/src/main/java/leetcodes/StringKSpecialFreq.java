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

    // TODO. 只考虑最终结果的区间，根据k参数控制区间偏移量
    //  不关心删除的字符是什么，也不关系选择什么删除字符
    //
    // aabcaba, k=0 -> 3
    // a b c
    // 4 2 1
    //
    // dabdcbdcdcd, k=2 -> 2
    // a b c d
    // 1 2 3 5
    //
    // aaabaaa, k=2 -> 1
    // a b
    // 6 1
    //
    // vvnowvov, k=2 -> 1 特殊情况，所选择的最终区间为[1,3]
    // 4 2 1 1
    //
    // uzzezzuzenzu, k=0 -> 6
    // 6 3 2 1
    public int minimumDeletions(String word, int k) {
        int[] freqArray = new int[26];
        for (char c: word.toCharArray()) {
            freqArray[c - 'a']++;
        }

        Arrays.sort(freqArray);
        for (int index=0; index < 13; index++) {
            int temp = freqArray[index];
            freqArray[index] = freqArray[25 - index];
            freqArray[25 - index] = temp;
        }

        int minSteps = Integer.MAX_VALUE;
        for (int index=0; index < 26; index++) {
            int count = freqArray[index];
            if (count == 0) {
                break;
            }

            // TODO. 这里需要考虑所有偏移情况
            for (int offset = count + k; offset >= count; offset--) {
                int countSteps = countSteps(freqArray, offset, Math.max(offset - k, 0));
                minSteps = Math.min(minSteps, countSteps);
            }
        }
        return minSteps;
    }

    private int countSteps(int[] freqArray, int max, int min) {
        int count = 0;
        for (int index=0; index < 26; index++) {
            int freq = freqArray[index];
            if (freq == 0) {
                break;
            }

            if (freq > max) {
                count += freq - max;
            }
            if (freq < min) {
                count += Math.min(min - freq, freq);
            }
        }
        return count;
    }
}
