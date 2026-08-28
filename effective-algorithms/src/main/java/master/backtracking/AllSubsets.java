package master.backtracking;

import java.util.ArrayList;
import java.util.List;

// Subsets
// Given an integer array nums of unique elements,
// return all possible subsets (the power set)
// The solution set must not contain duplicate subsets
public class AllSubsets {

    // TODO. 回溯算法模板：记录一组数据排列出来的所有可能
    // nums = [1,2,3]
    // [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
    //
    // 算法的复杂度和数学公式有关(组合形式)
    // n + (n-1) + Mathematical formula

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        getSubset(ans, nums,0, temp);
        // Collections.sort(ans);
        return ans;
    }

    // Index位置的值可取可不取
    public void getSubset(List<List<Integer>> ans, int[] nums, int index, List<Integer> temp){
        if(index == nums.length){
            ans.add(new ArrayList<>(temp));
            return;
        }

        temp.add(nums[index]);
        getSubset(ans, nums,index + 1, temp);

        temp.remove(temp.size() - 1);
        getSubset(ans, nums,index + 1, temp);
    }
}
