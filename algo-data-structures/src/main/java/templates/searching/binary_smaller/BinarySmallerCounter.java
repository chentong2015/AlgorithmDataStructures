package templates.searching.binary_smaller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO. 有关数据查找和统计的算法
public class BinarySmallerCounter {

    // Count of Smaller Numbers After Self
    // Given an integer array nums, return an integer array counts
    // where counts[i] is the number of smaller elements to the right of nums[i].
    //
    // nums = [5,2,6,1] -> [2,1,1,0]
    // nums = [-1,-1] -> [0,0]
    // nums = [-1] -> [0]

    public static void main(String[] args) {
        int[] nums = {5,2,6,1};
        List<Integer> resultList = countSmaller(nums);
        for (int result: resultList) {
            System.out.println(result);
        }
    }

    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> resultList = new ArrayList<>();

        return resultList;
    }
}
