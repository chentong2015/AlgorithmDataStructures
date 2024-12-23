package amazon.question;

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
    //  核心算法: 每次选择“剩余频率最高”的两个字符来组合，直到无法选择
    public static void main(String[] args) {
        System.out.println(reorganizeString("vvvlo"));
    }

    public static String reorganizeString(String s) {
        int[] counts = new int[26];
        for (char c: s.toCharArray()) {
            counts[c - 'a']++;
        }

        StringBuilder stringBuilder = new StringBuilder();
        char maxCountChar = findMaxCountChar(counts);
        while (maxCountChar != ' ') {
            stringBuilder.append(maxCountChar);
            char nextCountMaxChar = findNextCountMaxChar(counts, maxCountChar);
            if (nextCountMaxChar != ' ') {
                stringBuilder.append(nextCountMaxChar);
                maxCountChar = findMaxCountChar(counts);
            } else {
                int index = maxCountChar - 'a';
                if (counts[index] > 0) {
                    return "";
                }
                break;
            }
        }
        return stringBuilder.toString();
    }

    // 选择统计中最大的字符，选中后降低它的统计
    private static char findMaxCountChar(int[] counts) {
        int maxIndex = -1;
        for (int index=0; index<26; index++) {
            if (counts[index] > 0) {
                if (maxIndex == -1 || counts[index] > counts[maxIndex]) {
                    maxIndex = index;
                }
            }
        }
        if (maxIndex != -1) {
            counts[maxIndex]--;
            return (char) ('a' + maxIndex);
        }
        return ' ';
    }

    // 选择剩余字符中统计最大的字符，选中后降低它的统计
    private static char findNextCountMaxChar(int[] counts, char charExcluded) {
        int maxIndex = -1;
        for (int index=0; index<26; index++) {
            char c = (char) ('a' + index);
            if (c != charExcluded && counts[index] >0) {
                if (maxIndex == -1) {
                    maxIndex = index;
                } else {
                    if (counts[index] > counts[maxIndex]) {
                        maxIndex = index;
                    }
                }
            }
        }
        if (maxIndex == -1 || counts[maxIndex] == 0) {
            return ' ';
        }
        counts[maxIndex]--;
        return (char) ('a' + maxIndex);
    }
}
