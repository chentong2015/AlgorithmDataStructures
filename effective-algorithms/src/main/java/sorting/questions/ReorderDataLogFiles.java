package sorting.questions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// Reorder Data in Log Files
// You are given an array of logs.
// Each log is a space-delimited string of words, where the first word is the identifier.
// Letter-logs: All words (except the identifier) consist of lowercase English letters.
// Digit-logs: All words (except the identifier) consist of digits.
//
// 1. The letter-logs come before all digit-logs.
// 2. The letter-logs are sorted lexicographically by their contents.
//    If their contents are the same, then sort them lexicographically by their identifiers.
// 3. The digit-logs maintain their relative ordering.
//
// 1 <= logs.length <= 100
// 3 <= logs[i].length <= 100
// All the tokens of logs[i] are separated by a single space.
// logs[i] is guaranteed to have an identifier and at least one word after the identifier.
public class ReorderDataLogFiles {

    // logs = ["dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"]
    // Output: ["let1 art can", "let3 art zero", "let2 own kit dig", "dig1 8 1 5 1", "dig2 3 6"]
    //
    // logs = ["a1 9 2 3 1", "g1 act car","zo4 4 7", "ab1 off key dog", "a8 act zoo"]
    // Output: ["g1 act car", "a8 act zoo", "ab1 off key dog", "a1 9 2 3 1", "zo4 4 7"]
    //
    // O(N + K*logK) 只需要排序Letter数目的排序复杂度
    // O(1)          不需要空间复杂度，直接利用原数组操作
    public String[] reorderLogFiles(String[] logs) {
        int right = logs.length - 1;
        for (int index = logs.length - 1; index >= 0; index--) {
            String log = logs[index];
            char c = log.split(" ")[1].charAt(0);
            if (Character.isDigit(c)) {
                String temp = logs[right];
                logs[right] = log;
                logs[index] = temp;
                right--;
            }
        }
        Arrays.sort(logs, 0, right + 1, (log1, log2) -> {
            // 直接截取Content内容数据
            String content1 = log1.substring(log1.indexOf(" "));
            String content2 = log2.substring(log2.indexOf(" "));

            // TODO. 直接比较log等效于直接比较Identifier
            int result = content1.compareTo(content2);
            return result == 0 ? log1.compareTo(log2) : result;
        });
        return logs;
    }
}