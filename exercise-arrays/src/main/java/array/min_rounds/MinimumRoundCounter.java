package array.min_rounds;

import java.util.HashMap;
import java.util.Map;

// TODO. 不考虑task的有序性，支持乱序task的输入
// O(n+n) O(n)
public class MinimumRoundCounter {

    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> mapTasks = new HashMap<>();
        for (int task: tasks) {
            if (mapTasks.containsKey(task)) {
                mapTasks.put(task, mapTasks.get(task) + 1);
            } else {
                mapTasks.put(task, 1);
            }
        }
        int minTotalRounds = 0;
        for (int number: mapTasks.values()) {
            int minRounds = countMinRoundsForSameLevel(number);
            if (minRounds == -1) {
                return -1;
            }
            minTotalRounds += minRounds;
        }
        return minTotalRounds;
    }

    // TODO. 基于余数的进一步判断，需要优化判断的逻辑
    private int countMinRoundsForSameLevel(int number) {
        if (number < 2) {
            return -1;
        } else if (number == 2) {
            return 1;
        } else {
            int roundsByThree = number / 3;
            int remainder = number % 3;
            if (remainder == 0) {
                return roundsByThree; // min rounds to complete all teaks by 3 each time
            } else if (remainder == 1) {
                return roundsByThree + 1; // (3,,,3,1) -> (3,,,2,2) min rounds to finish
            } else {
                return roundsByThree + 1; // (3,,,3,2) -> (3,,,3,2) keep same
            }
        }
    }
}
