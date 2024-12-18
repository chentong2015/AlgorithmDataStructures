package amazon.question_interview.num_to_english;

public class NumWordsConversion {

    // TODO. 题目的本质是罗列出所有的Case，使用最少的判断条件来计算
    private final String[] belowTwenty = {"", "One", "Two", "Three", "Four",
            "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
            "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] tens = {"", "", "Twenty", "Thirty", "Forty",
            "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        return helper(num);
    }

    // 递归会的时间复杂度是线性的，只和数字的个数有关
    // 空间复杂度也是线性的，使用常量的内存空间
    private String helper(int num) {
        StringBuilder result = new StringBuilder();
        if (num < 20) {
            result.append(belowTwenty[num]);
        } else if (num < 100) {
            result.append(tens[num / 10]).append(" ").append(belowTwenty[num % 10]);
        } else if (num < 1000) {
            result.append(helper(num / 100))
                    .append(" Hundred ")
                    .append(helper(num % 100));
        } else if (num < 1000000) {
            result.append(helper(num / 1000))
                    .append(" Thousand ")
                    .append(helper(num % 1000));
        } else if (num < 1000000000) {
            result.append(helper(num / 1000000))
                    .append(" Million ")
                    .append(helper(num % 1000000));
        } else {
            result.append(helper(num / 1000000000))
                    .append(" Billion ")
                    .append(helper(num % 1000000000));
        }
        return result.toString().trim();
    }
}
