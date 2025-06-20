import java.util.*;

public class LearnArray4 {

    // TODO: 左边乘积 * 右边乘积 = index位置的其余乘积 (0...i-1 index i+1...length-1)
    //   遍历两遍 + 从两端读 = 复杂的题目都是由简单的题目构成的
    // Product of Array Except Self
    // Given an integer array nums, return an array answer
    // answer[i] is equal to the product of all the elements of nums except nums[i]
    // The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer
    // nums = [1,2,3,4]     -> [24,12,8,6]  数组长度说明，int类型的值范围，必须保证剩余的值的乘积在int的最值范围之内 !!
    // nums = [-1,1,0,-3,3] -> [0,0,9,0,0]  数组有没有可能为null，不能使用除法，不能通过总值来除以当前的元素值
    // 最优的复杂度: O(n) O(1) 空间不考虑结果数组的影响
    //
    // 1   2  3   4  -> nums
    // 1   2  6  24  -> res 用一个数组保留左侧累计乘积的历史数据
    // *   *  8   6
    // *   *  12  4  -> rightProduct 用一个变量累计右侧累计的乘积值
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] res = new int[length];
        res[0] = 1;
        for (int i = 1; i < length; i++) {
            res[i] = res[i - 1] * nums[i - 1]; // 累积index前面的位置的乘积值
        }

        int rightProduct = 1;
        for (int i = length - 1; i >= 0; i--) {
            res[i] = res[i] * rightProduct;   // "左侧"的累积值保留在res[i]求值的位置
            rightProduct = nums[i] * rightProduct;  // "右侧"的累积值保留在rightProduct中
        }
        return res;
    }

    // Container With Most Water
    // N non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai)
    // [1,8,6,2,5,4,8,3,7] -> 7*7=49 -> 存水量=Min(x1, x2)*gabs
    public int maxArea(int[] height) {
        // 测试理解：1. 找中间能存储的最大空间的水量，确定container左右两个边缘的位置和值
        //             O(n*n) 这里的时间复杂度过高，不可行 O(1)

        // 正确思考: 1. 由左右向中间移动，那边高度更低则移动那边，因为储水量由最低的那个高度决定，如果移动更高的高度+距离减小，会导致后面的结果不是最大值 !
        //          2. 当左右两边相等高度时，如何判断移动? 是否有Test Case无法通过? [1,2,100,1,1,10,2]
        int max = 0;
        int count = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {        // 这里的查找可以
                count = Math.min(height[i], height[j]) * (j - i);
                max = Math.max(max, count);
            }
        }
        return max;
    }

    // Longest Consecutive Sequence
    // Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
    // nums = [100,4,200,1,3,2] -> 1,2,3,4 -> length=4
    public int longestConsecutive(int[] nums) {
        // 测试理解：1. 提取数字的连续片段(不包含重复的数字)的最长长度，直接使用排序O(nlog(n)) O(1)
        Arrays.sort(nums);
        int longestStreak = 1;
        int currentStreak = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                if (nums[i - 1] + 1 == nums[i]) {
                    currentStreak += 1;  // 依次统计到该index位置的时候，前面累加的最长的连续长度 !!
                } else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }
        return Math.max(longestStreak, currentStreak);
    }

    // 正确理解：1. 使用HashMap<key, key+1>来存储value值和它指向下一个value值(键值), 通过判断key是否存在来进行连续的查找 !!
    //          2. 使用HashSet<key>只需要存储value值, 因为它的下一个连续的值一定是value+1, 不需要额外的空间来存储 !!
    //             O(n+n)=O(n)  O(n)
    public int longestConsecutive2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, num + 1);
        }
        int max = 0;
        for (int key : map.keySet()) {                 // O(n)
            if (!map.containsKey(key - 1)) {           // 注意：只需要从每一个连续片段的开头开始计算，最多会再遍历一遍序列所有值 + O(n)
                int count = 1;
                int searchValue = map.get(key);        // searchValue可以直接使用key，然后循环searchValue++
                while (map.containsKey(searchValue)) { // 嵌套的while有可能会造成O(n*n)的复杂度 !!
                    searchValue = map.get(searchValue);
                    count++;
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }

    // Largest Number
    // Given a list of non-negative integers nums, arrange them such that they form the largest number
    // nums = [3,30,34,5,9,200]  -> "9534330200"
    public String largestNumber(int[] nums) {
        // 正确理解:  1. 本质上是需要做一个排序，换成字符串进行大小比较 !! 不需要比较"整数分位"上的值
        //  9 > 5 > 34 > 3 > 30 > 200
        //  O(nlog(n)) 由于数组排序带来的时间复杂度 O(n)
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strings, new LargerNumberComparator());
        if (strings[0].equals("0")) {
            return "0";
        }
        StringBuilder largestNumberStr = new StringBuilder();
        for (String numAsStr : strings) {
            largestNumberStr.append(numAsStr);
        }
        return largestNumberStr.toString();
    }

    // 自定义比较器，用于在Array数组排序时的元素之间比较
    class LargerNumberComparator implements Comparator<String> {

        // 直接比较两种字符串组合起来的大小，取更大的组合情况
        @Override
        public int compare(String str1, String str2) {
            String order1 = str1 + str2;
            String order2 = str2 + str1;
            return order2.compareTo(order1);
        }
    }
}
