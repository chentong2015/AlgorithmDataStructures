package leetcode4;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// Process Tasks Using Servers
// You are given two 0-indexed integer arrays servers and tasks of lengths n and m respectively.
// - servers[i] is the weight of the ith server,
// - tasks[j] is the time needed to process the jth task in seconds
//
// Tasks are assigned to the servers using a task queue.
// Initially, all servers are free, and the queue is empty.
//
// At second j, the jth task is inserted into the queue
// (starting with the 0th task being inserted at second 0).
//
// 注意赋值给Server的条件和规则
// As long as there are free servers and the queue is not empty,
// the task in the front of the queue will be assigned to a free server with the smallest weight,
// and in case of a tie, it is assigned to a free server with the smallest index.
//
// 必须保证从头开始，按照插入的顺序来服务每个task
// If there are no free servers and the queue is not empty,
// we wait until a server becomes free and immediately assign the next task.
//
// If multiple servers become free at the same time,
// then multiple tasks from the queue will be assigned
// in order of insertion following the weight and index priorities above.
//
// A server that is assigned task j at second t will be free again at second t + tasks[j].
// Build an array ans of length m, where ans[j] is the index of the server the jth task will be assigned to.
// Return the array ans.
//
// servers.length == n
// tasks.length == m
// 1 <= n, m <= 2 * 10^5
// 1 <= servers[i], tasks[j] <= 2 * 10^5
public class ProcessTasksUsingServers {

    // TODO. 使用PriorityQueue优先队列来完成Server的排序，状态的交换
    // servers = [3,3,2]
    // tasks = [1,2,3,2,1,2]
    // answer =[2,2,0,2,1,2]
    //
    // servers = [5,1,4,3,2]
    // tasks = [2,1,2,4,5,2,1]
    // answer =[1,4,1,4,1,3,2]
    //
    // O(N + M*logM)
    // O(N + N + M)
    public int[] assignTasks(int[] servers, int[] tasks) {
        // (idServer, weight) 先根据权重排序，然后根据index排序
        Queue<int[]> free = new PriorityQueue<>((a, b) -> a[1] == b[1] ? (a[0] - b[0]) : (a[1] - b[1]));
        for(int idServer = 0; idServer < servers.length; idServer++) {
            free.add(new int[]{idServer, servers[idServer]});
        }

        // (idServer, nextFreeTime) 根据下一个空闲时间排序，从小到大
        Queue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        int curTime = 0;
        int index = 0;
        int[] answer = new int[tasks.length];
        while(index < tasks.length) {
            // 将所有已经空闲的Server重新加入空闲Heap堆中
            while(!busy.isEmpty() && busy.peek()[1] <= curTime) {
                int idServer = busy.poll()[0];
                free.add(new int[]{idServer, servers[idServer]});
            }

            // TODO. 批量分配同一个Time时间段能够执行任务的Server
            while(!free.isEmpty() && index <= curTime && index < tasks.length) {
                int idServer = free.poll()[0];
                busy.add(new int[]{idServer, curTime + tasks[index]});
                answer[index++] = idServer;
            }

            // 如果当前时间没有空闲的Server，更新curTime到下一个nextFreeTime空闲时间
            // 反之移动到下一个task
            if(free.isEmpty()) {
                curTime = busy.peek()[1];
            } else {
                curTime++;
            }
        }
        return answer;
    }
}
