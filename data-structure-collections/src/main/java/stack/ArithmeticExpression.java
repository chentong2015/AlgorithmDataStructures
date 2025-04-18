package stack;

import java.util.Stack;

// Evaluate Reverse Polish Notation
// Evaluate the value of an arithmetic expression in Reverse Polish Notation
// Valid operators are +, -, *, and /. Each operand may be an integer or another expression
// It is guaranteed that the given RPN expression is always valid, always evaluate to result
public class ArithmeticExpression {

    // TODO. 典型Stack栈场景: 算术表达式, 利用栈的存取进行计算
    // tokens = ["2","1","+","3","*"]
    // ((2 + 1) * 3) = 9
    //
    // O(n)
    // O(n) 运算的符号和运算数相差为1，大概为整个tokens长度的一半
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (stack.isEmpty()) {
                stack.push(Integer.parseInt(token));
            } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int value = stack.pop();    // 如果是运算符号，则栈中一定有两个运算数据可以使用 !!
                int newValue = stack.pop();
                if (token.equals("+")) {
                    stack.push(value + newValue);
                }
                if (token.equals("-")) {
                    stack.push(newValue - value); // 注意运算符的计算顺序
                }
                if (token.equals("*")) {
                    stack.push(value * newValue);
                }
                if (token.equals("/")) {
                    stack.push(newValue / value); // 前面的值除以后面的值
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.peek();
    }
}
