package data_structure.arraystring.diff_string;

// TODO. 使用最佳的复杂度比较两个字符串之间的字符差异  > 扩展到相差n个字符?
// 通过字符统计+抵消来判断，无需遍历两次完整的CharArray ！
// StringA = 00000200001000010001..
// StringB = 00000100000000-100000..
public class DiffStringByChars {

    public static void main(String[] args) {
        // Test Diff one char
        System.out.println(stringsDiffByOneChar("leet", "leat"));
        System.out.println(stringsDiffByOneChar("leet", "lead"));

        // Test Diff two chars
        System.out.println(stringsDiffByOneChar("chen", "cheh"));
        System.out.println(stringsDiffByOneChar("chen", "chch"));
    }

    // TODO. 快速判断两个字符串是否相差一个字符
    private static boolean stringsDiffByOneChar(String strA, String strB) {
        int diffCount = 0;
        int[] counts = new int[26];
        for (char c: strA.toCharArray()) {
            counts[c - 'a']++;
            diffCount++;
        }
        for (char c: strB.toCharArray()) {
            // 每当抵消一个相同的字符，降低统计的结果
            if (counts[c - 'a'] > 0) {
                counts[c - 'a']--;
                diffCount--;
            }
        }
        return diffCount==1;
    }

    // 由于两个字符串必须长度一致, 因此哪个字符串先统计无关紧要
    private static boolean stringsDiffByOneChar2(String strA, String strB) {
        if (strA.length() != strB.length()) {
            return false;
        }
        int[] counts = new int[26];
        for (char c: strA.toCharArray()) {
            counts[c - 'a']++;
        }

        int countDiffChar = 0;
        for (char c: strB.toCharArray()) {
            // 当位置值为0: 出现新的字符/或者之前记录的字符已经被抵消了
            // 当位置小于0: 说明是之前出现的新的字符
            if (counts[c - 'a'] <= 0) {
                countDiffChar++;
            }

            // strB的新字符(差异的字符)不能超过一个
            // TODO. 修改判断条件精确控制差异的字符数目
            if (countDiffChar > 1) {
                return false;
            }
            counts[c - 'a']--;
        }
        return true;
    }
}
