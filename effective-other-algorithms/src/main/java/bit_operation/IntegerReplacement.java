package bit_operation;

// Integer Replacement
// Given a positive integer n, you can apply one of the following operations:
// - If n is even, replace n with n / 2.
// - If n is odd, replace n with either n + 1 or n - 1.
// Return the minimum number of operations needed for n to become 1.
// 1 <= n <= 2^31 - 1
// 1 <= n <= 2147483647
public class IntegerReplacement {

    // TODO. 注意特殊的边界值次方值: 使用long类型存储
    // n = 8
    // 8 -> 4 -> 2 -> 1
    //
    // n = 7
    // 7 -> 8 -> 4 -> 2 -> 1
    // 7 -> 6 -> 3 -> 2 -> 1
    //
    // 2 ^ 16 = 65536
    // 2 ^ 31 = 2147483648
    public int integerReplacement(long n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (n % 2 == 0) {
            // 是偶数但不一定是2的次方数，比如18
            return 1 + integerReplacement(n / 2);
        }
        return 1 + Math.min(integerReplacement( n - 1), integerReplacement(n + 1));
    }

    // TODO. 通过Bit位运算来判断2的次方数
    public int integerReplacementBit(int n) {
        long x = n;
        int count = 0;
        while (x != 1) {
            if ((x & 1) == 0) {
                x >>= 1; // 右移一位取一半的值
            } else {
                // 判断最后两个位置的bit值，判断加减方向
                if (x == 3 || (x & 3) == 1) {
                    x--;  // 0001
                } else {
                    x++;  // 0010 或 0011
                }
            }
            count++;
        }
        return count;
    }
}
