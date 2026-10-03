package amazonTop50.subarrays_sum_total;

import java.util.ArrayDeque;
import java.util.Deque;

public class SumSubarrayMultiMin2 {

    int MOD = 1000000007;

    // TODO. 直接根据Index(左右更小值指标+双重前缀和)累加到结果
    // 第一层 prefixSum 用来表示一个子数组的sum
    // 第二层 prefixSum2 用来快速计算一堆prefixSum的总和
    //
    // 1  3  5   2   6  3   1
    // 1  4  9   11  17 20  21  = prefixSum
    // 1  5  14  25  .  .   .   = prefixSum2
    //    l             r
    //
    public int totalStrength(int[] strength) {
        int n = strength.length;
        long[] prefixSum2 = prefixOfPrefixSum(strength, n);

        int[] leftSmaller = prevSmaller(strength, n);
        int[] rightSmallerOrEqual = nextSmallerOrEqual(strength,n);

        long res =0;
        for(int i=0; i<n; i++){
            int left = leftSmaller[i];
            int right = rightSmallerOrEqual[i];

            // 坐标的差值是"倍数"乘积因子
            long val = (i-left) * (prefixSum2[right] - prefixSum2[i]) % MOD + MOD
                        - (right-i) * (prefixSum2[i] - prefixSum2[Math.max(left, 0)]) % MOD;
            val = (strength[i] * val) % MOD;
            res = (res + val) % MOD;
        }
        return (int)res;
    }

    // 双重前缀和: "前缀和"是计算的乘积因子
    long[] prefixOfPrefixSum(int[] arr, int n){
        long[] res = new long[n+1];
        res[0] = 0;

        long sum = 0;
        for(int i=1; i<=n; i++){
            sum += arr[i-1];
            sum %= MOD;
            res[i] = (res[i-1] + sum) % MOD;
        }
        return res;
    }

    // 单调栈: index左侧更小值的坐标, 没有则为-1
    int[] prevSmaller(int[] arr, int n){
        int[] res = new int[n];
        Deque<Integer> st = new ArrayDeque<>();
        for(int i=0; i<n; i++){
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return res;
    }

    // 单调栈: index右侧更小值的坐标, 没有则为n
    int[] nextSmallerOrEqual(int[] arr, int n){
        int[] res = new int[n];
        Deque<Integer> st = new ArrayDeque<>();
        for(int i=n-1; i>=0; i--){
            while(!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }
            res[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        return res;
    }
}
