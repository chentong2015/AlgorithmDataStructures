package substring;

// TODO: KMP Pattern Matching(Substring Search)
// 模式串匹配失败时，利用已经匹配的字符串的“最长相等前后缀”，把模式串指针跳过去，而不是让主串指针回退
public class KmpPatternMatch {

    // A B A B A B C  主串
    // A B A B C      模板串
    // 0 0 1 2 3
    //         这个位置匹配失败后j=pi[j-1]=2
    //         回到第三个字符A继续匹配，前面的AB字符"等效于"已经匹配好
    //
    // str    : a b x a b c a b c a b y
    // pattern: a b c a b y
    //          0 1 2 3 4 5
    //          0 0 0 1 2 0
    //          left 当x和c不等时，后退一步，然后再确定到a的位置
    //
    // O(n+m)
    // 0(m)
    public static boolean testKMPPatternMatching(String str, String pattern) {
        // TODO. 先计算index回退位置的数组
        int left = 0;
        int right = 1;
        int[] indexs = new int[pattern.length()];
        while (right < pattern.length()) {
            if (pattern.charAt(left) == pattern.charAt(right)) {
                indexs[right] = indexs[right - 1] + 1;
                left++;
            }
            right++;
        }

        int i = 0; // str
        int j = 0; // pattern
        while (i < str.length()) {
            if (str.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) {
                    return true; // 首个匹配位置(i-j)
                }
            } else if (j > 0) {
                j = indexs[j - 1]; // j回退到最佳的起始位置, i保持不变
            } else {
                i++; // 说明首个字符不匹配, 只能往后移动主串坐标
            }
        }
        return false;
    }
}
