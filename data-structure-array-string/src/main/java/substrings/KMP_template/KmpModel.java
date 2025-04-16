package substrings.KMP_template;

public class KmpModel {

    // TODO: KMP Pattern Matching(Substring Search)
    // 快速判断两个字符串的匹配和包含关系
    // 取消对pattern子字符串的遍历，将时间复杂度从O(n*m)降低为O(n+m)
    public boolean kmp(String a, String b) {
        int[] lps = lps(b);
        int n = a.length(), m = b.length(), i = 0, j = 0;
        while (i < n) {
            if (a.charAt(i) == b.charAt(j)) {
                i++;
                j++;
                if (j == m)
                    return true;
            } else {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i++;
            }
        }
        return false;
    }

    public int[] lps(String b) {
        int n = b.length();
        int[] lps = new int[n];
        int i = 1, l = 0;
        while (i < n) {
            if (b.charAt(i) == b.charAt(l)) {
                l++;
                lps[i] = l;
                i++;
            } else {
                if (l != 0)
                    l = lps[l - 1];
                else
                    i++;
            }
        }
        return lps;
    }
}
