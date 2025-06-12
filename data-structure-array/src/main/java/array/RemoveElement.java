package array;

// Remove Element
// Given an array nums and a value val, remove all instances of that value in-place
// and return the new length
// The order of elements can be changed.
// It doesn't matter what you leave beyond the new length
//
// 0 <= nums.length <= 100
// 0 <= nums[i] <= 50
// 0 <= val <= 100
public class RemoveElement {

    // 直接将非特定的数据移动到开头
    // nums = [3,2,2,3], val = 3
    // -> 2, nums = [2,2,_,_]
    //
    // nums = [0,1,2,2,3,0,4,2], val = 2
    // -> 5, nums = [0,1,4,0,3,_,_,_]
    //
    public int removeElement(int[] nums, int val) {
        int left = 0;
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] != val) {
                nums[left++] = nums[index];
            }
        }
        return left;
    }
}
