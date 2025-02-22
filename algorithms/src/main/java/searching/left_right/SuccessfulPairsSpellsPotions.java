package searching.left_right;

import java.util.Arrays;

// Successful Pairs of Spells and Potions
// You are given two positive integer arrays spells and potions, of length n and m respectively,
// where spells[i] represents the strength of the ith spell
// and potions[j] represents the strength of the jth potion.
//
// You are also given an integer success.
// A spell and potion pair is considered successful if the product of their strengths is at least success.
//
// Return an integer array pairs of length n
// where pairs[i] is the number of potions that will form a successful pair with the ith spell.
//
// n == spells.length
// m == potions.length
// 1 <= n, m <= 10^5
// 1 <= spells[i], potions[i] <= 10^5
// 1 <= success <= 10^10
public class SuccessfulPairsSpellsPotions {

    // TODO. 遍历spells时二分查找并统计右侧有多少数据大于目标差值
    //   potions中如果有重复数据，则需要二分查找定位到最左坐标 !!
    // spells = [5,1,3], potions = [1,2,3,4,5], success = 7
    // - 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
    // - 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5].     0 pairs are successful.
    // - 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15].   3 pairs are successful.
    // -> [4,0,3] is returned.
    //
    // spells = [3,1,2], potions = [8,5,8], success = 16
    // - 0th spell: 3 * [8,5,8] = [24,15,24]. 2 pairs are successful.
    // - 1st spell: 1 * [8,5,8] = [8,5,8].    0 pairs are successful.
    // - 2nd spell: 2 * [8,5,8] = [16,10,16]. 2 pairs are successful.
    // -> [2,0,2] is returned.
    //
    // [5,  1,  3], [1,2,3,4,5], success = 7
    //  >=2 >=7 >=3
    //  4   0   3
    //
    // O(M*logM + N*(logM+logM))
    // O(N)
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] result = new int[spells.length];

        // 必须排序后才能使用二分搜索
        Arrays.sort(potions);
        for (int index = 0; index < spells.length; index++) {
            int gapIndex = (int) Math.ceil((double) success / spells[index]);

            // TODO. 可能连续使用两次二分查找
            int findIndex = Arrays.binarySearch(potions, gapIndex);
            if (findIndex < 0) {
                findIndex = -(findIndex + 1);
            } else {
                findIndex = binarySearchLeft(potions, gapIndex);
            }

            // 根据Index坐标统计右侧更大数目
            result[index] = potions.length - findIndex;
        }
        return result;
    }

    // TODO. 在确定能够找到target的前提下找最左侧目标
    private int binarySearchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int idx = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // 继续二分法查找则往左侧移动
                idx = mid;
                right = mid - 1;
            }
        }
        return idx;
    }
}
