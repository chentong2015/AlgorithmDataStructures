package prefix_sum;

import java.util.HashMap;
import java.util.List;


public class CountInterestingSubarrays {

    public static void main(String[] args) {
        List<Integer> list = List.of(3,1,9,6);
        CountInterestingSubarrays instance = new CountInterestingSubarrays();
        System.out.println(instance.countInterestingSubarrays(list, 3, 0));
    }

    // TODO. Prefix Sum 前缀和变式计算
    // nums = [3,2,4], modulo = 2, k = 1 -> 3
    // [3]     -> 3%2=1, 1%2 == 1 -> cnt=1
    // [3,2]   -> 3%2=1, 1%2 == 1 -> cnt=1
    // [3,2,4] -> 3%2=1, 1%2 == 1 -> cnt=1
    //
    // nums = [3,1,9,6], modulo = 3, k = 0 -> 2
    // [3,1,9,6] -> nums[i]%3 = 0, 3%3 = 0 -> cnt = 3
    // [1]       -> 0%3 = 0, 0%3 = 0 -> cnt = 0
    //
    // 3  2  4  3  / 1, 3, 5, 7,,,, cnt
    // 1  1  1  2  = prefix sum
    //
    // Time O(n)
    // Space O(mod) 只需要存储module大小的统计
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        // Means the number of prefix array that have acc % mod == k.
        // initial count[0] = 1 for empty prefix subarray.
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        // Means the number of nums[i] % mod == k in i + 1 first elements.
        int acc = 0;
        long result = 0;
        for (int num : nums) {
            int addCnt = num % modulo == k ? 1 : 0;
            acc = (acc + addCnt) % modulo;

            // TODO. 注意非0余数Diff的计算
            int diff = (acc - k + modulo) % modulo;

            // TODO. 统计有多少个diff差值，也将能组成多少个和为k值的子数组
            result += map.getOrDefault(diff, 0);

            // Hashmap统计结果: diff=0时统计一次，diff=1时统计一次
            // "0" -> 2
            // "1" -> 1 用来计算差值子数组的统计
            // "2" -> 1
            int baseCount = map.getOrDefault(acc, 0);
            map.put(acc, baseCount + 1);
        }
        return result;
    }
}
