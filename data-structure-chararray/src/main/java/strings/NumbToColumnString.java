package strings;

public class NumbToColumnString {

    public static void main(String[] args) {
        System.out.println(NumbToColumnString.convertToColString(1));  // A
        System.out.println(NumbToColumnString.convertToColString(2));  // B
        System.out.println(NumbToColumnString.convertToColString(25)); // Y
        System.out.println(NumbToColumnString.convertToColString(26)); // Z
        System.out.println(NumbToColumnString.convertToColString(27)); // AA
        System.out.println(NumbToColumnString.convertToColString(35)); // AI
        System.out.println(NumbToColumnString.convertToColString(135)); // EE
        System.out.println(NumbToColumnString.convertToColString(1345)); // AYS
    }

    // TODO: 从低位往高位移动判断，计算Char字符的偏移
    public static String convertToColString(int colNum) {
        if (colNum < 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (colNum > 26) {
            int offset = colNum % 26;
            stringBuilder.append((char) (64 + offset));
            colNum /= 26;
        }
        stringBuilder.append((char) (64 + colNum));
        return stringBuilder.reverse().toString();
    }
}
