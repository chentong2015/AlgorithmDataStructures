package strings;

// Reverse Words in a String
// Given an input string s, reverse the order of the words
// s may contain leading or trailing spaces or multiple spaces between two words
// Return a string of the words in reverse order concatenated by a single space
public class ReverseStringWords {

    // TODO. Split差分字符串后重新拼接
    // s = "the sky is blue" -> "blue is sky the"
    // s = "  hello world " -> "world hello"
    //
    // O(N)
    // O(N)
    public String reverseWords(String str) {
        String[] words = str.trim().split(" ");
        StringBuilder sb = new StringBuilder();

        // 从后往前组合结果，无需反转
        for (int i = words.length - 1; i >= 0; i--) {
            if(!words[i].isEmpty() && i > 0) {
                sb.append(words[i]).append(" ");
            } else {
                sb.append(words[i]);
            }
        }
        return sb.toString();
    }
}
