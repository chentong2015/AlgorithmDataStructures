package amazon.question.days_rob_bank;

import java.util.ArrayList;
import java.util.List;

public class GoodDaysRobBank {

    // TODO. 使用三指针(无空间复杂度)将将会操作TLE(针对大量全部是相等的数据)
    // 5,3,3,3,5,6,2 [index-time, index, index+time] 构成凹形结构
    // i   j   k     将凹形结构作为Sliding Windows滑动窗口移动
    //   i   j   k
    public static List<Integer> goodDaysToRobBank(int[] security, int time) {
        List<Integer> goodDays = new ArrayList<>();

        // 特殊情况time=0时，所有的数据都应该返回
        if (time == 0) {
            for (int index=0; index < security.length; index++) {
                goodDays.add(index);
            }
            return goodDays;
        }
        if(security.length < 3) {
            return goodDays;
        }

        int index = 0;
        while (index < security.length - 2) {
            if (security[index] >= security[index + 1]) {
                int j = index;
                while (j < security.length - 1 && security[j] >= security[j + 1]) {
                    j++;
                    if (j - index == time) { // Has found left values
                        break;
                    }
                }

                if (j - index == time) {
                    int k = j;
                    while (k < security.length - 1 && security[k] <= security[k+1]) {
                        k++;
                        if (k - j == time) { // Has found right values
                            break;
                        }
                    }
                    if (k - j == time) {
                        goodDays.add(j);
                        // TODO. Sliding Windows 核心窗口的移动
                        while (k < security.length - 1
                                && security[index] >= security[index + 1]
                                && security[j] == security[j + 1]
                                && security[k] <= security[k + 1]) {
                            index++;
                            j++;
                            k++;
                            goodDays.add(j);
                        }
                        index = k; // 移动到窗口的右边缘
                    } else {
                        index++;
                    }
                } else {
                    index++;
                }
            } else {
                index++;
            }
        }
        return goodDays;
    }
}
