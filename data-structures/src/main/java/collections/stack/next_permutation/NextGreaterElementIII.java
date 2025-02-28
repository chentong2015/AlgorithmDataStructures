package collections.stack.next_permutation;

// Next Greater Element III
// Given a positive integer n, find the smallest integer
// which has exactly the same digits existing in the integer n and is greater in value than n.
// If no such positive integer exists, return -1.
//
// Note that the returned integer should fit in 32-bit integer,
// if there is a valid answer but it does not fit in 32-bit integer, return -1.
//
// 1 <= n <= 2^31 - 1
public class NextGreaterElementIII {

    // TODO. 注意不是找后面"Next Greater"下一个比它大的数据
    //  1. 从后往前找到某个数后面最小的比它大的数，交换
    //  2. 将该数后面的数从小到大排序，保证最小
    // 12 ->  21
    // 101 -> 110
    // 123 -> 132
    // 1324 -> 1342
    // 21 ->  -1
    // 54321 -> -1
    // 53654 -> 54653 > 54356
    // 53621 -> 56321 > 56123
    // 230241 ->  230421 > 230412
    //
    public static int nextGreaterElement(int n) {

        return 0;
    }
}
