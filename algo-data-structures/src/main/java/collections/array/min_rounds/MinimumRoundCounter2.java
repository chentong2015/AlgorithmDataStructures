package collections.array.min_rounds;

import java.util.Arrays;

// TODO. 考虑tasks任务的有序性，保证相同level的任务在一起
// O(n*logn)+O(n) 时间复杂度来换取空间复杂度
// O(1)
public class MinimumRoundCounter2 {

    public int minimumRounds(int[] tasks) {
        if(tasks.length == 1) {
            return -1;
        }
        Arrays.sort(tasks);
        int minTotalRounds = 0;
        int left = 0;
        for(int index = 1; index < tasks.length; index++){
            // 在遍历过程中统计相同Level的数量, 否则需要在循环结束再判断
            int countSameLevel = -1;
            if (index == tasks.length - 1) {
                if(tasks[left] != tasks[index]) {
                    return -1;
                }
                countSameLevel = index - left + 1;
            } else {
                if (tasks[left] != tasks[index]) {
                    countSameLevel = index - left;
                }
            }

            if(countSameLevel > 0){
                int minRounds = getRoundsForSameLevel(countSameLevel);
                if (minRounds == -1) {
                    return -1;
                }
                minTotalRounds += minRounds;
                left = index; // 移动左边坐标到新值位置
            }
        }
        return minTotalRounds;
    }

    private int getRoundsForSameLevel(int countSameLevel) {
        if (countSameLevel == 1) {
            return -1;
        }
        if (countSameLevel % 3 == 0) {
            return countSameLevel / 3;
        }
        return countSameLevel / 3 + 1; // for remainder 1 or 2
    }
}
