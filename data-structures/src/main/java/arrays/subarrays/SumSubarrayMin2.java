package arrays.subarrays;

public class SumSubarrayMin2 {

    // TODO. 优化算法: 一次性将Monotonic Stack单调栈中数据读取出来
    // Input: 3   1   2    4
    // Stack: -1  -1  1    2    统计前面更小值的Index坐标位置
    // Sum[]: 3   2   2+2  4+4  使用这个数组来做累加效果
    //
    // O(n+n+n) 最佳的时间复杂度
    // O(n)     对等的空间复杂度, 栈数组中存储的坐标
    public static int sumSubarrayMinsMaster(int[] nums) {
        int[] smallIndexBefore = new int[nums.length];

        // 第一个元素前面没有更小值的坐标
        smallIndexBefore[0] = -1;

        for (int i = 1; i < nums.length; i++) {
            int before = i - 1;
            while (before >= 0 && nums[before] >= nums[i]) {
                before = smallIndexBefore[before];
            }
            smallIndexBefore[i] = before;
        }

        int[] sum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // TODO. 考虑Gap间隔，统计当前nums[i]能够被当作几次最小值
            int gap = i - smallIndexBefore[i];
            sum[i] = nums[i] * gap;

            // TODO. 累加前面更小值的Index位置的统计Sum, 避免往前循环
            if (smallIndexBefore[i] >= 0) {
                sum[i] += sum[smallIndexBefore[i]];
            }
        }

        long result = 0;
        for (int i = 0; i < nums.length; i++) {
            result += sum[i];
        }
        return (int) (result % 1_000_000_007);
    }
}
