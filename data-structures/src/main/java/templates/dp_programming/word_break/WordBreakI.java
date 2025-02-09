package templates.dp_programming.word_break;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Word Break
// Given a string s and a dictionary of strings wordDict,
// return true if s can be segmented into a space-separated sequence of one or more dictionary words.
//
// 1 <= s.length <= 300
// 1 <= wordDict.length <= 1000
// 1 <= wordDict[i].length <= 20
// s and wordDict[i] consist of only lowercase English letters.
// All the strings of wordDict are unique.
public class WordBreakI {

    // TODO. 使用DP数组记录所有能够成立的子字符串组合位置Index
    // "leetcode", wordDict = ["leet","code"] -> true
    //  00010001
    //
    // "applepenapple", wordDict = ["apple","pen"] -> true
    //  0000100100001
    //
    // "catsandog", wordDict = ["cats","dog","sand","and","cat"] -> false
    //
    // "aaaaaaaaaaaaaaaaab", ["a","aa","aaa","aaaa","aaaaa","aaaaaa"] -> false
    //
    // O(n*n*n) 时间复杂度比较高
    // O(n+n)
    public boolean wordBreak(String str, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[str.length() + 1];
        dp[0] = true; // 初始化dp0保证能判断到第一个字符

        for (int index = 1; index <= str.length(); index++) {
            for (int left = 0; left < index; left++) {
                if (!dp[left]) {
                    continue;
                }

                // 当左边的字符串成立时，判断右边的子字符串是否成立
                String suffix = str.substring(left, index);
                if (set.contains(suffix)) {
                    dp[index] = true;
                    break;
                }
            }
        }
        return dp[str.length()];
    }
}
