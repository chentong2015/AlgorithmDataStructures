package backtracking.combination_permutations;

import java.util.ArrayList;
import java.util.List;

// Full Combination and Permutation
// 返回数据的全排列，通过回溯算法DFS进行排序组合 !!
public class AllCombinations {

    public static List<String> findFullPermutation(String value) {
        List<String> result = new ArrayList<>();
        backtracking(result, new StringBuilder(), value.toCharArray(), 0);
        return result;
    }

    // 第一轮 a ab abc ac
    // 第二轮 b bc
    // 第三轮 c
    private static void backtracking(List<String> result, StringBuilder stringBuilder, char[] chars, int start) {
        // 只要stringBuilder非空，便是一个有效的值
        if (!stringBuilder.isEmpty()) {
            result.add(stringBuilder.toString());
        }

        for (int index=start; index < chars.length; index++) {
            stringBuilder.append(chars[index]);

            backtracking(result, stringBuilder, chars, index+1);

            // Remove the last added element
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }
}
