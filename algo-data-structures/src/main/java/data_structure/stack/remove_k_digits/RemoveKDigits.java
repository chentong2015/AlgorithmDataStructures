package data_structure.stack.remove_k_digits;

public class RemoveKDigits {

    // TODO. 通过Stack来存储开头遍历的数字，优先移除数字前面“更大的数字”，从而降低最后结果
    // Remove K Digits
    // Given string num representing a non-negative integer num, and an integer k
    // return the "smallest possible" integer after removing k digits from num
    // num = "1432219", k = 3 -> 1219
    // num = "10200",   k = 1 -> 200
    // num = "11200",   k = 1 -> 1100
    //
    // 1 <= k <= num.length <= 10^5 严格的大小约束,
    // num consists of only digits  有效数字，最高位不为0
    //
    // O(n + k) 最终会遍历所有的字符，同时将k减小到0
    // O(n)
    public static String removeKDigits(String num, int k) {
        int length = num.length() - k;
        char[] stack = new char[num.length()];

        int countStackChars = 0;
        for (int i = 0; i < num.length(); i++) {
            char currentChar = num.charAt(i);
            // 如果还有剩余的k次数可用，并且栈中末尾上的数据较大，则优先将其移除
            while (k > 0 && countStackChars > 0 && stack[countStackChars - 1] > currentChar) {
                countStackChars--;
                k--;
            }
            stack[countStackChars] = currentChar;
            countStackChars++;
        }
        return getDigitsFromStack(stack, length);
    }

    // 移除stack栈数组中开头的0，从非'0'的位置截取并生成结果
    private static String getDigitsFromStack(char[] stack, int length) {
        int index = 0;
        while (index < length && stack[index] == '0') {
            index++;
        }
        if (index == length) {
            return "0";
        }

        // Params:
        // value – Array that is the source of characters
        // offset – The initial offset
        // count – The length 最后一个参数是截取的长度
        return new String(stack, index, length-index);
    }
}
