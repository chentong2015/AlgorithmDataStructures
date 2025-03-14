package collections.stack;

import java.util.Stack;

// Daily Temperatures
// Array of integers temperatures represents the daily temperatures
// Return an array answer such that answer[i] is the number of days you have to wait
// after the i^th day to get a warmer temperature
// If there is no future day for which this is possible, keep answer[i] == 0 instead
//
// 1 <= temperatures.length <= 10^5
// 30 <= temperatures[i] <= 100
public class DailyTemperatures {

    // TODO. 典型Stack栈场景: 存储后续更大值的坐标位置，计算差值
    // [73,74,75,71,69,72,76,73]
    // [1,1,4,2,1,1,0,0]
    //
    // [30,40,50,60]
    // [1,1,1,0]
    //
    // O(N)
    // O(N + N) 原始参数数组需要用于判断，不能修改
    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] result = new int[length];
        Stack<Integer> stack = new Stack<>();

        for (int index = length - 1; index >= 0; index--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[index]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                result[index] = 0;
            } else {
                result[index] = stack.peek() - index;
            }
            stack.push(index);
        }
        return result;
    }
}
