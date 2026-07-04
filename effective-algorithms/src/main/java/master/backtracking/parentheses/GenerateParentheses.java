package master.backtracking.parentheses;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    // TODO. 回溯法生成所有括号的排序组合
    // Generate Parentheses
    // Given n pairs of parentheses
    // write a function to generate all combinations of well-formed parentheses
    // n = 3 -> ["((()))","(()())","(())()","()(())","()()()"]
    //
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        // 最终的结果要带到回溯参数中，传递中间过程的值
        backtrack(result, stringBuilder, 0, 0, n);
        return result;
    }

    // 每种递归的方法中都有两种if可以执行，反复穷举出所有可能
    public void backtrack(List<String> result, StringBuilder currentStr, int open, int close, int max) {
        if (currentStr.length() == max * 2) {
            result.add(currentStr.toString());
            return; // 符合条件后返回，避免无限递归
        }

        if (open < max) { // 先添加左侧括号
            currentStr.append("(");
            backtrack(result, currentStr, open + 1, close, max);
            currentStr.deleteCharAt(currentStr.length() - 1); // 回溯的核心：递归完成之后，取出尾部的字符 !!
        }
        if (close < open) { // 右侧括号比左侧括号少，则补充添加
            currentStr.append(")");
            backtrack(result, currentStr, open, close + 1, max);
            currentStr.deleteCharAt(currentStr.length() - 1);
        }
    }
}
