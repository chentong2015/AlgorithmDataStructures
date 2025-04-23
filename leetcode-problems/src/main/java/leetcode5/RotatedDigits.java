package leetcode5;

// Rotated Digits
// An integer x is a good if after rotating each digit individually by 180 degrees,
// we get a valid number that is different from x.
// Each digit must be rotated - we cannot choose to leave it alone.
//
// A number is valid if each digit remains a digit after rotation
// - 0, 1, and 8 rotate to themselves,
// - 2 and 5 rotate to each other
// - 6 and 9 rotate to each other
// - the rest of the numbers do not rotate to any other number and become invalid.
//
// Given an integer n, return the number of good integers in the range [1, n]
// 1 <= n <= 10^4
//
public class RotatedDigits {

    // TODO. 无效实现O(1)复杂度，只能做O(N)
    // n = 10 -> 4
    // good numbers in [1, 10] : 2, 5, 6, 9.
    //
    // O(N)
    // O(1)
    public int rotatedDigits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isGood(i)) {
                count++;
            }
        }
        return count;
    }

    // TODO. 旋转规则的特点
    // - 如果存在 3 4 7 则旋转无效
    // - 对于数字 0 1 8 旋转后有效且相等
    // - 对于数字 2 5 6 9 旋转后有效且不等
    private boolean isGood(int num) {
        boolean hasDifferent = false;
        while (num > 0) {
            int digit = num % 10;
            if (digit == 3 || digit == 4 || digit == 7) {
                return false;
            }
            if (digit == 2 || digit == 5 || digit == 6 || digit == 9) {
                hasDifferent = true;
            }
            num /= 10;
        }
        return hasDifferent;
    }
}
