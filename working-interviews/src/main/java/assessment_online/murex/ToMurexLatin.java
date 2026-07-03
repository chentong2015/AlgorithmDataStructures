package assessment_online.murex;

public class ToMurexLatin {

    // 对字符串进行指定规则的加工，添加指定的后缀??
    // this asa out test
    // ihts?? asa?? uot?? sett??
    // 至少有一个单词，并且单词之间使用空格隔开
    // O(N) O(N) N为字符串中字符的数目
    public static String toMurextLatin(String suffix, String message) {
        StringBuilder result = new StringBuilder();
        for (String word : message.split(" ")) {
            String formattedWord = formatWord(suffix, word);
            if (!result.isEmpty()) {
                result.append(" ");
            }
            result.append(formattedWord);
        }
        return result.toString();
    }

    private static String formatWord(String suffix, String word) {
        StringBuilder tempWord = new StringBuilder(word.toLowerCase());
        tempWord.reverse();
        char tempChar = tempWord.charAt(0);
        if (tempChar != 'a' && tempChar != 'e' && tempChar != 'i') {
            tempWord.deleteCharAt(0).append(tempChar);
        }
        return tempWord.append(suffix).toString();
    }
}
