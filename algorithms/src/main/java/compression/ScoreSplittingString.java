package compression;

// Maximum Score After Splitting a String
// Given a string s of zeros and ones,
// return the maximum score after splitting the string into two non-empty substrings
// (i.e. left substring and right substring).
//
// The score after splitting a string =
// the number of zeros in the left substring + the number of ones in the right substring.
//
// 2 <= s.length <= 500
// The string s consists of characters '0' and '1' only.
public class ScoreSplittingString {

    // TODO. 两遍读取判断，找出最佳的划分地点
    // s = "011101" -> 5
    // left = "0" and right = "11101", score = 1 + 4 = 5
    //
    // s = "00111" -> 5
    // left = "00" and right = "111", score = 2 + 3 = 5
    //
    // s = "1111" -> 3
    // left = "1" and right = "111", score = 0 + 3 = 3
    //
    // s = "01001" ->
    // left = "0100" and right = "1", score = 3 + 1 = 4
    //
    // 0 1 0 0 1
    // 1 1 2 3
    //   2 1 1 1
    //
    // O(N)
    // O(1)
    public int maxScore(String s) {
        int length = s.length();
        int[] counts = new int[length];

        // Count left zeros
        counts[0] = s.charAt(0) == '0' ? 1 : 0;
        for (int index = 1; index < length - 1; index++) {
            if (s.charAt(index) == '0') {
                counts[index] = counts[index - 1] + 1;
            } else {
                counts[index] = counts[index - 1];
            }
        }

        // 在第二次遍历过程中直接得出结果
        // Count right ones
        int result = 0;
        int countRightOnes = s.charAt(length - 1) == '1' ? 1 : 0;
        for (int index = length - 2; index > 0; index--) {
            result = Math.max(result, counts[index] + countRightOnes);
            if (s.charAt(index) == '1') {
                countRightOnes++;
            }
        }

        // 最后判断左子字符串只有一个字符的情况
        result = Math.max(result, counts[0] + countRightOnes);
        return result;
    }
}
