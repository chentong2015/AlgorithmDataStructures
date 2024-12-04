package templates.sorting.merge_sort;

import java.util.List;

public class MergeSortingQuestion {

    // Count of Smaller Numbers After Self
    // Given an integer array nums, return an integer array counts
    // where counts[i] is the number of smaller elements to the right of nums[i].
    //
    // nums = [5,2,6,1] -> [2,1,1,0]
    // nums = [-1,-1] -> [0,0]
    // nums = [-1] -> [0]
    //
    // 统计index右侧比更小值的个数:
    // - 无法使用Stack存储 & 无法先排序全部数据再处理
    // - 利用空间复杂度来降低时间复杂度(原始复杂度O(n*n))
    //
    // 1 2 3 4 5
    //       0 0  O(n) 最差复杂度
    // 5 4 3 2 1
    //       1 0  O(1) 最优复杂度
    public List<Integer> countSmaller(int[] nums) {



        return null;
    }
}
