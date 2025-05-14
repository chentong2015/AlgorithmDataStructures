package two_pointers;

// Sum of Square Numbers
// Given a non-negative integer c,
// decide whether there are two integers a and b such that a^2 + b^2 = c
//
// 0 <= c <= 2^31 - 1
public class SumSquareNumbers {

    // TODO. 使用双指针在[0, sqrt(N)]区间查找
    // 5 = 1 * 1 + 2 * 2 -> true
    // 4 = 0 * 0 + 2 * 2 -> true
    // 1 = 0 * 0 + 1 * 1 -> true
    // 3 -> false
    //
    // O(sqrt(N))
    // O(1)
    public boolean judgeSquareSum(int c) {
        int a = 0;
        long b = (int) Math.sqrt(c);
        long sum = a * a + b * b;
        while (a <= b) {
            if (sum == c) {
                return true;
            }
            if (sum < c) {
                a++;
            } else {
                b--;
            }
            sum = a * a + b * b;
        }
        return false;
    }
}
