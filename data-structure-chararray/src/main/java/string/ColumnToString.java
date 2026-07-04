package string;

public class ColumnToString {

    public static void main(String[] args) {
        System.out.println(ColumnToString.convertColNumber(1));  // A
        System.out.println(ColumnToString.convertColNumber(2));  // B
        System.out.println(ColumnToString.convertColNumber(25)); // Y
        System.out.println(ColumnToString.convertColNumber(26)); // Z
        System.out.println(ColumnToString.convertColNumber(27)); // AA
        System.out.println(ColumnToString.convertColNumber(35)); // AI
        System.out.println(ColumnToString.convertColNumber(135)); // EE
        System.out.println(ColumnToString.convertColNumber(1345)); // AYS
    }

    // TODO: 从低位往高位移动判断，计算Char字符的偏移
    public static String convertColNumber(int colNum) {
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
