package datadog;

// Solution 2: 降低时间复杂度的维度
// 针对不同的子字符串长度，只遍历一次整个字符串
//
// 1 char    -> n
// 2 chars   -> n
// 3 chars   -> n
// ...
// n-1 chars -> n
//
// O((n-1)*n) 一共循环n-1次，每次时间复杂度为n
// O(1)       没有创建空间复杂度
public class SubstringCounterOk {

    // abc
    // a b c ab bc
    // 1 1 2 2  3
    public static void main(String[] args) {
        int total = getTotalNumOfNonEmptyExSubstrings("abcbnds");
        System.out.println(total);
    }

    // TODO. Sliding Windows 滑动窗口算法运用到子字符串的遍历
    public static int getTotalNumOfNonEmptyExSubstrings(String input) {
        int totalCount = input.length(); // For the substring with 1 char
        int subLength = 2;
        while (subLength < input.length()) {
            // Get the first subLength sum
            int substringValueSum = 0;
            for (int index=0; index < subLength; index++) {
                substringValueSum += getCharValue(input.charAt(index));
            }
            if (substringValueSum % subLength == 0) {
                totalCount++;
            }
            // For the remaining chars
            for (int index = subLength; index < input.length(); index++) {
                substringValueSum -= getCharValue(input.charAt(index - subLength));
                substringValueSum += getCharValue(input.charAt(index));
                if (substringValueSum % subLength == 0) {
                    totalCount++;
                }
            }
            subLength++;
        }
        return totalCount;
    }

    // Get the mapping value for one char
    private static int getCharValue(char c) {
        if (c == 'a' || c == 'b') {
            return 1;
        }
        return 2 + (c - 'b') / 3; // Calculate offset position
    }
}
