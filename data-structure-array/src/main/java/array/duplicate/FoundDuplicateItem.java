package array.duplicate;

// Find the Duplicate Number
// Given an array of integers nums containing n + 1 integers
// where each integer is in the range [1, n] inclusive.
// There is only one repeated number in nums, return this repeated number.
//
// You must solve the problem without modifying the array nums and using only constant extra space.
//
// 1 <= n <= 10^5
// nums.length == n + 1
// 1 <= nums[i] <= n
// All the integers in nums appear only once
// except for precisely one integer which appears two or more times.
public class FoundDuplicateItem {

    // TODO. Mark visited element negative 标记坐标: 常规算法
    //
    // [1,3,4,2,2] -> 2
    // [3,1,3,4,2] -> 3
    // [3,3,3,3,3] -> 3
    //
    // 0 1 2 3 4 .... n-1 n
    //   y   y y
    //
    public int findDuplicate(int[] nums) {
        for(int i=0;i<nums.length;i++) {
            int ind = Math.abs(nums[i]);
            if(nums[ind] < 0) {
                return ind;
            }
            nums[ind] = -nums[ind];
        }
        return -1;
    }


    // TODO. Floyd Cycle Finding Algo 快慢指针: 判圈算法
    // 条件限制不能修改数组，不能将值转换成Index坐标
    public int findDuplicate2(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break; // 判断有重复值的存在
            }
        }

        int slow2 = nums[0]; // 再从起点出发追逐slow点
        while (slow2 != slow) {
            slow2 = nums[slow2];
            slow = nums[slow];
        }
        return slow; // 相遇的点就是重复值点
    }
}
