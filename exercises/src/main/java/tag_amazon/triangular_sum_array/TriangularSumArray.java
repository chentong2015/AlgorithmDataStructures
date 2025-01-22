package tag_amazon.triangular_sum_array;

public class TriangularSumArray {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        System.out.println(triangularSum(nums));
    }

    // TODO. 本质上需要考虑: 中间计算过程的推倒值是否需要存在?
    // Find Triangular Sum of an Array
    // 1  3   4  2
    //   4   7  6
    //     1   3
    //       4
    //
    // O(n-1 + n-2 + ... 1) 时间复杂度主要体现在计算中间值
    // O(1)  直接利用原始的数组存储中间结果
    public static int triangularSum(int[] nums) {
        int times = nums.length - 1;
        while (times > 0) {
            for (int index=0; index < times; index++) {
                nums[index] = (nums[index] + nums[index+1]) % 10;
            }
            times--;
        }
        return nums[0];
    }
}
