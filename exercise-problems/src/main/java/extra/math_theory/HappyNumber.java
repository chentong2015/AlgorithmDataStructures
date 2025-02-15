package extra.math_theory;

import java.util.HashSet;

public class HappyNumber {

    // Happy Number
    // Starting with any positive integer, replace the number
    // by the sum of the squares of its digits
    //
    // n = 19  -> true
    // 1^2 + 9^2 = 82
    // 8^2 + 2^2 = 68
    // 6^2 + 8^2 = 100
    // 1^2 + 0^2 + 0^2 = 1 结果将永远保持1
    //
    // n = 2 -> false
    // 2^2=4
    // 4^2=16
    // 1+36=37
    // 9+49=58
    // 25+64=89
    // 64+81=145
    // 1+16+25=42
    // 16+4=20
    // 4+0=4 造成死循环的特殊数字4

    // TODO. 判断迭代计算过程中的数字规律，造成计算的Circle
    public boolean isHappy(int n) {
        while (n != 1 && n != 4) {
            n = sumOfSquaresDigits(n);
        }
        return n == 1;
    }

    // 计算过程中Thread Stack也会造成空间复杂度
    private int sumOfSquaresDigits(int n) {
        int sum = 0;
        while (n != 0) {
            // 不断累加每个位置上的digit数字的平方
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }


    // TODO. 使用Set<>来记录计算过程中出现过的数据，判断Circle
    // 造成一定的空间复杂度的开销
    public boolean isHappySet(int n) {
        HashSet<Integer> set = new HashSet<>();
        int sum = sumOfSquaresDigits(n);
        set.add(sum);
        while (sum != 1) {
            sum = sumOfSquaresDigits(sum);
            if (set.contains(sum))  {
                return false;
            }
            set.add(sum);
        }
        return true;
    }
}
