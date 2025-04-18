package iteration;

// House Robber
// 找到一组数中能够获得的最大值Sum，不能取相邻的两个值
public class HouseRobber {

    // TODO. 本质上是往后迭代判断结果的问题，无需开辟空间
    // [1,2,3,1] -> 1 + 3 = 4
    // [2,1,1,2] -> 2 + 2 = 4 中间的间隔位置不一定
    // [2,7,9,3,1] -> 2 + 9 + 1 = 12
    //
    // O(N)
    // O(1)
    public int robHouses(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        } else if (nums.length == 3) {
            return Math.max(nums[1], nums[0] + nums[2]);
        }

        // 注意max值的初始化，只有两种值的可能
        nums[2] += nums[0];
        int maxSum = Math.max(nums[1], nums[2]);
        for (int index = 3; index < nums.length; index++) {
            int maxBefore = Math.max(nums[index - 3], nums[index - 2]);
            nums[index] += maxBefore;
            maxSum = Math.max(maxSum, nums[index]);
        }
        return maxSum;
    }
}
