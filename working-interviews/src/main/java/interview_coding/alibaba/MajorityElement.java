package interview_coding.alibaba;

// Majority Element
// Given an array nums of size n, return the majority element.
//
// The majority element is the element that appears more than [n / 2] times.
// You may assume that the majority element always exists in the array.
//
// n == nums.length
// 1 <= n <= 5 * 10^4
// -10^9 <= nums[i] <= 10^9
public class MajorityElement {

    // TODO. 数字出现频率抵消思想
    // nums = [3,2,3] -> 3
    // nums = [2,2,1,1,1,2,2] -> 2
    //
    // O(N)
    // O(1)
    public int majorityElement(int[] nums) {
        if (nums.length < 3) {
            return nums[0]; // 三个以下的值必须相同
        }

        int tempNum = nums[0];
        int count = 1;
        for (int index = 1; index < nums.length; index++) {
            if (nums[index] == tempNum) {
                count++;
                if (count > nums.length / 2) {
                    return tempNum;
                }
            } else {
                if (count == 1) {
                    tempNum = nums[index]; // 更换数字, 重新统计
                    count = 1;
                } else {
                    count--; // 抵消之前的统计
                }
            }
        }
        return tempNum;
    }
}
