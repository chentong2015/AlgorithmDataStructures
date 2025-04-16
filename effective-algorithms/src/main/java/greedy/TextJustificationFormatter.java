package greedy;

import java.util.ArrayList;
import java.util.List;

// Text Justification
//
// words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
// Output:
// [
//   "This    is    an",
//   "example  of text",
//   "justification.  "
// ]
//
// Format the string from [startIndex, endIndex)
// 1. Pack as many words as you can in each line
// 2. Pad extra spaces ' ' so that each line has exactly maxWidth characters.
// 3. If the number of spaces on a line does not divide evenly between words,
//    the empty slots on the left will be assigned more
// 4. For the last line of text, it should be left-justified
//
// words[i].length <= maxWidth 保证一行至少能够放下一个完整单词，没有截断的操作
// Build formatted word with spaces 在构建时需要合理分配空格位置，最后一个单词不分配
// "This " 5 +1 +1 +1 = 8
// "is " 3 +1 +1 = 5
// "an", 2 = 2
public class TextJustificationFormatter {

    public static void main(String[] args) {
        TextJustificationFormatter formatter = new TextJustificationFormatter();
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        String[] words1 = {"What","must","be","acknowledgment","shall","be"};

        List<String> result = formatter.fullJustify(words1, 16);
        for (String item: result) {
            System.out.println(item);
        }
    }

    // TODO. 算法使用的leftSpaces空间复杂度可以优化
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> resultLines = new ArrayList<>();
        int[] leftSpaces = new int[words.length];

        int index =0;
        while (index < words.length) {
            // Greedy贪心算法思想，每行尽量选择更多数量的单词
            int countLength = words[index].length();
            int endIndex= index + 1;
            while (endIndex < words.length && countLength <= maxWidth) {
                int nextLength = 1 + words[endIndex].length();
                if (countLength + nextLength > maxWidth) {
                    break;
                }
                countLength += nextLength;
                endIndex++;
            }

            int totalRemainingSpaces = maxWidth - countLength;
            String line = buildLine(words, leftSpaces, index, endIndex, totalRemainingSpaces);

            // 最后一行因为是左对齐，因此需要额外再补充空格
            if (line.length() < maxWidth) {
                String lastLine = buildFormattedWord(line, maxWidth - line.length());
                resultLines.add(lastLine);
            } else {
                resultLines.add(line);
            }
            index = endIndex;
        }
        return resultLines;
    }

    // TODO. 将指定数量的单词构建成格式化的长度
    private String buildLine(String[] words, int[] leftSpaces, int startIndex, int endIndex, int totalRemainingSpaces) {
        // There is only one word at this line, so left-justified
        if (startIndex + 1 == endIndex) {
            return buildFormattedWord(words[startIndex], totalRemainingSpaces);
        }

        // The last line must be left-justified instead of fully-justified.
        if (endIndex < words.length) {
            calculateLeftSpaces(leftSpaces, startIndex, endIndex, totalRemainingSpaces);
        }

        StringBuilder lineBuilder = new StringBuilder();
        for (int idx = startIndex; idx < endIndex - 1; idx++) {
            String formattedWord = buildFormattedWord(words[idx], 1 + leftSpaces[idx]);
            lineBuilder.append(formattedWord);
        }
        lineBuilder.append(words[endIndex-1]);
        return lineBuilder.toString();
    }

    private void calculateLeftSpaces(int[] leftSpaces, int startIndex, int endIndex, int totalRemainingSpaces) {
        int index = startIndex;
        while (totalRemainingSpaces > 0) {
            leftSpaces[index]++;
            index++;
            if (index == endIndex - 1) {
                index = startIndex;
            }
            totalRemainingSpaces--;
        }
    }

    private String buildFormattedWord(String word, int remainingSpaces) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(word);

        for (int index=0; index < remainingSpaces; index++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}
