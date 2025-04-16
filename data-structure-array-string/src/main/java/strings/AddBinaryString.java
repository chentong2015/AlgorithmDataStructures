package strings;

// Add Binary
// Given two binary strings a and b, return their sum as a binary string
// a and b consist only of '0' or '1' characters.
// Each string does not contain leading zeros except for the zero itself.
public class AddBinaryString {

    // TODO：从低位往高位读取，从后往前，必须考虑到数值的进位问题 !!
    // Input: a = "1010", b = "1011" -> "10101"
    //
    // O(max(m, n))
    // O(max(m+n))
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }
            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            sb.append(sum % 2); // 求余数作为基础值
            carry = sum / 2;    // 求除数作为上升值
        }
        if (carry != 0) {
            sb.append(carry);   // 最后追加进位值
        }
        return sb.reverse().toString();
    }
}
