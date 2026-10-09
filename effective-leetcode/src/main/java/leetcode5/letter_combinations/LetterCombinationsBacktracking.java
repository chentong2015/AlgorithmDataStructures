package leetcode5.letter_combinations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Letter Combinations of a Phone Number
// Given a string containing digits from 2-9 inclusive,
// return all possible letter combinations that the number could represent.
// Return the answer in any order.
//
// A mapping of digits to letters (just like on the telephone buttons) is given below.
// Note that 1 does not map to any letters.
//
// 1 <= digits.length <= 4
// digits[i] is a digit in the range ['2', '9'].
public class LetterCombinationsBacktracking {

    // TODO. 使用StringBuilder提高字符串的拼接速度
    // digits = "23"
    // ["ad","ae","af","bd","be","bf","cd","ce","cf"]
    //
    // digits = "2"
    // ["a","b","c"]

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
        backtracking(result, digits, 0, new StringBuilder());
        return result;
    }

    private void backtracking(List<String> result, String digits, int index, StringBuilder currentSb) {
        if (index == digits.length()) {
            result.add(currentSb.toString());
            return;
        }

        String str = digitMapStr.get(digits.charAt(index));
        for (char ch : str.toCharArray()) {
            currentSb.append(ch);
            backtracking(result, digits, index+1, currentSb);

            // 回溯最后添加的字符，避免累计给下个循环
            currentSb.deleteCharAt(currentSb.length() - 1);
        }
    }
}
