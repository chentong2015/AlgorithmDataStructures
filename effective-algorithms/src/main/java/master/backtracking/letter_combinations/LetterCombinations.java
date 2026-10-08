package master.backtracking.letter_combinations;

import java.util.ArrayList;
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
public class LetterCombinations {

    // TODO. 普通算法: 例举所有字符组合的结果
    // digits = "23"
    // ["ad","ae","af","bd","be","bf","cd","ce","cf"]
    //
    // digits = "2"
    // ["a","b","c"]

    public List<String> letterCombinations(String digits) {
        List<StringBuilder> list = new ArrayList<>();
        int digit = Integer.parseInt(String.valueOf(digits.charAt(0)));
        for (char c : getChars(digit)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(c);
            list.add(stringBuilder);
        }
        for (int index = 1; index < digits.length(); index++) {
            digit = Integer.parseInt(String.valueOf(digits.charAt(index)));
            combine(list, digit);
        }

        List<String> result = new ArrayList<>();
        for (StringBuilder stringBuilder : list) {
            result.add(stringBuilder.toString());
        }
        return result;
    }

    // TODO. List遍历的时候不能修改, 必须额外处理 !!
    private void combine(List<StringBuilder> list, int digit) {
        List<StringBuilder> temp = new ArrayList<>(list);
        list.clear();
        for (StringBuilder letterBuilder : temp) {
            for (char c : getChars(digit)) {
                StringBuilder newSb = new StringBuilder(letterBuilder);
                newSb.append(c); // 创建一个新的SB
                list.add(newSb);
            }
        }
    }

    private char[] getChars(int digit) {
        if (digit == 2) {
            return new char[] {'a', 'b', 'c'};
        } else if (digit == 3) {
            return new char[] {'d', 'e', 'f'};
        } else if (digit == 4) {
            return new char[] {'g', 'h', 'i'};
        } else if (digit == 5) {
            return new char[] {'j', 'k', 'l'};
        } else if (digit == 6) {
            return new char[] {'m', 'n', 'o'};
        } else if (digit == 7) {
            return new char[] {'p', 'q', 'r', 's'};
        } else if (digit == 8) {
            return new char[] {'t', 'u', 'v'};
        } else {
            return new char[] {'w', 'x', 'y', 'z'};
        }
    }
}
