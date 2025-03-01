package collections.stack;

import java.util.Stack;

// Check If Word Is Valid After Substitutions
// Given a string s, determine if it is valid.
// A string s is valid if, starting with an empty string t = "",
// you can transform t into s after performing the following operation any number of times:
//  - Insert string "abc" into any position in t.
//  - t becomes tleft + "abc" + tright, where t == tleft + tright
//  - Note that tleft and tright may be empty.
// Return true if s is a valid string, otherwise, return false.
//
// 1 <= s.length <= 2 * 10^4
// s consists of letters 'a', 'b', and 'c'
public class ValidWordAfterSubstitutions {

    // TODO. Stack使用栈来判断是否满足ABC Balance平衡
    // "aabcbc" -> true
    // "" -> "abc" -> "aabcbc"
    //
    // "abcabcababcc" -> true
    // "" -> "abc" -> "abcabc" -> "abcabcabc" -> "abcabcababcc"
    //
    // "abccba" -> false
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c: s.toCharArray()) {
            if (stack.isEmpty()) {
                if (c == 'b' || c == 'c') {
                    return false;
                }
                stack.push(c);
            } else {
                if (c == 'a') {
                    stack.push(c);
                } else if (c == 'b') {
                    if (stack.peek() != 'a') {
                        return false;
                    }
                    stack.push(c);
                } else {
                    if (stack.peek() != 'b') {
                        return false;
                    }
                    stack.pop(); // pop 'b'
                    stack.pop(); // pop 'a'
                }
            }
        }
        return stack.isEmpty();
    }
}
