package leetcode.count_substring;

public class CountBeautifulSubstringsII {

    // baeyh, k=2
    //
    // abababababababababab, k=2
    //
    public int beautifulSubstrings(String s, int k) {
        int countV = 0;
        int countC = 0;
        char[] charArray = s.toCharArray();
        for (char c: charArray) {
            if (isVowels(c)) {
                countV++;
            } else {
                countC++;
            }
        }
        int minCount = Math.min(countV, countC);

        int result = 0;
        int loop = 1;
        while (loop <= minCount) {
            if ((loop * loop) % k == 0) {
                result += countSlidingWindow(charArray, loop * 2);
            }
            loop++;
        }
        return result;
    }

    // TODO. 标准的滑动窗口统计
    private int countSlidingWindow(char[] charArray, int length) {
        int countV = 0;
        int countC = 0;
        int count = 0;
        for (int index = 0; index < charArray.length; index++) {
            // 添加新的字符统计
            if (isVowels(charArray[index])) {
                countV++;
            } else {
                countC++;
            }

            if (index == length - 1) {
                if (countV == countC) {
                    count++;
                }
            } else if (index >= length){
                // 减去最开头的字符统计
                if (isVowels(charArray[index - length])) {
                    countV--;
                } else {
                    countC--;
                }
                if (countV == countC) {
                    count++;
                }
            }
        }
        return count;
    }

    // s consists of only English lowercase letters.
    private boolean isVowels(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
