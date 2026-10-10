package amazonTop50.race_car;

import java.util.*;

// Race Car
// Your car starts at position 0 and speed +1 on an infinite number line.
// Your car can go into negative positions. Your car drives automatically according to a sequence of instructions
//
// 'A' (accelerate) and 'R' (reverse):
// When you get an instruction 'A', your car does the following:
//  position += speed,
//  speed *= 2
//When you get an instruction 'R', your car does the following:
//  If your speed is positive then speed = -1,
//  otherwise speed = 1
//  Your position stays the same.
//
// Given a target position target, return the length of the shortest sequence of instructions to get there.
// 1 <= target <= 10^4
// position >= 0
public class RaceCarTarget {

    // TODO. BFS扩散: 逐步推理每一层能达到的目标点，DP记录已访问过的位置
    // Go as far as possible before pass target, stop and turn back
    //
    // target = 3, -> AA -> 2
    // position: 0 --> 1 --> 3
    // speed: 1 --> 2 --> 4
    //
    // target = 6 -> AAARA -> 5
    // 0 --> 1 --> 3 --> 7 --> 7 --> 6.
    // 1     2     4     8     -1    1
    //
    // 设S为BFS搜索过程中处理的不同(position, speed)状态数量
    // O(S) 假设HashSet的查找和插入平均为 O(1)
    // O(S) 保存访问状态和队列中的状态

    public int racecar(int target) {
        // TODO. 位置和速度一起才是一个状态, 同位不同速度达到的最短路径可能不同
        HashSet<String> visitedState = new HashSet<>();

        Queue<Integer[]> queue = new ArrayDeque<>();
        queue.add(new Integer[]{0,0,1});
        while(!queue.isEmpty()) {
            Integer[] array = queue.poll();
            int moves = array[0];
            int position = array[1];
            int speed = array[2];

            if (position == target) {
                return moves;
            }

            String state = position + "-" + speed;
            if (!visitedState.contains(state)) {
                visitedState.add(state);
                // 任何时候都可以使用A加速
                queue.add(new Integer[]{moves+1, position+speed, speed*2});

                // TODO. 使用R的两大条件
                // 当下一步位置已超过target且还在加速时，使用R将速度设负数，减速处理
                // 当下一步位置没超过target且速度为负数，使用R将速度设正，往target靠拢
                if ((position+speed > target && speed > 0)
                    || (position+speed < target && speed < 0)) {
                    queue.add(new Integer[]{moves+1, position, speed > 0 ? -1: 1});
                }
            }
        }
        return 0;
    }
}