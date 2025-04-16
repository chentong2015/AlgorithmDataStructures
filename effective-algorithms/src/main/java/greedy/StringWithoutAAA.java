package greedy;

// String Without AAA or BBB
// Given two integers a and b, return any string s such that:
// - s has length a + b and contains exactly a 'a' letters, and exactly b 'b' letters
// - The substring 'aaa' does not occur in s,
// - The substring 'bbb' does not occur in s.
//
// 0 <= a, b <= 100
// It is guaranteed such an s exists for the given a and b.
public class StringWithoutAAA {

    // TODO. Greedy Algo 每次选择字符时都保证是最优选择
    // 相差数值大于等于2，则选择大数值的字符两个
    //
    // a = 1, b = 2
    // "abb", "bab" and "bba"
    //
    // a = 1, b = 3
    // "bbab"
    //
    // a = 4, b = 1
    // "aabaa"
    //
    // a = 3, b = 8
    // bb a bb a bb a bb
    //
    // a = 4, b = 4
    // aa bb aa bb
    // ab ab ab ab
    public String strWithout3a3b(int a, int b) {
        StringBuilder stringBuilder = new StringBuilder();

        // 每一步都保证是最优选择
        while (a > 0 && b > 0) {
            if (a - b >= 2) {
                stringBuilder.append("aa");
                a -= 2;
                stringBuilder.append("b");
                b--;
            } else if (b - a >= 2) {
                stringBuilder.append("bb");
                b -= 2;
                stringBuilder.append("a");
                a--;
            } else {
                stringBuilder.append("a");
                a--;
                stringBuilder.append("b");
                b--;
            }
        }

        // 因为一定有解，所以剩余的数目一定小于3个
        if (a == 1) {
            stringBuilder.append("a");
        } else if (a == 2) {
            stringBuilder.append("aa");
        }

        if (b == 1) {
            stringBuilder.append("b");
        } else if (b == 2) {
            stringBuilder.append("bb");
        }

        return stringBuilder.toString();
    }
}
