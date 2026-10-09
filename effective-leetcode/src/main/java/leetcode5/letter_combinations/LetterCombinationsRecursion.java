package leetcode5.letter_combinations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Letter Combinations of a Phone Number
//
// digits = "23"
// ["ad","ae","af","bd","be","bf","cd","ce","cf"]
public class LetterCombinationsRecursion {

    // TODO. 递归使用字符串参数性能较差: String必须重新创建

    HashMap<Character, String> digitMapStr = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        digitMapStr.put('2', "abc");
        digitMapStr.put('3', "def");
        digitMapStr.put('4', "ghi");
        digitMapStr.put('5', "jkl");
        digitMapStr.put('6', "mno");
        digitMapStr.put('7', "pqrs");
        digitMapStr.put('8', "tuv");
        digitMapStr.put('9', "wxyz");

        List<String> result = new ArrayList<>();
        recursion(result, digits, 0, "");
        return result;
    }


    private void recursion(List<String> result, String digits, int index, String currentStr) {
        if (index == digits.length()) {
            result.add(currentStr);
            return;
        }

        String str = digitMapStr.get(digits.charAt(index));
        for (char ch : str.toCharArray()) {
            recursion(result, digits, index+1, currentStr + ch);
        }
    }
}
