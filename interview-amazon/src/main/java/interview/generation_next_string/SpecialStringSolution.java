package amazon.OnlineAssessment.generation_next_string;

// https://leetcode.com/discuss/interview-question/5700189/Amazon-Online-Assessment/
public class SpecialStringSolution {

    // TODO. 计算下一个特殊字符串的核心信息: 移动的位置 + 字符升级的偏移量
    static class NextCharacter {
        public int position;
        public int offset;

        public NextCharacter(int position, int offset) {
            this.position = position;
            this.offset = offset;
        }
    }

    public static String getSpecialString(String s) {
        // Find the end position to start getting Next Char
        int lastPosition = s.length() - 1;
        for(int index = 1; index < s.length(); index++) {
            if (s.charAt(index) == s.charAt(index-1)) {
                lastPosition = index;
                break;
            }
        }

        NextCharacter nextCharacter = findUpgradeNextCharacter(s, lastPosition);
        if (nextCharacter.position == -1) {
            return "-1";
        }

        // Build the next special string: beginning + upgrade char + ending AB
        StringBuilder stringBuilder = new StringBuilder();
        for(int index = 0; index < nextCharacter.position; index++) {
            stringBuilder.append(s.charAt(index));
        }

        char oldChar = s.charAt(nextCharacter.position);
        char newChar = (char) (oldChar + nextCharacter.offset);
        stringBuilder.append(newChar);

        appendEndingAB(stringBuilder, nextCharacter.position, s.length());
        return stringBuilder.toString();
    }

    // TODO. 从后往前迭代: 找到第一个能够升级的位置, 如果位置Char已经为‘z'则不能升级
    private static NextCharacter findUpgradeNextCharacter(String s, int lastPosition) {
        int index = lastPosition;
        while (index >= 0) {
            // 当前位置的char无法升级，则往前移动一位
            if (s.charAt(index) == 'z') {
                index--;
                continue;
            }
            if (index == 0) {
                return new NextCharacter(index, 1);
            }

            int offset = 1;
            char nextChar = (char) (s.charAt(index) + offset);
            if (nextChar == s.charAt(index -1)) {
                // 当前位置的char无法升级，则往前移动一位
                if (nextChar == 'z') {
                    index--;
                    continue;
                }
                offset++;
            }
            // 在指定位置按照offset来进行升级
            return new NextCharacter(index, offset);
        }
        return new NextCharacter(-1, 0);
    }

    private static void appendEndingAB(StringBuilder stringBuilder, int start, int end) {
        boolean shouldAddA = true;
        for (int right = start + 1; right < end; right++) {
            if (shouldAddA) {
                stringBuilder.append('a');
                shouldAddA = false;
            } else {
                stringBuilder.append('b');
                shouldAddA = true;
            }
        }
    }
}
