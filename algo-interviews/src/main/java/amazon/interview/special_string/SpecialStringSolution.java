package amazon.interview.special_string;

// TODO. 必须先找到能够升级的position位置 ?
// https://leetcode.com/discuss/interview-question/5700189/Amazon-Online-Assessment/
public class SpecialStringSolution {

    // a  b  b  d
    //
    // a  b  c  a/b/d   -->  第一个出现的将是结果
    // a  b  d  a
    public static String getSpecialString(String s) {
        // Check for one character

        StringBuilder stringBuilder = new StringBuilder();
        char[] charArray = s.toCharArray();
        stringBuilder.append(charArray[0]);

        int index = 1;
        while (index < charArray.length) {
            // ...
            index++;
        }
        return stringBuilder.toString();
    }

    // TODO. 可以通过Stack来递归查找后续能够升级的位置
    private static int findUpgradePosition(char[] charArray) {
        return 0;
    }

    private static String buildLastSubString() {
        return "";
    }
}
