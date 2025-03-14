package collections.stack.string_calculator;

import java.util.Stack;

// Basic Calculator
// Given a string s representing a valid expression,
// implement a basic calculator to evaluate it, return the result of the evaluation.
//
// You are not allowed to use any built-in function
// which evaluates strings as mathematical expressions, such as eval().
//
// 1 <= s.length <= 3 * 10^5
// s consists of digits, '+', '-', '(', ')', and ' '.
// s represents a valid expression.
// '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
// '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
// There will be no two consecutive operators in the input.
// Every number and running calculation will fit in a signed 32-bit integer.
public class BasicCalculator {

    // TODO. 典型Stack栈数学表达式: '+', '-', '(', ')'
    // s = "1 + 1" -> 2
    // s = " 2-1 + 2 " -> 3
    // s = "(1+(4+5+2)-3)+(6+8)" -> 23
    //
    // O(N)
    // O(N)
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int total = 0;  // 表示一个括号中的和
        int number = 0; // 表示单独的一个数值
        int sign = 1;   // 表示累计数值时的符号(加为正，减为负)
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10 * number + (c - '0');
            } else if (c == '+') {
                total += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                total += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '('){
                stack.push(total); // 将左括号前面的计算结果保留到栈中
                stack.push(sign);  // Push the result first, then sign;
                sign = 1;          // Reset the sign and result for value in the parenthesis
                total = 0;
            } else if (c == ')') {
                total += sign * number;
                number = 0;           // 累计()括号中的结果和前面保存到栈中的历史结果
                total *= stack.pop(); // stack.pop() is the sign before the parenthesis
                total += stack.pop(); // stack.pop() now is the result calculated before the parenthesis
            }
        }
        if (number != 0) {
            total += sign * number;
        }
        return total;
    }
}
