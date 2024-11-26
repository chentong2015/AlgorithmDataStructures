package datadog;

// Solution 1: brute force 暴力破解
// 判断每一个种可能的子字符串结果，对子字符串逐个循环每个字符
//
// N*(N-1)*N  总复杂度为O(N^3)，等差数列Sequence
// O(N)       空间复杂度
public class SubstringCounterKO {

    public static int getTotalNumOfNonEmptyExSubstrings(String input) {
        char[] chars = input.toLowerCase().toCharArray();
        int totalNum = 0;
        for (int startIndex = 0; startIndex < chars.length; startIndex++) {
            for (int endIndex = startIndex; endIndex < chars.length; endIndex++) {
                if (isValidExSubstring(chars, startIndex, endIndex)) {
                    System.out.println(startIndex + "--" + endIndex);
                    totalNum++;
                }
            }
        }
        return totalNum;
    }

    // TODO. 第三层的for循环会造成时间复杂度额外的增加
    private static boolean isValidExSubstring(char[] chars, int startIndex, int endIndex) {
        int sum = 0;
        for (int index = startIndex; index <= endIndex; index++) {
            if (chars[index] == 'a' || chars[index] == 'b') {
                sum += 1;
            } else {
                int mapPosition = (chars[index] - 'b') / 3; // 9 宫格映射的偏移位置
                sum += mapPosition + 2; // +2 映射的实际数字值
            }
        }
        return (sum % (endIndex - startIndex + 1)) == 0;
    }
}
