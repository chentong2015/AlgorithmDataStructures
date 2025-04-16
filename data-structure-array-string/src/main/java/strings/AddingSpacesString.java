package strings;

// Adding Spaces to a String
// You are given a 0-indexed string s and a 0-indexed integer array spaces
// that describes the indices in the original string where spaces will be added.
// Each space should be inserted before the character at the given index.
//
// For example, given s = "EnjoyYourCoffee" and spaces = [5, 9],
// we place spaces before 'Y' and 'C', which are at indices 5 and 9 respectively.
// Thus, we obtain "Enjoy Your Coffee".
//
// Return the modified string after the spaces have been added.
//
// 1 <= s.length <= 3 * 10^5
// s consists only of lowercase and uppercase English letters.
// 1 <= spaces.length <= 3 * 10^5
// 0 <= spaces[i] <= s.length - 1
// All the values of spaces are strictly increasing.
public class AddingSpacesString {

    // TODO. 21ms 基于StringBuilder的操作
    // s = "LeetcodeHelpsMeLearn", spaces = [8,13,15]
    // -> "Leetcode Helps Me Learn"
    //
    // s = "icodeinpython", spaces = [1,5,7,9]
    // -> "i code in py thon"
    //
    // s = "spacing", spaces = [0,1,2,3,4,5,6]
    // -> " s p a c i n g"
    //
    // O(N + N) 最初情况是双倍的长度
    // O(N + N) 最差情况是每个字符中间都插入空格
    public String addSpaces(String s, int[] spaces) {
         StringBuilder stringBuilder = new StringBuilder();
         int idSpace = 0;
         for (int index = 0; index < s.length(); index++) {
             if (idSpace < spaces.length && index == spaces[idSpace]) {
                 stringBuilder.append(" ");
                 idSpace++;
             }
             stringBuilder.append(s.charAt(index));
         }
         return stringBuilder.toString();
    }

    // TODO. 7ms 基于Byte字节操作:
    public String addSpacesBytes(String s, int[] spaces) {
        int sourceLen = s.length();
        int spacesLen = spaces.length;
        byte[] resultBytes = new byte[sourceLen + spacesLen];

        // Copies characters from this string into the destination byte array
        s.getBytes(0, sourceLen, resultBytes, 0);

        // 从后往前依次填充每个Byte字节 + 插入Space位置坐标
        int targetIndex = resultBytes.length - 1;
        int spaceIndex = spacesLen - 1;
        for (int sourceIndex = sourceLen - 1; sourceIndex >=0;  sourceIndex--) {
            resultBytes[targetIndex--] = resultBytes[sourceIndex];
            if(spaceIndex >= 0 && sourceIndex == spaces[spaceIndex]) {
                resultBytes[targetIndex--] = ' ';
                spaceIndex--;
            }
        }
        return new String(resultBytes);
    }
}
