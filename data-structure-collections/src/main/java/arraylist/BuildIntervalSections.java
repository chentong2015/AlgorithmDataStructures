package arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BuildIntervalSections {

    public static void main(String[] args) {
        Integer[] items = {0, 0, 1, 1, 1, 1, 2, 6, 7, 20};
        List<Integer> excludedNumbers = Arrays.asList(items);
        // 3, 5, 8, 9
        List<Integer> result = buildInternalNumbers(excludedNumbers, 10);
    }

    // TODO. 保存区间的时候没有必要存储remaining数据的所用中间值
    // Build Interval Sections
    // Input: excluded numbers list, max num limit
    // Output: all remaining numbers less than limit, group by [start num, end num]
    //
    // input: 0 1 1 1 5, max = 10
    // output: 2 4  6 9
    //
    // O(n*log(n) + n)  O(n) 极限情况下结构区间数据和Nums数据在一个数量级
    public static List<Integer> buildInternalNumbers(List<Integer> excludedNumbers, int maxNum) {
        List<Integer> remainingNums = new ArrayList<>();
        excludedNumbers.add(maxNum);

        // TODO. 通过排序来将"可取值区间"展现出来
        Collections.sort(excludedNumbers);

        // TODO. 规避重复ID的影响: 同时遍历前后两个数据，存在间隔则存在取值区间
        for (int index=0; index < excludedNumbers.size() - 1; index++) {
            int currentNum = excludedNumbers.get(index);
            int nextNum = excludedNumbers.get(index + 1);
            if (currentNum != nextNum) {
                if (currentNum + 1 < nextNum) {
                    remainingNums.add(currentNum + 1);
                    remainingNums.add(nextNum - 1);
                }
            }
            // 因为在Excluded List中添加limit值，因此直接判断相等
            // 如果Excluded List中没有包含limit值，则必须在添加区间的时候判断
            if (nextNum == maxNum) {
                break;
            }
        }
        return remainingNums;
    }
}
