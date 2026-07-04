package strings;

// Jewels and Stones
// You're given strings jewels representing the types of stones that are jewels,
// and stones representing the stones you have.
// Each character in stones is a type of stone you have.
// You want to know how many of the stones you have are also jewels.
//
// Letters are case sensitives, so "a" is considered a different type of stone from "A".
//
// 1 <= jewels.length, stones.length <= 50
// jewels and stones consist of only English letters.
// All the characters of jewels are unique.
public class JewelsAndStones {

    // TODO. 基于26+26个英文字符构建常量数组
    // jewels = "aA", stones = "aAAbbbb" -> 3
    // jewels = "z", stones = "ZZ" -> 0
    //
    // O(N+M)
    // O(1)
    public int numJewelsInStones(String jewels, String stones) {
        int[] upperCases = new int[26];
        int[] lowerCases = new int[26];
        for (char c: jewels.toCharArray()) {
            if (c <= 'Z') {
                upperCases[c - 'A']++;
            } else {
                lowerCases[c - 'a']++;
            }
        }

        int result = 0;
        for (char c: stones.toCharArray()) {
            if (c <= 'Z') {
                if (upperCases[c - 'A'] == 1) {
                    result++;
                }
            } else {
                if (lowerCases[c - 'a'] == 1) {
                    result++;
                }
            }
        }
        return result;
    }
}
