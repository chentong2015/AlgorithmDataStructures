package leetcode4;

import java.util.HashMap;
import java.util.Map;

// Count Good Meals
// A good meal is a meal that contains exactly two different food items
// with a sum of deliciousness equal to a power of two.
// You can pick any two different foods to make a good meal.
//
// Given an array of integers deliciousness
// where deliciousness[i] is the deliciousness of the i item of food,
// return the number of different good meals you can make from this list modulo 10^9 + 7.
//
// Note that items with different indices are considered different
// even if they have the same deliciousness value.
//
// 1 <= deliciousness.length <= 10^5
// 0 <= deliciousness[i] <= 2^20
public class CountGoodMeals {

    // TODO. 注意2倍数的取值范围: 2^0 - 2^21 总共22种可能值
    // [1,3,5,7,9] -> 4
    // (1,3), (1,7), (3,5), (7,9)
    //
    // [1,1,1,3,3,3,7] -> 15
    // (1,1) * 3 + (1,3) * 9 + (1,7) * 3
    //
    // [0, 1, 1, 12, 18, 20] -> 4
    //
    // [1048576, 1048576] -> 1
    //
    // O(21*N) 常量倍数的时间复杂度
    // O(N)    统计所有数据的频率
    public int countPairs(int[] deliciousness) {
        long result = 0;
        Map<Integer, Integer> map = new HashMap<>();

        // TODO. 在遍历每个新数时，循环特定次数的倍数
        for(int num: deliciousness){
            int power = 1; // 2的零次方为1
            for(int i=0; i<22; i++){
                if(map.containsKey(power-num)) {
                    result += map.get(power-num);
                    result %= 1000000007; // 直接对溢出值求余数
                }
                power *= 2;
            }

            int baseCount = map.getOrDefault(num, 0);
            map.put(num, baseCount + 1);
        }
        return (int) result;
    }
}
