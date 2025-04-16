package leetcode4;

// Average Waiting Time
// There is a restaurant with a single chef.
// You are given an array customers, where customers[i] = [arrivali, timei]:
// - arrivali is the arrival time of the ith customer.
// - The arrival times are sorted in non-decreasing order.
// timei is the time needed to prepare the order of the ith customer.
//
// 符合先到先服务的原则
// When a customer arrives, he gives the chef his order,
// and the chef starts preparing it once he is idle.
// The customer waits till the chef finishes preparing his order.
// The chef does not prepare food for more than one customer at a time.
// The chef prepares food for customers in the order they were given in the input.
//
// 平均时间精确都小数点后5位
// Return the average waiting time of all customers.
// Solutions within 10^-5 from the actual answer are considered accepted.
//
// 1 <= customers.length <= 10^5
// 1 <= arrivali, timei <= 10^4
// arrivali <= arrivali+1
public class AverageWaitingTime {

    // TODO. DP编程往后迭代，统计总的等待时间
    // [[1,2],[2,5],[4,3]] -> (2 + 6 + 7) / 3 = 5.
    // 1 2 3 4 5 6 7 8 9 10
    // i1>
    //     i2>
    //               i3>
    //
    // [[5,2],[5,4],[10,3],[20,1]] -> (2 + 6 + 4 + 1) / 4 = 3.25
    // 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21
    //         i1
    //         i2
    //                   i3
    //                                                  i4
    // O(N)
    // O(1)
    public double averageWaitingTime(int[][] customers) {
        double totalAwaitingTime = 0;
        int nextFreeTime = 1;
        for (int[] customer: customers) {
            int comingTime = customer[0];
            int waitingTime = customer[1];
            // 计算最大的下一个空闲时间
            nextFreeTime = Math.max(nextFreeTime, comingTime) + waitingTime;
            totalAwaitingTime += (nextFreeTime - comingTime);
        }

        String average = String.format("%.5f", totalAwaitingTime / customers.length);
        return Double.parseDouble(average);
    }
}
