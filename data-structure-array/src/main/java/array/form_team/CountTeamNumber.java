package array.form_team;

// Count Number of Teams
// You have to form a team of 3 soldiers amongst them under the following rules:
// - Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
// - (rating[i] < rating[j] < rating[k])
// - (rating[i] > rating[j] > rating[k])
//
// Return the number of teams you can form given the conditions.
// (soldiers can be part of multiple teams).
//
// n == rating.length
// 3 <= n <= 1000
// 1 <= rating[i] <= 10^5
// All the integers in rating are unique.
public class CountTeamNumber {

    // TODO. 经典降维思想: 将三维降低成二维, 从中间往两边扩散
    // rating = [2,1,3] -> 0
    // rating = [2,5,3,4,1] -> (2,3,4), (5,4,1), (5,3,1) -> 3
    // rating = [1,2,3,4] -> 123, 234, 124, 134 -> 4
    //
    // 定位中间位置index往两边扩展，避免从一个方向寻找造成O(n*n)的复杂度
    // O(n^2) 每一次循环，内层的循环总次数为o(n)
    // O(1)
    public int numTeams(int[] arr) {
        int count = 0;
        for (int index = 0; index < arr.length; index++) {
            int leftSmaller = 0;
            int leftLarger = 0;
            for (int left = 0; left < index; left++) {
                if (arr[left] < arr[index]) {
                    leftSmaller++;
                } else {
                    leftLarger++;
                }
            }

            int rightSmaller = 0;
            int rightLarger = 0;
            for (int right = index + 1; right < arr.length; right++) {
                if (arr[index] < arr[right]) {
                    rightLarger++;
                } else {
                    rightSmaller++;
                }
            }

            // TODO. 排列组合计算两种结果可能
            count += leftSmaller * rightLarger;
            count += leftLarger * rightSmaller;
        }
        return count;
    }
}
