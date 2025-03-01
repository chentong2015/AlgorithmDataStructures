package collections.stack.string_decode;

import java.util.Stack;

// Decode String
// Given an encoded string, return its decoded string
// The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets
// is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
//
// You may assume that the input string is always valid;
// there are no extra white spaces,
// square brackets are well-formed, etc
//
// you may assume that the original data does not contain any digits
// and that digits are only for those repeat numbers,
//
// 1 <= s.length <= 30
// s consists of lowercase English letters, digits, and square brackets '[]'.
// s is guaranteed to be a valid input.
// All the integers in s are in the range [1, 300]
public class DecodeString {

    // TODO: 典型双Stack栈结构: 交替存储并迭代计算
    // "3[a]2[bc]" -> "aaabcbc"
    // "3[a2[c]]" -> "accaccacc"
    // "2[abc]3[cd]ef" -> "abcabccdcdcdef"
    // "abc3[a]bc" -> "abcaaabc"
    //
    // 从strStack栈中取字符串来重复intStack中的次数
    //  intStack  strStack
    //   2         c
    //   3         a
    public String decodeString(String s) {
        Stack<StringBuilder> strStack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();

        // current即存储遍历过程中的片段字符串，表示最后结果字符串
        StringBuilder current = new StringBuilder();

        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';  // k倍数需要考虑十位百位
            } else if (ch == '[') {
                strStack.push(current); // 存储之前的片段字符
                current = new StringBuilder();// 用于重新累计字符

                intStack.push(k);       // 存储[前面用于计算的倍数
                k = 0;                  // k倍数入栈后初始化
            } else if (ch == ']') {
                StringBuilder tmp = current;  // cur字符串需要被重复
                current = strStack.pop();     // 拿到前面入strStack中存储字符，进行拼接
                k = intStack.pop();
                while (k-- > 0) {
                    current.append(tmp);
                }
            } else {
                current.append(ch);
            }
        }
        return current.toString();
    }
}
