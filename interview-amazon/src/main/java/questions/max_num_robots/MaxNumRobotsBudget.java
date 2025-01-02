package amazon.Interviews.max_num_robots;

import java.util.LinkedList;

// Maximum Number of Robots Within Budget
// Return the maximum number of consecutive robots you can run
// such that the total cost does not exceed budget.
//
// chargeTimes.length == runningCosts.length == n
// 1 <= n <= 5 * 10^4
// 1 <= chargeTimes[i], runningCosts[i] <= 10^5
// 1 <= budget <= 10^15
public class MaxNumRobotsBudget {

    public static void main(String[] args) {
        int[] chargeTimes = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int[] runningCosts = {100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000};

        MaxNumRobotsBudget robotsBudget = new MaxNumRobotsBudget();
        System.out.println(robotsBudget.maximumRobots(chargeTimes, runningCosts, 25205));
    }

    // TODO. 动态Sliding Windows滑动窗口问题: 选择元素必须连续，暂存遍历过的元素
    // chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
    // max(3,6,1) + 3 * sum(2,1,3) = 6 + 3 * 6 = 24 < 25
    // max(6,1,3) + 3 * sum(1,3,4) = 6 + 3 * 8 = 30 > 25
    // max(1,3) + 2 * sum(3,4) = 3 + 2 * 4 = 11
    // max(1,3,4) + 3 * sum(3,4,5) = 4 + 3 * 5 = 19
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int maxNumbers = 0;
        LinkedList<Integer> linkedList = new LinkedList<>();

        int leftIndex = 0;
        long sumValues = 0; // TODO. 一定要使用long存储大数据的和
        for (int index = 0; index < chargeTimes.length; index++) {
            saveNewIndex(linkedList, chargeTimes, index);

            sumValues += runningCosts[index];
            int maxValue = getMaxValue(linkedList, chargeTimes, leftIndex);
            long totalCost = maxValue + (long) (index - leftIndex + 1) * sumValues;

            while (totalCost > budget) {
                sumValues -= runningCosts[leftIndex];
                leftIndex++;
                maxValue = getMaxValue(linkedList, chargeTimes, leftIndex);
                totalCost = maxValue + (long) (index - leftIndex + 1) * sumValues;
            }

            maxNumbers = Math.max(maxNumbers, index - leftIndex + 1);
        }
        return maxNumbers;
    }

    // TODO. Sliding Window Maximum Problem
    //  链表存储窗口数据，添加的时候移除前面更小的数据
    private void saveNewIndex(LinkedList<Integer> linkedList, int[] chargeTimes, int index) {
        while (!linkedList.isEmpty() && chargeTimes[linkedList.peekLast()] <= chargeTimes[index]) {
            linkedList.pollLast();
        }
        linkedList.add(index);
    }

    // TODO. 最后链表peekFirst()头部坐标将代表最大值
    private int getMaxValue(LinkedList<Integer> linkedList, int[] chargeTimes, int leftIndex) {
        if (!linkedList.isEmpty() && linkedList.peek() < leftIndex) {
            linkedList.pollFirst();
        }
        if (linkedList.isEmpty()) {
            return 0;
        }
        return chargeTimes[linkedList.peek()];
    }
}
