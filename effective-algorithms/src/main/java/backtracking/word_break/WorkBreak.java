package backtracking.word_break;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Word Break II
// Given a string s and a dictionary of strings wordDict,
// add spaces in s to construct a sentence where each word is a valid dictionary word.
// Return all such possible sentences in any order.
//
// Note that the same word in the dictionary may be reused multiple times in the segmentation.
public class WorkBreak {

    // TODO. 使用回溯算法来计算可能的排列组合结果
    // "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
    // ["cat sand dog", "cats and dog"]
    //
    // O(N*M) N is length of chars
    // O(M)   M is count of results
    public List<String> wordBreak(String str, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        List<String> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        backtracking(result, str, 0, stringBuilder, set);
        return result;
    }

    // TODO. 回溯算法特征: 将最后的统计结果带到方法的参数中
    //
    // O(N) 每一个回溯到底的计算都遍历全部字符
    private void backtracking(List<String> result, String str, int startIndex, StringBuilder currentSubStr, Set<String> set) {
        if (startIndex == str.length()) {
            result.add(currentSubStr.toString().trim());
            return;
        }
        // endIndex可以取到str.length()值, 在截取后缀子字符串时[左闭右开)
        for (int endIndex = startIndex + 1; endIndex <= str.length(); endIndex++) {
            String suffix = str.substring(startIndex, endIndex);
            if (set.contains(suffix)) {
                int lengthBefore = currentSubStr.length();

                currentSubStr.append(suffix).append(" ");
                backtracking(result, str, endIndex, currentSubStr, set);

                // TODO. 回溯添加suffix后缀之前的长度，继续查找匹配的其他后缀
                currentSubStr.setLength(lengthBefore);
            }
        }
    }
}
