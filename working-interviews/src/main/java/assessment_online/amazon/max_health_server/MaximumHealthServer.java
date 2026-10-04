package assessment_online.amazon.max_health_server;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MaximumHealthServer {

    public static void main(String[] args) {
        int[] health = {4,5,5,6};
        int[] serverType = {1,2,1,2};

        MaximumHealthServer healthServer = new MaximumHealthServer();
        System.out.println(healthServer.findMaxHealthServer(health, serverType, 1));
    }

    // O(n*log(m)) 循环建堆的复杂度
    // O(n+n+k)    Map统计和Heap堆的存储空间
    public long findMaxHealthServer(int[] health, int[] serverType, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int index=0; index < health.length; index++) {
            // 在建堆的同时自动求和: 针对特定type类型
            int type = serverType[index];
            int sumHealth = health[index] + map.getOrDefault(type, 0);
            map.put(type, sumHealth);

            // Save only m items in priority queue(max-heap)
            // 默认最小堆，优先出堆的元素是root最小元素，保留后续更大的值
            heap.offer(sumHealth);
            if (heap.size() > m) {
                heap.poll();
            }
        }

        long result = 0;
        while (!heap.isEmpty()) {
            result += heap.poll();
        }
        return result;
    }
}
