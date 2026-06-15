package array;

// Given an integer array nums,
// return the third distinct maximum number in this array.
// If the third maximum does not exist, return the maximum number.
//
// 1 <= nums.length <= 10^4
// -2^31 <= nums[i] <= 2^31 - 1
public class ThirdMaxNumber {

    // TODO. 不考虑数值相等情况(处理离散数据), 无需开辟内存空间
    // nums = [3,2,1] -> 1
    // nums = [1,2] -> 2
    // nums = [2,2,3,1] -> 1
    public int thirdMax(int[] nums) {
        int firstMax = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        int thirdMax = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num > firstMax) {
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = num;
            } else if (num > secondMax && num < firstMax) {
                thirdMax = secondMax;
                secondMax = num;
            } else if (num > thirdMax && num < secondMax){
                thirdMax = num;
            }
            // 忽略三个最值的相等值, 不做数值变动
        }

        // 判读第三个数据是否被赋值过
        if(thirdMax == Integer.MIN_VALUE){
            return firstMax;
        } else {
            return thirdMax;
        }
    }
}
