package templates.sorting.heap_sort;

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
// 重排letter-logs数据，digit-logs数据保持不变(顺序不能打乱)
// logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
// Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
public class ReorderDataLogFiles {

    // "dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"
    // 0               1               2           3                   4
    //
    // 1               4               3           0                   1
    //
    // O(N*log(N)) 无论何种方式，排序的复杂度无法省略
    // O(N)
    public String[] reorderLogFiles(String[] logs) {
        // Sort all digit-logs to the end
        int right = logs.length - 1;
        for (int index= logs.length - 1; index >= 0; index--) {
            String content = getContent(logs[index]);
            if (isDigitLogs(content)) {
                switchLogs(logs, index, right);
                right--;
            }
        }

        // Build Min-Heap 只使用一部分来进行堆排序
        PriorityQueue<HeapNode> priorityQueue = new PriorityQueue<>(new Comparator<HeapNode>() {
            @Override
            public int compare(HeapNode node1, HeapNode node2) {
                int compareResult = node1.content.compareTo(node2.content);
                if (compareResult == 0) {
                    // If their content are the same, sort by identifier
                    return node1.identifier.compareTo(node2.identifier);
                }
                return compareResult;
            }
        });
        for(int index=0; index <= right; index++) {
            String log = logs[index];
            HeapNode node = new HeapNode(getIdentifier(log), getContent(log));
            priorityQueue.offer(node);
        }

        // Add sorted letter-logs at the beginning
        int left = 0;
        while (!priorityQueue.isEmpty()) {
            HeapNode node = priorityQueue.poll();
            logs[left] = node.identifier + " " + node.content;
            left++;
        }
        return logs;
    }

    private String getIdentifier(String log) {
        return log.split(" ")[0];
    }

    private String getContent(String log) {
        return log.substring(log.indexOf(" ") + 1);
    }

    private boolean isDigitLogs(String content) {
        return Character.isDigit(content.charAt(0));
    }

    private void switchLogs(String[] logs, int index1, int index2) {
        String temp = logs[index1];
        logs[index1] = logs[index2];
        logs[index2] = temp;
    }

    static class HeapNode {
        String identifier;
        String content; // the log content

        public HeapNode(String identifier, String content) {
            this.identifier = identifier;
            this.content = content;
        }
    }
}
