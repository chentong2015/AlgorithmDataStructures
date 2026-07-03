package assessment_online.murex;

import java.util.Collections;
import java.util.List;

public class CheckNeighboring {

    // 0 - 999 计算出座位可以被最少划分，最多可以将3个连续的座位算到一起
    // O(nlog(n)) O(1)
    public static int checkNeighboring(List<Integer> seatNumbers) {
        if (seatNumbers == null || seatNumbers.isEmpty()) {
            return 0;
        }

        int sum = 0;
        int count = 0;
        int countNumber = -1;
        Collections.sort(seatNumbers);

        for (int seat : seatNumbers) {
            if (count == 0) {
                countNumber = seat;
                count++;
            } else {
                if (countNumber + 1 == seat) {
                    count++;
                    if (count == 3) {
                        sum++; // 统计划分的组
                        count = 0;
                    }
                } else {
                    sum++;
                    count = 0;
                }
                countNumber = seat; // 注意更新统计值的变化
            }
        }
        if (count < 3) {
            sum++; // 最后一部分需要进行统计
        }
        return sum;
    }
}
