package searching;

public class BinarySumProblem {

    // TODO. 二分位Index两侧可以直接求和，通过sum来判断二分法的移动方向
    // Given an integer N
    // return the integer number K in a way that
    // the sum that goes from 1 to K-1 is equals to the sum that goes form k+1 to N (all inclusive),
    // if there is no number that met the condition return -1.
    //
    // N = 8 -> k = 6
    // Explanation: [1,2,3,4,5,6,7,8]
    // LEFT = SUM(1,K-1) = 1 + 2 + 3 + 4 + 5 = 15
    // RIGHT = SUM(K+1,N) = 7 + 8 = 15
    //
    // O(log(n)) O(1)
    public static int findKValue(int n) {
        int left = 1;
        int right = n;
        int total = (n * (n + 1)) / 2;
        while (left < right) {
            int mid = left + (right - left)/2;
            int sumLeft = (mid * (mid - 1))/2; // 等差数列请和公式 N*(N+1)/2
            int sumRight = total - mid - sumLeft;

            if (sumLeft == sumRight) {
                return mid;
            } else if (sumLeft < sumRight) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return -1;
    }
}
