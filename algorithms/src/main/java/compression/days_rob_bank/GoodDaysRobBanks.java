package compression.days_rob_bank;

import java.util.ArrayList;
import java.util.List;

public class GoodDaysRobBanks {

    // TODO. 算法核心: 压缩数据 + 动态编程(累计历史) + 多次遍历
    // 5, 3, 3, 3, 5, 6, 2
    // 0  1  2  3  0  0  1 = Decrease 前面下降数据的统计
    // 0  4  3  2  1  0  0 = Increase 后面上升数据的统计
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
