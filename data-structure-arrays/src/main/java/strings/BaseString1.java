package strings;

public class BaseString1 {

    // TODO: 从int整数转成char，再使用StringBuilder来拼接字符串
    //  1. 使用'A'+const int 必须使用具体的值作为偏移量
    //  2. 使用(char)(65+value) 使用变量作为偏移，然后强制转换成char
    public static String convertColNumber(int col) {
        if (col < 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int remaining = col;
        while (remaining > 0) {
            int offset = (remaining - 1) % 26;
            char c = (char) (65 + offset);
            stringBuilder.append(c);
            remaining = (remaining - offset) / 26;
        }
        return stringBuilder.reverse().toString();
    }

    // TODO：充分使用字符串中字符直接的运算 .charAt(index)-'0' 从char转换到int来计算 !!
    // Add Binary
    // Given two binary strings a and b, return their sum as a binary string
    // a and b consist only of '0' or '1' characters.
    // Each string does not contain leading zeros except for the zero itself.
    // Input: a = "1010", b = "1011" -> "10101"
    public String addBinary(String a, String b) {
        // 正确理解：1. 需要从char转换成int来计算 O(max(m, n)) O(max(m+n))
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;          // 从低位往高位读取，从后往前，必须考虑到数值的进位问题 !!
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {       // 不用比较那个字符串更长，统一读取，每次累加两个相同位置上面的值 !!
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);          // 求余数作为基础值
            carry = sum / 2;             // 求除数作为上升值
        }
        if (carry != 0) sb.append(carry);// 最后追加进位值
        return sb.reverse().toString();  // 注意.reverse()复杂度
    }

    // Implement strStr() / Java indexOf()
    // Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack
    // haystack = "hello", needle = "ll" -> index = 2
    public int strStr(String haystack, String needle) {
        // 测试理解：1. 在没有别的解法时，直接使用常规解法 O(n*m) O(1) !!
        if (needle == null || needle.isEmpty()) {
            return 0;
        }
        if (haystack == null || haystack.length() < needle.length()) {
            return -1;
        }
        for (int index = 0; index < haystack.length(); index++) {
            for (int j = 0; j < needle.length(); j++) {
                if (index + j == haystack.length()) return -1;
                if (haystack.charAt(index + j) != needle.charAt(j)) break;  // 在内部判断3种可能的情况
                if (j == needle.length() - 1) return index;
            }
        }
        return -1;
    }
}
