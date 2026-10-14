package leetcode6.matrix.ksmallest;

// Kth Smallest Element in a Sorted Matrix
// Given an n x n matrix where each of the rows and columns is sorted in ascending order,
// return the kth smallest element in the matrix.
//
// All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
// It is the kth smallest element in the sorted order, not the kth distinct element.
//
// 1 <= n <= 300
// 1 <= k <= n^2
public class KthSmallestSortedMatrix {

    // TODO. 在Min和Max最值之间进行二分查找
    // [1,,,,,,K,,,,,N,,,N*N] 第k个最小，则前面有k-1个更小值
    //    low       high
    //
    // [1,2], k=2 -> 1
    // [1,3]
    //
    // [1, 5, 9], k = 8 -> 13
    // [10,11,13],
    // [12,13,15]
    //
    // O(log(Max-Min)*(N+N))
    // O(1)
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0];
        int high = matrix[n-1][n-1];
        while (low < high) {
            int midValue = low + (high - low) / 2;
            int count = CountSortedMatrix.countSmallerValues(matrix, midValue);
            if (count >= k) {
                high = midValue;
            } else {
                low = midValue + 1;
            }
        }
        // 跳出条件low=high, 此时low前面刚好有(k-1)个更小值
        return low;
    }
}
