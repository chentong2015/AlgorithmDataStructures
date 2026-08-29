package leetcode2;

import java.util.Collections;
import java.util.List;

// Minimum Processing Time
//
// 每个处理器可以接受四个并发的执行的Task任务
// You have a certain number of processors, each having 4 cores.
// The number of tasks to be executed is four times the number of processors.
// 每个Core核心只能执行一个Task任务
// Each task must be assigned to a unique core, and each core can only be used once.
//
// You are given an array processorTime representing the time each processor becomes available
// and an array tasks representing how long each task takes to complete
//
// Return the minimum time needed to complete all tasks.
// tasks.length == 4 * n
public class MinProcessingTime {

    // TODO. 算法核心: 耗时长的task应该分配给等待时间短的处理器
    // processorTime = [8,10], tasks = [2,2,3,1,8,7,4,5] -> 16
    // max(8 + 8, 8 + 7, 8 + 4, 8 + 5) = 16.
    // max(10 +2, 10 +2, 10 +3, 10 +1) = 13.
    //
    // processorTime = [10,20], tasks = [2,3,1,2,5,8,4,3]
    // max(10 + 3, 10 + 5, 10 + 8, 10 + 4) = 18.
    // max(20 + 2, 20 + 1, 20 + 2, 20 + 3) = 23.
    //
    // 通过排序将数据的特征显示出来
    // 10, 20  1 2 4 7  8 8 9 10
    //
    // O(4n*log(4n)) 对Task的排序更耗时间
    // O(1)
    public int minProcessingTime(List<Integer> processorTime, List<Integer> tasks) {
        Collections.sort(processorTime);
        Collections.sort(tasks);

        int maxTime = 0;
        for (int index = 0; index < processorTime.size(); index++) {
            for (int k = 0; k < 4; k++) {
                int taskIndex = tasks.size() - 1 - 4 * index - k;
                maxTime = Math.max(maxTime, processorTime.get(index) + tasks.get(taskIndex));
            }
        }
        return maxTime;
    }
}
