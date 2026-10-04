package assessment_online.amazon;

import java.util.Arrays;
import java.util.List;

// 给定一组Node节点, 使用有限的预算进行容量升级, 再选择出特定的节点来部署
// 要求最后的部署节点的容量求“与运算”值最大，返回这个最大值
//
// 节点当前容量 capacities[i]
// 每个upgradeBudget可以让某个节点 capacity + 1
// 最终选择deploymentNode个节点最大化 bitwise AND
//
// 1 <= num <= 10^9
public class MaxBitwiseScore {

    // TODO. Bitwise AND 位运算: 从高位到低位贪心构造答案
    // 01101   (13)      01011 (11)
    //& 1111   (15)      10000 (16)
    //& 1100   (12)      01011 (11)
    // ------------      ----------
    //  1100   (12)      00000
    //
    // 1 2 4 8 11, 10, 3
    //
    // O(31*N*logN)
    // O(N)
    public int getMaxCapacityScore(List<Integer> capacities, int upgradeBudget, int deploymentNode) {
        int ans = 0;
        for (int bit = 30; bit >= 0; bit--) {
            int mask = ans | (1 << bit);

            long[] costs = new long[capacities.size()];
            for (int i = 0; i < capacities.size(); i++) {
                costs[i] = cost(capacities.get(i), mask);
            }
            Arrays.sort(costs);

            long total = 0;
            for (int i = 0; i < deploymentNode; i++) {
                total += costs[i];
                if (total > upgradeBudget) {
                    break;
                }
            }
            if (total <= upgradeBudget) {
                ans = mask;
            }
        }
        return ans;
    }

    private long cost(int capacity, int mask) {
        if ((capacity & mask) == mask) {
            return 0;
        }

        // 找最高的mask要求为1, 而a为0的bit
        int bit = 30;
        while ((capacity & (1 << bit)) != 0 || (mask & (1 << bit)) == 0) {
            bit--;
        }

        // 高于bit的部分保持 a
        long x = ((long)(capacity >>> (bit + 1))) << (bit + 1);

        // 当前bit必须变成 1
        x |= 1L << bit;

        // 低位只需要满足 mask
        x |= mask & ((1L << bit) - 1);
        return x - capacity;
    }
}