package amazon.online_assessment.generation_next_string;

// 生成下一个特殊字符串
// - 特殊字符串中不能包含两个连续的char是相同的
// - 返回一个字符串能够生成的最小的(字符逻辑上)下一个特殊字符
public class SpecialStringDemo {

    public static String getSpecialString(String s) {
        if (s.length() == 1) {
            if (s == "z") {
                return "-1";
            }
            char nextChar = (char) (s.charAt(0) + 1);
            return String.valueOf(nextChar);
        }

        StringBuilder stringBuilder = new StringBuilder();
        char[] charArray = s.toCharArray();
        stringBuilder.append(charArray[0]);

        // Loop all chars to check if there are adjacent characters
        int index = 1;
        while (index < charArray.length) {
            if (charArray[index] == charArray[index - 1]) {
                if (charArray[index] == 'z') {
                    return "-1";
                }
                char nextChar = (char) (charArray[index] + 1);
                stringBuilder.append(nextChar);
                break;
            } else {
                stringBuilder.append(charArray[index]);
            }
            index++;
        }

        // Check the last character
        if (index == charArray.length) {
            if (charArray[index -1] == 'z') {
                return "-1";
            } else {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);

                // TODO. 最后一个字符升级时必须考虑和前一个字符的冲突
                char nextChar = (char) (charArray[index - 1] + 1);
                if (nextChar == charArray[index - 2]) {
                    while (nextChar != 'z' ) {
                        nextChar = (char) (nextChar + 1);
                    }
                }
                stringBuilder.append(nextChar);
                return stringBuilder.toString();
            }
        }

        // Complete all the last characters as the end of output string
        boolean shouldAddA = true;
        for (int right = index + 1; right < charArray.length; right++) {
            if (shouldAddA) {
                stringBuilder.append('a');
                shouldAddA = false;
            } else {
                stringBuilder.append('b');
                shouldAddA = true;
            }
        }
        return stringBuilder.toString();
    }
}
