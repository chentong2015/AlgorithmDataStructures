package amazon;

// The kth Factor of n
// You are given two positive integers n and k.
// A factor of an integer n is defined as an integer i where n % i == 0.
// Consider a list of all factors of n sorted in ascending order,
// return the kth factor in this list or return -1 if n has less than k factors.
//
// 1 <= k <= n <= 1000
// Could you solve this problem in less than O(n) complexity?
//
// 快速判断一个数的所有整除因子
// n = 12, k = 3
// [1, 2, 3, 4, 6, 12], the 3rd factor is 3
//
// n = 7, k = 2
// [1, 7], the 2nd factor is 7.
//
// n = 4, k = 4
// [1, 2, 4], -1
public class KthFactorN {

    // TODO. 数学逻辑: 在遍历一半数据的过程中也同时判断质数
    //
    // n = 46, k = 4
    // 1 2 23 46
    public int kthFactor(int n, int k) {
        int count = 0;
        int index = 1;
        while (index <= n / 2) {
            if (n % index == 0) {
                count++;
                if (count == k) {
                    return index;
                }
            }
            index++;
        }
        // 只相差最后一个数本身
        if (count + 1 == k) {
            return n;
        }

        // 跳出while循环说明是质数
        if (k == 1) {
            return 1;
        } else if (k == 2) {
            return n;
        } else {
            return -1;
        }
    }
}
