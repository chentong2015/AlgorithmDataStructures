package interview_coding.amazon.integer_to_english;

// TODO. 本质是Recursion递归算法, 映射不同位置上的值
// O(1) 递归会的时间复杂度是Linear线性的，只和数字的个数有关
// O(1) 空间复杂度也是线性的，使用常量的内存空间
public class IntegerToEnglishWord {

    // Index 通过坐标映射英语单词
    private final String[] beforeTwenty = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    // Index=0,1 必须设置空字符串
    private final String[] afterTwenty = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        return convert(num);
    }

    private String convert(int num) {
        StringBuilder result = new StringBuilder();
        if (num < 20) {
            result.append(beforeTwenty[num]); // 直接个位数映射
        } else if (num < 100) {
            result.append(afterTwenty[num / 10]) // 十位数映射
                  .append(" ")
                  .append(beforeTwenty[num % 10]); // 个位数映射
        } else if (num < 1000) {
            result.append(convert(num / 100))
                  .append(" Hundred ")
                  .append(convert(num % 100));
        } else if (num < 1000000) {
            result.append(convert(num / 1000))
                  .append(" Thousand ")
                  .append(convert(num % 1000));
        } else {
            result.append(convert(num / 1000000))
                  .append(" Million ")
                  .append(convert(num % 1000000));
        }
        return result.toString().trim();
    }
}
