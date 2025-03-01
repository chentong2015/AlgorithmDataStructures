package collections.stack;

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

    // TODO:
    // "3[a]2[bc]" -> "aaabcbc"
    // "3[a2[c]]" -> "accaccacc"
    // "2[abc]3[cd]ef" -> "abcabccdcdcdef"
    public String decodeString(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';   // 将数字从高位向低位整合起来
            } else if (ch == '[') {      // 将统计出来的数字入栈，将[后面积累的cur字符串入栈 !!
                intStack.push(k);
                strStack.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (ch == ']') {      // ]  出现的时候需要decode字符，重复指定的次数
                StringBuilder tmp = cur; // c  cur中存储的是需要重复的
                cur = strStack.pop();    // a+ 再拿到前面入strStack中的存储的字符，进行拼接
                for (k = intStack.pop(); k > 0; k--) {
                    cur.append(tmp);
                }
            } else {
                cur.append(ch);
            }
        }
        return cur.toString();
    }
}
