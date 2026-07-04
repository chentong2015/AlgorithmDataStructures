package live_coding.amazon.integer_to_roman;

// Integer to Roman
// Given an integer, convert it to a Roman numeral.
// 1 <= num <= 3999
public class IntegerToRoman {

    // TODO. 将int值"从大到小"映射，映射一次减一次对应值
    // 3749 -> "MMMDCCXLIX"
    // 3000 = MMM as 1000 (M) + 1000 (M) + 1000 (M)
    //  700 = DCC as 500 (D) + 100 (C) + 100 (C)
    //   40 = XL as 10 (X) less of 50 (L)
    //    9 = IX as 1 (I) less of 10 (X)

    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D",  "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; ++i) {
            if (num == 0) {
                break;
            }
            // 如果大于映射的值，则循环减值并取字母
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
}
