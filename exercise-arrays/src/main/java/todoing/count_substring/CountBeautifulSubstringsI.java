package todoing.count_substring;

// Count Beautiful Substrings I
// You are given a string s and a positive integer k.
// Let vowels and consonants be the number of vowels and consonants in a string.
// Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
// Consonant letters in English are every letter except vowels.
//
// TODO. 注意约束的条件是数量的乘积
// A string is beautiful if:
// vowels == consonants and (vowels * consonants) % k == 0
//
// Return the number of non-empty beautiful substrings in the given string s.
public class CountBeautifulSubstringsI {

    public static void main(String[] args) {
        String value = "eeebjoxxujuaeoqibd";
        CountBeautifulSubstringsI instance = new CountBeautifulSubstringsI();
        System.out.println(instance.beautifulSubstrings(value, 8));
    }

    // TODO. 子字符串必须是连续, 原始字符的位置顺序不能打乱
    // b a e y h, k=2 -> 2
    // baey, aeyh
    //
    // 最差的时间复杂度，V和C字符各自一半，且K=1
    // O(n + n/2 * n)
    // O(n)
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

    // TODO. 滑动窗口统计指定长度的SubString是否满足条件
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
