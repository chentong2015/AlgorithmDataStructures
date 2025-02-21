package dp_programming.subsequence;

// Shortest Common SuperSequence
// Given two strings str1 and str2, return the shortest string
// that has both str1 and str2 as subsequences
// If multiple answers exist, you may return any of them
//
// 1 <= str1.length, str2.length <= 1000
// str1 and str2 consist of lowercase English letters.
public class CommonShortestSuperSequence {

    // TODO: 和LCS同样的算法，拿到最短公共SuperSequence的长度，并非字符组合的结果
    // str1 = "aaaaaaaa", str2 = "aaaaaaaa" -> "aaaaaaaa"
    //
    // str1 = "abac", str2 = "cab"  -> "cabac" 如何从所有的结果中取出一个有效值 !!
    //          a       b     a       c
    //    0     1a     2ab   3aba   4abac
    // c  1c    ac/ca  cab   caba   abac
    // a  2ca   ca     cab   caba   cabac
    // b  3cab  cab    cab   caba   cabac  保留每一个位置的计算长度值, 从末尾往前面推导
    private int[][] getLengthOfShortestCommonSuperSequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    // 第一行和第列只需要补充指定字符串的长度
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (str1.charAt(i - 1) == str2.charAt(j-1)) {
                    // 如果相等，则在去掉公共字符的基础上补充一个共同字符
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    // 选择最小的合成长度上面添加一
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
                }
            }
        }
        return dp;
    }

    // 根据两个原始的字符串来组成指定长度的Super Sequence
    public String shortestCommonSupersequence(String str1, String str2) {
        int i = str1.length();
        int j = str2.length();
        int[][] dp = getLengthOfShortestCommonSuperSequence(str1, str2);
        int position = dp[i][j];

        char[] arr = new char[position];
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                arr[--position] = str1.charAt(i - 1);
                i--;
                j--;
                // 如果dp[i - 1][j]位置的值更小，则说明使用str1的字符会使得路径更加的短 !!
            } else if (dp[i - 1][j] < dp[i][j - 1]) {
                arr[--position] = str1.charAt(i - 1);
                i--;
            } else {
                arr[--position] = str2.charAt(j - 1);
                j--;
            }
        }
        while (i > 0) {
            arr[--position] = str1.charAt(i - 1);
            i--;
        }
        while (j > 0) {
            arr[--position] = str2.charAt(j - 1);
            j--;
        }
        return new String(arr);
    }
}
