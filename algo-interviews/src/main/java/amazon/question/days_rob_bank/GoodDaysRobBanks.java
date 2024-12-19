package amazon.question.days_rob_bank;

import java.util.ArrayList;
import java.util.List;

public class GoodDaysRobBanks {

    public static void main(String[] args) {
        int[] security1 = {5, 3, 3, 3, 5, 6, 2}; // [2, 3]
        int[] security2 = {1, 1, 1, 1, 1};
        int[] security3 = {1, 2, 3, 4, 5, 6};
        int[] security4 = {1, 2, 3, 4};
        int[] security5 = {1, 2, 5, 4, 1, 0, 2, 4, 5, 3, 1, 2, 4, 3, 2, 4, 8};
        // 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 -> [5,10,14]
        List<Integer> results = goodDaysToRobBank(security1, 2);
        for (int i : results) {
            System.out.println(i);
        }
    }

    // TODO. 算法核心: 压缩数据 + 动态编程(累计历史) + 多次遍历
    // 5, 3, 3, 3, 5, 6, 2
    // 0  1  2  3  0  0  1 = Decrease 下降数据的统计
    // 0  4  3  2  1  0  0 = Increase 上升数据的统计
    public static List<Integer> goodDaysToRobBank(int[] security, int time) {
        int[] decreaseCounts = new int[security.length];
        for(int i = 1; i < security.length - time; i++){
            if(security[i-1] < security[i]){
                decreaseCounts[i] = 0;
            } else {
                decreaseCounts[i] = decreaseCounts[i-1] + 1;
            }
        }

        int[] increaseCounts = new int[security.length];
        for(int i = security.length - 2; i >= time; i--) {
            if(security[i] > security[i + 1]){
                increaseCounts[i] = 0;
            } else {
                increaseCounts[i] = increaseCounts[i + 1] + 1;
            }
        }

        // 只有满足前后特征统计的Index位置是有效位置
        ArrayList<Integer> result = new ArrayList<>();
        for(int index = time; index < security.length - time; index++) {
            if(decreaseCounts[index] >= time && time <= increaseCounts[index]) {
                result.add(index);
            }
        }
        return result;
    }
}
