package arrays.array.min_rounds;

import java.util.Arrays;

// TODO. 考虑tasks任务的有序性，保证相同level的任务在一起
// O(n*logn)+O(n) 时间复杂度来换取空间复杂度
// O(1)
public class MinimumRoundCounterMaster {

    // 使用For+While在循环的过程中统计Same Level
    public int minimumRounds(int[] tasks) {
        Arrays.sort(tasks);
        int minTotalRounds = 0;
        for (int index = 0; index < tasks.length; index++) {
            int countSameLevel = 1;
            int right = index + 1;
            while (right < tasks.length && tasks[right] == tasks[index]) {
                countSameLevel++;
                right++;
            }

            // Update index to next position 然后index会被加1然后移动到right为止
            index = right - 1;

            if (countSameLevel == 1) {
                return -1;
            }
            if (countSameLevel % 3 != 0) {
                minTotalRounds++;
            }
            minTotalRounds += countSameLevel / 3;
        }
        return minTotalRounds;
    }
}
