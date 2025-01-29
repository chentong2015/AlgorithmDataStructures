package leetcode.count_paris_form;

import java.util.HashMap;
import java.util.Map;

// Count Pairs That Form a Complete Day II
// Given an integer array hours representing times in hours,
// return an integer denoting the number of pairs i, j
// where i < j and hours[i] + hours[j] forms a complete day.
//
// 1 <= hours.length <= 5 * 10^5
// 1 <= hours[i] <= 10^9
public class CountPairsFormCompleteDayII {

    // TODO. 进阶算法: 只考虑24小时余数的统计数量，通过余数两两组合
    //
    // O(n)  只遍历一遍全部数据
    // 0(23) 只统计23个小时偏移量
    public static long countCompleteDayPairs(int[] hours) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int countOne = 0;
        for (int hour: hours) {
            int key = hour % 24;
            if (key == 0) {
                countOne++;
            } else {
                int count = countMap.getOrDefault(key, 0);
                countMap.put(key, count + 1);
            }
        }

        // 直接统计1的结果组合 + 剩余24小时偏移量的组合
        long countResult = 0;
        if (countOne > 1) {
            countResult += (long) countOne * (countOne - 1) / 2;
        }

        // TODO. 只遍历一半, 且offset=12属于特殊偏移量
        for (int offset = 1; offset <= 12; offset++) {
            if (countMap.containsKey(offset) && countMap.containsKey(24 - offset)) {
                if (offset == 12) {
                    int count = countMap.get(12);
                    countResult += (long) count * (count - 1) / 2;
                } else {
                    countResult += (long) countMap.get(offset) * countMap.get(24 - offset);
                }
            }
        }
        return countResult;
    }
}
