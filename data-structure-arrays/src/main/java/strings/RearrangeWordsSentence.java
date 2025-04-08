package strings;

import java.util.ArrayList;
import java.util.List;

// Rearrange Words in a Sentence
// Given a sentence text (A sentence is a string of space-separated words) in the following format:
// 1. First letter is in upper case.
// 2. Each word in text are separated by a single space.
// Rearrange the words in text such that all words are rearranged in an increasing order of their lengths
public class RearrangeWordsSentence {

    // 如果两个单词的长度相同，则按照原来的顺序返回
    // text = "Leetcode is cool"      -> "Is cool core_algo" 结果句子的首字母需要大写
    // text = "Keep calm and code on" -> "On and keep calm code"
    // 1. 1 <= text.length <= 10^5
    // 2. 句子只包含字母和空格
    // O(nlog(n)) O(n) n为word单词的数量
    public static String arrangeWords(String text) {
        if (text == null || !text.contains(" ")) return text;
        List<String> words = new ArrayList<>(List.of(text.split(" ")));
        // TODO: 直接调用List.sort(Comparator<? super E> c)方法，按照自定义的规则进行排序
        //       或者直接使用Arrays.sort();
        //       Merge Sort稳定排序，算法复杂度可能优于nlog(n)
        words.sort((str1, str2) -> {
            if (str1.length() > str2.length()) {
                return 1;
            } else if (str1.length() == str2.length()) {
                return 0;
            } else {
                return -1;
            }
        });

        // String.join(delimiter, charSequence) 可以直接在字符序列中插入分割符号
        // 测试对字符串处理的准确程度
        StringBuilder result = new StringBuilder();
        for (String str : words) {
            if (result.length() == 0) {
                String firstChar = String.valueOf(str.charAt(0));
                result.append(firstChar.toUpperCase());
                result.append(str.substring(1));
            } else {
                result.append(" ");
                result.append(str.toLowerCase());
            }
        }
        return result.toString();
    }
}
