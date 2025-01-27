package wothers.math;

import java.util.ArrayList;
import java.util.List;

// Sequential Digits
// An integer has sequential digits
// if and only if each digit in the number is one more than the previous digit.
// Return a sorted list of all the integers
// in the range [low, high] inclusive that have sequential digits.
//
// 10 <= low <= high <= 10^9 注意最大的数值位数是9位
//
// low = 10, high = 100 -> [12,23,34,45,56,67,78,89]
// low = 58, high = 155 -> [67,78,89,123]
// low = 100, high = 300 -> [123,234]
// low = 1000, high = 13000 -> [1234,2345,3456,4567,5678,6789,12345]
public class SequentialDigits {

    // TODO. 直接对最后的结果进行一一遍历，跳过所有无效的值
    // 最小值和最大值数据之间的结果是有规律
    // O(1)
    // O(1)
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();

        int lowTemp = low;
        int lowLength = 1;
        while (lowTemp >= 10) {
            lowLength++;
            lowTemp /= 10;
        }

        int highTemp = high;
        int highLength = 1;
        while (highTemp >= 10) {
            highLength++;
            highTemp /= 10;
        }

        int length = lowLength;
        while (length <= highLength) {
            // 初始化执行长度的Sequential Digits
            int value = 0;
            for (int i = 0; i < length; i++) {
                value = value * 10 + (value % 10 + 1);
            }

            // 遍历特定长度的所有有效值，并添加到结果中
            while (value <= high) {
                if (low <= value) {
                    result.add(value);
                }
                if (value % 10 == 9) {
                    break;
                }

                // TODO. 通过数学计算来更新下一个有效值，注意次方运算
                int divider = (int) Math.pow(10, length - 1);
                value = (value % divider) * 10 + (value % 10 + 1);
            }
            length++;
        }
        return result;
    }
}
