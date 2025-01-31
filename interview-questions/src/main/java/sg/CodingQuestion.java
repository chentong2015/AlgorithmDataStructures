package sg;

public class CodingQuestion {

    // TODO. 累计数组前面的值: 直接使用原数组存储累计的结果
    // input: [0, 1, 2, 3, 4]
    // output: [0, 1, 3, 6, 10]
    public void calculateSumBefore(int[] nums) {
        for (int index = 1; index < nums.length; index++) {
            nums[index] += nums[index - 1];
        }
    }

    /**
     * 判断两个输入的整数值的和是否等于提供的结果，如果错误则返回计算错误的数值位置
     *
     * @param valOne     第一个加数
     * @param valTwo     第二个加数
     * @param result 用于判断的和数
     * @return 返回ok字符串或者返回index位置字符串
     */
    public static String compute(int valOne, int valTwo, int result) {
        int indexFault = 0;
        int additionValue = 0;
        while (valOne >= 10) {
            // 从最低位开始比较，需要累加上之前"低位的进位值"
            int sum = valOne % 10 + valTwo % 10 + additionValue;
            if (sum >= 10) {
                additionValue = 1;
                sum = sum % 10;
            } else {
                additionValue = 0;
            }

            // 比较每一轮计算出来的末尾数是否正确
            if (sum != result % 10) {
                return String.valueOf(indexFault);
            }

            // 往高位移动一位进行判断
            valOne = valOne / 10;
            valTwo = valTwo / 10;
            result = result / 10;
            indexFault++;
        }

        if (valOne + valTwo != result) {
            return String.valueOf(indexFault);
        }
        return "ok";
    }
}
