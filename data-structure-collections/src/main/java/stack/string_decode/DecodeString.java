package stack.string_decode;

import java.util.Stack;

// Decode String
// Given an encoded string, return its decoded string
// The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets
// is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
//
// You may assume that the input string is always valid;
// there are no extra white spaces, square brackets are well-formed, etc
//
// you may assume that the original data does not contain any digits
// and that digits are only for those repeat numbers,
//
// 1 <= s.length <= 30
// s consists of lowercase English letters, digits, and square brackets '[]'.
// s is guaranteed to be a valid input.
// All the integers in s are in the range [1, 300]
public class DecodeString {

    // TODO: 典型双Stack栈结构: 遇到左方括号存, 遇到右方括号算(从内往外)
    // "3[a]2[bc]" -> "aaabcbc"
    // "3[a2[c]]" -> "accaccacc"
    // "2[abc]3[cd]ef" -> "abcabccdcdcdef"
    // "abc3[a]bc" -> "abcaaabc"
    //
    // 从strStack栈中取字符串来重复intStack中的次数
    //  intStack  strStack
    //   2         bc
    //   3         a
    public String decodeString(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();

        // finalStrB 遍历过程中片段字符串，也是最后字符串
        StringBuilder finalStrB = new StringBuilder();

        int num = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';  // 倍数需要考虑十位百位
            } else if (ch == '[') {
                strStack.push(finalStrB);   // 存储之前的片段字符, 重新累计
                finalStrB = new StringBuilder();

                intStack.push(num);         // 存储[前面用于计算的倍数
                num = 0;
            } else if (ch == ']') {          // TODO. 遇到]则一定可以计算
                StringBuilder repeatStr = finalStrB;
                finalStrB = strStack.pop();  // 取出strStack栈中存储的原来字符
                num = intStack.pop();
                while (num-- > 0) {
                    finalStrB.append(repeatStr);
                }
            } else {
                finalStrB.append(ch); // TODO. 没有数字则直接拼接字符
            }
        }
        return finalStrB.toString();
    }
}