package strings.reversed;

public class ReversedStringBits {

    // TODO. 只需将“反转字符串序列”中不匹配的字符”甩“到末尾重新组合
    // 要移动的步数是”甩“到末尾字符数目，每次只要”甩“的正确字符，成功的步数一定最小
    // String Manipulation
    // given a binary representation of string type.
    // in one operation you can take any bit and append at the end of string.
    // return the minimum number of operations required until string matches with its reversed string
    //
    // input1 = "00110101" output = 3
    // input2 = "1010100" output = 3
    //
    // 0 0 1’ 1 0’ 1’ 0’ 1’ -> 只关系不同的bit位置, 先1再0最后0
    // 1 0 1  0 1  1  0  0
    //
    // 1 0’ 1 0’ 1’ 0’ 0
    // 0 0  1 0  1  0  1
    public static int minStepsToReversedString(String value) {
        char[] chars = value.toCharArray();
        int n = chars.length;
        int commonPrefix = 0;
        for (char aChar : chars) {
            if (aChar == chars[n - 1 - commonPrefix]) {
                commonPrefix++;
            }
        }
        // 最后要移动的字符便是排除掉公共前缀的字符数目
        return n - commonPrefix;
    }
}
