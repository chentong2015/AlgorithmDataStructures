package leetcode3.integer_to_roman;

// Integer to Roman
// Given an integer, convert it to a Roman numeral.
// 1 <= num <= 3999
public class IntegerToRoman {

    // TODO. 从高到低依次构建字符的映射关系
    // 将特殊值的映射也添加到Mapping中
    // 4(IV), 9(IX), 40(XL), 90(XC), 400(CD) and 900(CM)
    //
    // 3749 -> "MMMDCCXLIX"
    // 3000 = MMM as 1000 (M) + 1000 (M) + 1000 (M)
    // 700 = DCC as 500 (D) + 100 (C) + 100 (C)
    //  40 = XL as 10 (X) less of 50 (L)
    //   9 = IX as 1 (I) less of 10 (X)
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D",  "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; ++i) {
            if (num == 0) {
                break;
            }

            // 从映射表中从高到低依次取出字符
            // 取出某个字符后，减去该字符对应的数值
            while (num >= values[i]) {
                sb.append(symbols[i]);
                num -= values[i];
            }
        }
        return sb.toString();
    }
}
