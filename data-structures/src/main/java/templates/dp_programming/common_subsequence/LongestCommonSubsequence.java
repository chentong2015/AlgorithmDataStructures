package templates.dp_programming.common_subsequence;

// TODO. 金典的CS问题: Longest Common Subsequence
// - Subsequence 子序列是按照顺序排序的一串子字符(不是严格连续)，通过删除某些字符形成
// - 最长公共子序列可以应用在数据比较与猜测(判断子序列的意思)
//   - diff utility & winMerge
//   - Git版本比较与修订
// https://en.m.wikipedia.org/wiki/Longest_common_subsequence_problem
public class LongestCommonSubsequence {

    // TODO. DP二维数组解决LCS的问题：从历史比较的记录中逐渐递归出最后的结果
    // Longest Common Subsequence
    // Given two Strings, find the longest common subsequence between them
    //
    // text1="agcat", text2="gac" -> (ac),(gc),and(ga) -> 2
    //     A  G	 C	A  T  +
    // G   0  0	 0	0  0  0
    // A   0  0  1  1  1  1
    // C   0  1  1  1  2  2
    // +   0  1  1  2  2  2
    //
    // O(n*m) 循环的次数
    // O(n*m) 通过DP数组来记录“历史组合序列”的所有数据
    public int longestCommonSubsequence(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < str1.length(); ++i)
            for (int j = 0; j < str2.length(); ++j)
                if (str1.charAt(i) == str2.charAt(j)) {
                    // 如果相等，则在“排除掉这个相同字符”前面LCS结果上+1
                    // dp[i][j]为该字符前面的两个字符串的LCS结果
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    // 如果不等，则比较拿掉char1或拿掉char2得出的结果更大
                    // 在这两种历史记录的基础上生成当前记录值
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
        return dp[str1.length()][str2.length()];
    }
}