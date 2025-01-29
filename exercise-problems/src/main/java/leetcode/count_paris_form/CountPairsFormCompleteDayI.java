package leetcode.count_paris_form;

// Count Pairs That Form a Complete Day I
// Given an integer array hours representing times in hours,
// return an integer denoting the number of pairs i, j
// where i < j and hours[i] + hours[j] forms a complete day.
//
// [12,12,30,24,24] -> (0,1) and (3,4)
// [72,48,24,3] -> (0,1), (0,2), and (1,2)
public class CountPairsFormCompleteDayI {

    public static void main(String[] args) {
        int[] hours = {21,19,3};
        System.out.println(countCompleteDayPairs(hours));
    }

    // TODO. 基础算法: 两个值来组合(1的个数自动计算，0只能和0两两组合)
    // 12,12,30,24,24
    // 0  0  0  1  1
    //
    // 72,48,24,3
    // 1  1  1  0
    //
    // O(n*n) 数组中0的数量决定时间复杂度
    // O(1)
    public static int countCompleteDayPairs(int[] hours) {
        int copyIndex = 0;
        int[] copyHours = new int[hours.length];

        int countResult = 0;
        int countOne = 0;
        for (int hour: hours) {
            if (hour % 24 == 0) {
                countOne++;
            } else {
                copyHours[copyIndex++] = hour % 24;
            }
        }
        if (countOne > 1) {
            countResult += countOne * (countOne - 1) / 2;
        }

        for (int i=0; i < hours.length - countOne; i++) {
            for (int j=i+1; j < hours.length - countOne; j++) {
                if ((copyHours[i] + copyHours[j]) % 24 == 0) {
                    countResult++;
                }
            }
        }
        return countResult;
    }
}
