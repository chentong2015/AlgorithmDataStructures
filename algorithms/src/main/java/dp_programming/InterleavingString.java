package dp_programming;

public class InterleavingString {

    // Interleaving String
    // Find whether s3 is formed by an interleaving of s1 and s2
    // s = s1 + s2 + ... + sn & t = t1 + t2 + ... + tm  以字符交叉的顺序组成最后的字符串
    // The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
    // 顺次从s1和s2中提取字符，拼接构成s3的字符串
    // 如果s1和s2中都可以提取同一个字符，则有两个拼接的可能 ... O(2^m+n) 结果的每一个位置可能来自S或者T
    //    '' d  b  b  c  a
    // '' T  F  F  F  F  F      构建二维数组，从左上角到右下角找到一条完整的T通路，则说明能够组成S3
    // a  T  F  F  F  F  F      每一个坐标位置是否是True，取决于前面一个位置和上面一个位置
    // a  T  T  T  T  T  F      将二维存储改为一维存储，只需要每个位置前面累积的判断 !!
    // b  F  T  T  F  T  F
    // c  F  F  T  T  T  T
    // c  F  F  F  T  F  T
    // => a a d b b c b c a c   => 总的位置 i+j-1
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;
        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1); // 第一列的判断
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1); // 第一行的判断
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
