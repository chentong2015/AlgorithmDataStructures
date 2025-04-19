package iteration;

// Jump Game
// You are given an integer array nums.
// You are initially positioned at the array's first index,
// and each element in the array represents your maximum jump length at that position.
//
// Return true if you can reach the last index, or false otherwise.
// 1 <= nums.length <= 104
// 0 <= nums[i] <= 105
// [2,3,1,1,4]
public class JumpGame {

    // TODO. Jump问题的本质: 迭代判断剩余的Steps是否足够
    // nums = [2,3,1,1,4] -> true
    // nums = [3,2,1,0,4] -> false
    //
    // O(N)
    // O(1)
    public boolean canJump(int[] nums) {
        int remainingSteps = 0;
        for (int num: nums) {
            if (remainingSteps < 0) {
                return false;
            }

            // 更新剩余的Steps步数统计
            if (remainingSteps < num) {
                remainingSteps = num;
            }
            // 为了跳到下一步，则必须消耗一步
            remainingSteps -= 1;
        }
        return true;
    }

    // 从后往前思考，记录最低必须从什么位置起跳
    public boolean canJumpGreedy(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            // 更新有效位置(可以到达的位置)
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
}
