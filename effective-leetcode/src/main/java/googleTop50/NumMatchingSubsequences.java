package googleTop50;

import java.util.HashMap;

// Number of Matching Subsequences
// Given a string s and an array of strings words,
// return the number of words[i] that is a subsequence of s.
//
// For example, "ace" is a subsequence of "abcde".
public class NumMatchingSubsequences {

    // TODO. 当出现大量重复单词时只需要验证一遍，累加出现频次
    //
    // s = "abcde"
    // words = ["a","bb","acd","ace"]
    //
    // N is num of words,
    // S is num of chars in s,
    // M is num of chars in each word
    //
    // O(N*(S+M))
    // O(N)
    public int numMatchingSubseq(String s, String[] words) {
        // word1 -> 2   提前对数据特征和进行分类
        // word2 -> 100
        HashMap<String, Integer> wordCountMap = new HashMap<>();
        for (String word : words) {
            int count = wordCountMap.getOrDefault(word, 0);
            count++;
            wordCountMap.put(word, count);
        }

        int count = 0;
        for (String word : wordCountMap.keySet()) {
            int indexS = 0;
            int indexW = 0;
            while (indexW < word.length() && indexS < s.length()) {
                if (word.charAt(indexW) == s.charAt(indexS)) {
                    indexW++;
                }
                indexS++;
            }

            // If found matching, accumulate the frequency
            if (indexW == word.length()) {
                count += wordCountMap.get(word);
            }
        }
        return count;
    }


    // TODO. 直接循环所有单词会出现Time Limit Exceeded问题
    public int numMatchingSubsequences(String s, String[] words) {
        int count = 0;
        for (String word : words) {
            int indexS = 0;
            int indexW = 0;
            while (indexW < word.length() && indexS < s.length()) {
                if (word.charAt(indexW) == s.charAt(indexS)) {
                    indexW++;
                }
                indexS++;
            }
            if (indexW == word.length()) {
                count++; // find matched
            }
        }
        return count;
    }
}
