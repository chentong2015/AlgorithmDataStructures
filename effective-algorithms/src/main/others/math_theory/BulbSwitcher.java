package math_theory;

import java.util.Arrays;

public class BulbSwitcher {

    public static void main(String[] args) {
        System.out.println((int) Math.sqrt(8));
        System.out.println(bulbSwitch(6));
    }

    // Bulb Switcher
    // There are n bulbs that are initially off. You first turn on all the bulbs,
    // then you turn off every second bulb.
    //
    // On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
    // For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb.
    // Return the number of bulbs that are on after n rounds.
    //
    // n = 6 -> 2                 返回最后亮灯的数目
    // 1 on  on  on  on  on  on   第一轮打开全部
    // 2 on  off on  off on  off  第二轮切换所有2倍数的位置
    // 3 on  off off off on  on   第三轮切换所有3倍数的位置
    // 4 on  off off on  on  on   第四轮切换所有4倍数的位置
    // 5 on  off off on  off on   第五轮切换所有5倍数的位置
    // 6 on  off off on  off off  第六轮切换所有6倍数的位置
    // 0 - 0
    // 1 - 1
    // 2 - 1
    // 3 - 1
    // 4 - 2  问题的答案符合通项公式
    // 5 - 2
    // 6 - 2
    // 7 - 2
    // 8 - 2
    public static int bulbSwitch(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        boolean[] bulbs = new boolean[n];
        Arrays.fill(bulbs, true);
        for (int index = 2; index <= n; index++) {
            int position = index;
            while (position <= n) {
                bulbs[position - 1] = !bulbs[position - 1];
                position += index;
            }
        }
        int count = 0;
        for (boolean bulb: bulbs) {
            count += bulb ? 1 : 0;
        }
        return count;
    }
}
