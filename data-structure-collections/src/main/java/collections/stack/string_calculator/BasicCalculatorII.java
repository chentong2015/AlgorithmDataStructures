package collections.stack.string_calculator;

import java.util.Stack;

// Basic Calculator II
// Given a string s which represents an expression, evaluate this expression and return its value.
// The integer division should truncate toward zero.
//
// You may assume that the given expression is always valid
// All intermediate results will be in the range of [-2^31, 2^31 - 1].
//
// You are not allowed to use any built-in function
// which evaluates strings as mathematical expressions, such as eval().
//
// 限制Test Case是在有效值的范围内 !
// 1 <= s.length <= 3 * 10^5
// s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
// s represents a valid expression.
// All the integers in the expression are non-negative integers in the range [0, 2^31 - 1].
// The answer is guaranteed to fit in a 32-bit integer.
public class BasicCalculatorII {

    // TODO. 典型Stack栈数学表达式: '+', '-', '*', '/' 四则运算
    //  乘法和除法直接计算, 加法存储正数, 减法存储负数
    // s = "3+2*2+1" -> 8
    // s = " 3/2 " -> 1
    // s = " 3+5 / 2 " -> 5
    // s = "1-1+1" -> 1
    //
    // TODO. 注意两个变量移动值的位置
    // +  3  +  2  *  2  +  1
    // pO cV
    //       pO cV nC ->
    //
    // O(N) N是所有字符的数目
    // O(M) M是数字的数目，最差情况下栈会存储所有的数字
    public int calculate(String s) {
        s = s.replace(" ", "");

        // 使用遍历来记录前一个操作符号并用于计算
        int currentValue = 0;
        char previousOperator = '+';

        Stack<Integer> stack = new Stack<>();
        for (int index = 0; index < s.length(); index++) {
            char newChar = s.charAt(index);

            // 不断累计个位数的值, 计算数字的偏移量
            if (Character.isDigit(newChar)) {
                currentValue = currentValue * 10 + (newChar - '0');
            }

            // 当读取"下一个操作符"或"数字读完"到末尾时，需要入栈计算
            if (!Character.isDigit(newChar) || index == s.length() -1) {
                if (previousOperator == '+') {
                    stack.push(currentValue);
                } else if (previousOperator == '-') {
                    stack.push(-currentValue);
                } else if (previousOperator == '*') {
                    stack.push(stack.pop() * currentValue);
                } else {
                    stack.push(stack.pop() / currentValue);
                }

                // 更新前一个操作符号到新读取的操作符
                previousOperator = newChar;
                currentValue = 0;
            }
        }

        // 累计Stack中所有数据和(正负数自然计算)
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
}
