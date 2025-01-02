package questions.zero_one_ways;

public class WaysSelectZeroOne {

    public static void main(String[] args) {
        System.out.println(numberOfWays("001101"));
        // System.out.println(numberOfWays("11100"));
        // System.out.println(numberOfWays("101"));
        // System.out.println(numberOfWays("0001100100"));
    }

    // TODO. 算法核心: 压缩数据，压缩数据进行统计
    // - 如果当前位置为"0": 只关心前面有多少"01" -> "0"可以来自前面任何位置
    // - 如果当前位置为"1": 只关心前面有多少"10" -> 前面的"1"全部可以缩合
    public static long numberOfWays(String s) {
        int zero = 0; // Individual zeroes count
        long zeroOne = 0; // Number of combinations of 01s
        int one = 0; // Individual ones count
        long oneZero = 0; // Number of combinations of 10s

        long numWays = 0;
        for(char ch : s.toCharArray()) {
            if(ch == '0') {
                zero++;
                oneZero += one; // Before 1 + Current 0 = Count 10
                numWays += zeroOne; // Before 01 + Current 0 = Count 010
            } else {
                one++;
                zeroOne += zero; // Before 0 + Current 1 = Count 01
                numWays += oneZero; // Before 10 + Current 1 = Count 101
            }
        }
        return numWays;
    }
}
