package master.backtracking;

import java.util.ArrayList;
import java.util.List;

// Permutations
// Given an array nums of distinct integers,
// return all the possible permutations. You can return the answer in any order.
//
// 1 <= nums.length <= 6
// -10 <= nums[i] <= 10
// All the integers of nums are unique.
public class AllPermutations {

    // TODO. 全排公式和部分排公式 => 递归返回所有可能结果
    // P(n,n)=n!
    // P(n,r)=n!/(n−r)!
    // 6*5*4*3*2*1 = 720
    //
    // nums = [1,2,3]
    // [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtracking(result, new ArrayList<>(), nums);
        return result;
    }

    // 循环每个位置的值都取一遍, 只添加不存在的值
    private void backtracking(List<List<Integer>> result, List<Integer> temp, int[] nums) {
        if (temp.size() == nums.length) {
            List<Integer> list = new ArrayList<>(temp); // 创建一个新的List
            result.add(list);
            return;
        }

        for (int num : nums) {
            if (!temp.contains(num)) {
                temp.add(num);
                backtracking(result, temp, nums);
                temp.remove(temp.size() - 1); // 撤销最后一个值, 否则temp表会不断添加
            }
        }
    }
}
