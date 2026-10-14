package leetcode6.matrix.ksmallest;

// Kth Smallest Number in Multiplication Table
// Nearly everyone has used the Multiplication Table.
// The multiplication table of size m x n is an integer matrix mat
// where mat[i][j] == i * j (1-indexed).
//
// Given three integers m, n, and k,
// return the kth smallest element in the m x n multiplication table.
//
// 1 <= m, n <= 3 * 10^4
// 1 <= k <= m * n
public class KthSmallestMultiplicationMatrix {

    // TODO. mat[i][j]==i*j 利用Index位置值和坐标条件
    // m = 3, n = 3, k = 5 -> 3
    // 1  2  3   n
    // 2  4  6   2n
    // 3  6  9   3n
    // m  2m 3m
    //
    // m = 2, n = 3, k = 6 -> 6
    // 1 2 3
    // 2 4 6
    //
    // O(log(M*N)*(M+N))
    // O(1)
    public int findKthNumber(int m, int n, int k) {
        int low = 1;
        int high = m*n;
        while (low < high) {
            int midValue = low + (high - low) / 2;
            int count = CountSortedMatrix.countSmallerValuesMN(m, n, midValue);
            if (count >= k) {
                high = midValue;
            } else {
                low = midValue + 1;
            }
        }
        // 跳出条件low=high, 此时low的值 ？
        return low;
    }
}