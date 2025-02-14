package extra.math;

public class BaseMath1 {

    // 判断质数的算法: 只能被一和自身进行整除
    public boolean isPrimeDigit(int digit) {
        if (digit == 1) {
            return false;
        } else {
            // 使用平方根Math.sqrt(checkNum)优化算法的复杂度
            for (int i = 2; i <= (long) Math.sqrt(digit); i++) {
                if (digit % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // Power of Three
    // Could you solve it without loops/recursion? 不用循环和递归都能实现
    public boolean isPowerOfThree(int n) {
        // 测试理解：判断一个值是否能够被3完全整除 !!
        //         O(log3(n))   O(1)
        if (n < 1) {
            return false;
        }
        while (n % 3 == 0) { // 是3的倍数  ==>  并不代表是3个阶乘 !!
            n /= 3;
        }
        return n == 1; // 最后的基数是1，从1*3的任何次方
        //  return (Math.log10(n) / Math.log10(3)) % 1 == 0; 数学公式计算log3(n)
    }

    // Count Primes
    // 核心算法是，数字从底到高，依次标记所有不是质数的位置  ===> 方法可以重构成多个子方法 !! 只有一个抽象逻辑层
    // O(/n*loglogn) < O(n*n)  O(n)
    public int countPrimes(int n) {
        int count = 0;
        if (n < 2) {
            return count;
        }
        boolean[] notPrimes = new boolean[n]; // 初始默认都是质数 !!
        int lastLimit = (int) java.lang.Math.sqrt(n);
        for (int i = 2; i <= lastLimit; i++) {
            if (!notPrimes[i]) {
                for (int j = 2 * i; j < n; j += i) { // 标记所有这个数的倍数，都不是质数 !!
                    notPrimes[j] = true;
                }
            }
        }
        for (int i = 2; i < n; i++) {
            if (!notPrimes[i]) {
                count++;
            }
        }
        return count;
    }

    // Sum Game
    // Alice (go first) and Bob replaces ? with one number (0-9), until there is no ? in the String
    // There are even character in the String 确定字符串由偶数个字符组成
    // Check sums of the first half and the second half are equal
    // nums = "5023"     -> 5+0=2+3       -> Bob will win
    // nums = "25??"     -> "259?"        -> Alice will win
    // nums = "?329 5???" -> "9329 5927"  -> Bob 后填，始终能够找到一种填法，使得前后的和相等
    // 证明一定是后者赢的逻辑：两侧?的差值数*(-9)=两侧数值总和的两倍 !!
    public boolean sumGame(String str) {
        int count = 0;
        int diffTotal = 0;
        int number;
        boolean isLeft;
        for (int index = 0; index < str.length(); index++) {
            isLeft = index < str.length() / 2;
            if (str.charAt(index) == '?') {
                count += isLeft ? 1 : -1;
            } else {
                number = str.charAt(index) - '0'; // Convert char to integer
                diffTotal += isLeft ? number : -number;
            }
        }
        if (count * -9 == diffTotal * 2) return false;
        return true;
    }
}
