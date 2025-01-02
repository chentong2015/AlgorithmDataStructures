package amazon.Interviews;

// TODO. 相邻字符不等: 本质上是利用不同字符之间抵消特性, 与具体字符无关
// Reorganize String
// Given a string s, rearrange the characters of s
// so that any two adjacent characters are not the same.
// Return any possible rearrangement of s or return "" if not possible.
//
// "aab"  -> "aba" 抵消后的排布结果
// "aaab" -> ""    相邻的字符抵消后，最后剩余的两个字符必然重叠
// "baa"  -> "aba" 原始字符串的第一个字符并不一定是结果的第一个字符
//
// 1 <= s.length <= 500
// s consists of lowercase English letters.
public class ReorganizeString {

    // TODO. Alternate placing the most common letters.
    // 核心算法: 每轮动态选择Top2频率的两个字符来组合，直到无法选择
    public static void main(String[] args) {
        System.out.println(reorganizeString("aab"));
        System.out.println(reorganizeString("vvvlo"));
    }

    public static String reorganizeString(String s) {
        int[] counts = new int[26];
        for (char c: s.toCharArray()) {
            counts[c - 'a']++;
        }

        StringBuilder stringBuilder = new StringBuilder();
        char maxCountChar = findMaxCountChar(counts, ' ');
        while (maxCountChar != ' ') {
            stringBuilder.append(maxCountChar);

            char secondMaxCountChar = findMaxCountChar(counts, maxCountChar);
            if (secondMaxCountChar != ' ') {
                stringBuilder.append(secondMaxCountChar);
            } else {
                // 如果第二个最大统计的字符没有找到，则不能再有字符剩余
                if (counts[maxCountChar - 'a'] > 0) {
                    return "";
                }
            }
            maxCountChar = findMaxCountChar(counts, ' ');
        }
        return stringBuilder.toString();
    }

    // 查找最大统计频率的字符
    // charExcluded排除某个字符用于查找第二大字符
    private static char findMaxCountChar(int[] counts, char charExcluded) {
        int maxIndex = -1;
        for (int index = 0; index < 26; index++) {
            char c = (char) ('a' + index);
            if (c != charExcluded && counts[index] >0) {
                if (maxIndex == -1 || counts[index] > counts[maxIndex]) {
                    maxIndex = index;
                }
            }
        }
        // 如果没有找到合适的字符则返回空，反之降低字符的统计
        if (maxIndex == -1) {
            return ' ';
        }
        counts[maxIndex]--;
        return (char) ('a' + maxIndex);
    }
}
