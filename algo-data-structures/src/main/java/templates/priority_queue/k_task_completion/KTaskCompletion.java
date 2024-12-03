package templates.priority_queue.k_task_completion;

import java.util.*;

public class KTaskCompletion {

    // TODO. 优先级队列用于存储TopK最大差异的收益
    public static int getMaximumRewardPoints(int k, List<Integer> reward1, List<Integer> reward2) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int maxRewardPoints = 0;
        for (int index=0; index < reward1.size(); index++) {
            maxRewardPoints += reward2.get(index);
            priorityQueue.add(reward1.get(index - reward2.get(index)));
        }


        return 0;
    }





    // TODO. 一般逻辑的算法复杂度太差
    // O(n*logn) 排序算法复杂度
    // O(n)      消耗空间复杂度
    public static int getMaximumRewardPoints2(int k, List<Integer> reward1, List<Integer> reward2) {
        int maxResult = 0;
        int length = reward1.size();
        int[] gaps = new int[length];
        for(int index=0; index < length; index++) {
            gaps[index] = reward1.get(index) - reward2.get(index);
            maxResult += reward2.get(index);
        }

        Arrays.sort(gaps);
        for (int index=0; index < k; index++) {
            maxResult += gaps[length-1-index];
        }
        return maxResult;
    }
}
