package amazon.integer_to_english;

// TODO. Recursion递归算法, 取不同位置上的值来匹配映射
// O(1) 递归会的时间复杂度是Linear线性的，只和数字的个数有关
// O(1) 空间复杂度也是线性的，使用常量的内存空间
public class IntegerToEnglishWord {

    private final String[] tens = {"", "", "Twenty", "Thirty", "Forty",
            "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private final String[] belowTwenty = {"", "One", "Two", "Three", "Four",
            "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
            "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    public String numberToWords(int num) {
        return num == 0 ? "Zero": convert(num);
    }

    private String convert(int num) {
        StringBuilder result = new StringBuilder();
        if (num < 20) {
            result.append(belowTwenty[num]);
        } else if (num < 100) {
            result.append(tens[num / 10]).append(" ").append(belowTwenty[num % 10]);
        } else if (num < 1000) {
            result.append(convert(num / 100)).append(" Hundred ").append(convert(num % 100));
        } else if (num < 1000000) {
            result.append(convert(num / 1000)).append(" Thousand ").append(convert(num % 1000));
        } else if (num < 1000000000) {
            result.append(convert(num / 1000000)).append(" Million ").append(convert(num % 1000000));
        } else {
            result.append(convert(num / 1000000000)).append(" Billion ").append(convert(num % 1000000000));
        }
        return result.toString().trim();
    }
}
