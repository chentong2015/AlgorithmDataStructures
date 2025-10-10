package todo.lock_system;

import java.util.*;

// Open the Lock
// 4 circular wheels. Each wheel has 10 slots (0-9)(9-0)
// The wheels can rotate freely and wrap around:
// for example we can turn '9' to be '0', or '0' to be '9'.
// Each move consists of turning one wheel one slot.
//
// The lock initially starts at '0000', can not reach "deadends"
// Given a target representing the value of the wheels that will unlock the lock,
// return the minimum total number of turns, -1 if it's impossible
//
// 1 <= deadends.length <= 500
// deadends[i].length == 4
// target.length == 4
// target will not be in the list deadends.
// target and deadends[i] consist of digits only.
public class OpenTheLock {

    // TODO. 开锁系统: 找到最短变化路径，并且规避特殊位置
    // deadends = ["0201","0101","0102","1212","2002"], target = "0202" -> 6
    // "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
    // "0000" -> "0001" -> "0002" -> "0102" -> "0202" invalid
    //
    // deadends = ["8888"], target = "0009" -> 1
    // "0000" -> "0009"
    //
    // deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
    // -> -1 no way to reach target
    //
    // TODO. 能够使用char上下滚动变化，逐步接近目标数字
    //  0  0  0  0
    //     ....
    //  0  2  1  2
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")) {
            return 0;
        }
        // 存储不能通过的Dead特征值
        boolean[] deadArray = new boolean[10000];
        for (String dead : deadends) {
            deadArray[Integer.parseInt(dead)] = true;
        }
        if (deadArray[0]) {
            return -1;
        }

        // TODO. 使用Queue来一层一层遍历相邻移动的值
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int turns = 1;
        while (!queue.isEmpty()) {
            int length = queue.size();
            for (int i = 0; i < length; i++) {

                // TODO. 将字符的变化转变成int值的计算
                int current = queue.poll();
                for (int j = 1; j < 10000; j *= 10) {
                    int mask = current % (j * 10) / j;
                    int masked = current - (mask * j);
                    for (int k = 1; k < 10; k += 8) {
                        int next = masked + (mask + k) % 10 * j;
                        if (deadArray[next]) {
                            continue;
                        }
                        if (next == Integer.parseInt(target)) {
                            return turns;
                        }
                        deadArray[next] = true;
                        queue.add(next);
                    }
                }
            }
            turns++;
        }
        return -1;
    }
}
