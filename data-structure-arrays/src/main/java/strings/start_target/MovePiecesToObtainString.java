package strings.start_target;

// Move Pieces to Obtain a String
// You are given two strings start and target, both of length n.
// Each string consists only of the characters 'L', 'R', and '_'
//
// The characters 'L' and 'R' represent pieces,
// a piece 'L' can move to the left only if there is a blank space directly to its left,
// a piece 'R' can move to the right only if there is a blank space directly to its right.
// The character '_' represents a blank space that can be occupied by any of the 'L' or 'R' pieces.
//
// Return true if it is possible to obtain the string target
// by moving the pieces of the string start any number of times. Otherwise, return false.
//
// n == start.length == target.length
// 1 <= n <= 10^5
// start and target consist of the characters 'L', 'R', and '_'.
public class MovePiecesToObtainString {

    // TODO. 双指针比较每个LR字符“坐标”是否符合移动要求
    // "_L__R__R_",  "L______RR" -> true
    // "R_L_",       "__LR" -> false
    // "_R",         "R_" -> false
    // "_L__R__R_L", "L______RR_" -> false
    //
    // O(N + N + N)
    // O(1)
    public boolean canChange(String start, String target) {
        int i = 0;
        int j = 0;
        int m = start.length();
        int n = target.length();
        while (i < m || j < n) {
            // Ignore black space before L or R character
            while (i < m && start.charAt(i) == '_') {
                i++;
            }
            while (j < n && target.charAt(j) == '_') {
                j++;
            }
            // 如果有一个移动结束，则保证都移动结束
            if (i == m || j == n) {
                return i == m && j == n;
            }

            // TODO. 以下三种情况都会导致移动失败 !!
            if (start.charAt(i) != target.charAt(j)
                || (start.charAt(i) == 'L' && i < j)
                || (start.charAt(i) == 'R' && i > j)) {
                return false;
            }
            i++;
            j++;
        }
        return true;
    }
}
