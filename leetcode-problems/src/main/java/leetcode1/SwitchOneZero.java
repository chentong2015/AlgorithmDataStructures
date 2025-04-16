package leetcode1;

// Sort 1's and 0's in array to the end. Either end is fine.
// Find the minimum number of adjacent swaps required to sort.
//
// [1,1,1,0,0,0] -> 0
// [0,0,0,1,1,1] -> 0
// [0,0,1,0,1,0] -> 3
// [0,0,0,0,1,0,1,0,0] -> 5
public class SwitchOneZero {

    public static void main(String[] args) {
        int[] nums = {0,0,1,0,1,0};
        int minStepsZero = countMovesToRight(nums, 0);
        int minStepsOne = countMovesToRight(nums, 1);
        System.out.println(Math.min(minStepsOne, minStepsZero));
    }

    // TODO. 只需要统计移动的步数，并不需要交换数据的位置 > 不要求返回移动好的数组
    //  需要避免移动时将target位置上的值进行覆盖，造成步数统计错误
    public static int countMovesToRight(int[] nums, int target) {
        int count = 0;
        int right = nums.length - 1;
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] == target) {
                while (right > index && nums[right] == target) {
                    right--;
                }
                count += right - index; // 统计往右侧移动的步数
                right--;
            }
            if (right <= index) { // right坐标可能移动到index左边
                break;
            }
        }
        return count;
    }
}
