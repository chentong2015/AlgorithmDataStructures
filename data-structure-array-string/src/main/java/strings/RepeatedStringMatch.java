package strings;

// Repeated String Match
// Given two strings a and b,
// return the minimum number of times you should repeat string a
// so that string b is a substring of it.
// If it is impossible for b to be a substring of a after repeating it, return -1.
//
// 1 <= a.length, b.length <= 10^4
// a and b consist of lowercase English letters.
public class RepeatedStringMatch {

    // TODO. 只需要在长度相等和长度超过时判断两次
    // a = "abcd", b = "cdabcdab" -> 3
    // -> abcdabcdabcd
    //
    // a = "a", b = "aa" -> 2
    // -> aaa
    //
    // O(N*M)
    // O(N)
    public int repeatedStringMatch(String a, String b) {
        int result = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (stringBuilder.length() < b.length()) {
            result++;
            stringBuilder.append(a);
        }

        // TODO. 使用KMP模板优化对字符串包含的判断
        if (stringBuilder.toString().contains(b)) {
            return result;
        }
        if (stringBuilder.append(a).toString().contains(b)) {
            return result + 1;
        }
        return -1;
    }
}
