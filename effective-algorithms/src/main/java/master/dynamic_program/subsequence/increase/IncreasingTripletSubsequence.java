package master.dynamic_program.subsequence.increase;

// Increasing Triplet Subsequence
// Given an integer array nums, return true if there exists a triple of indices (i, j, k)
// such that i < j < k and nums[i] < nums[j] < nums[k].
// If no such indices exists, return false.
//
// 1 <= nums.length <= 5 * 10^5
// -2^31 <= nums[i] <= 2^31 - 1
//
// Could you implement a solution: O(n) time complexity, O(1) space complexity ?
public class IncreasingTripletSubsequence {

    // TODO. 循环过程中记录"前面小值"和"中间值", 构成隐形比较关系
    // 该算法属于特殊技巧，无法通过推理得到，只是巧合解
    //
    // [1,2,3,4,5]   -> true
    // [5,4,3,2,1]   -> false
    // [2,1,5,0,4,6] -> 1,4,6 -> true
    //
    // ....i....j....k....
    //
    public boolean increasingTriplet(int[] nums) {
        int iNum = Integer.MAX_VALUE;
        int jNum = Integer.MAX_VALUE;
        for (int kNum : nums) {
            if (kNum <= iNum) {
                iNum = kNum;
            } else if (kNum <= jNum) {
                jNum = kNum;
            } else {
                // iNum < jNum < kNum 关系构成(kNum最大)
                return true;
            }
        }
        return false;
    }
}