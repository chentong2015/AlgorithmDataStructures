package leetcodes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

// Count Days Without Meetings
// You are given a positive integer days representing
// the total number of days an employee is available for work (starting from day 1).
//
// You are also given a 2D array meetings of size n where,
// meetings[i] = [start_i, end_i] represents the starting and ending days of meeting i (inclusive).
//
// Return the count of days when the employee is available for work
// but no meetings are scheduled.
//
// The meetings may overlap.
public class CountDaysWithoutMeeting {

    // TODO. 本质上需要计算meeting占据了多少有效的天数，从而计算无会议的天数
    // Input: days = 10, meetings = [[5,7],[1,3],[9,10]]
    // Output: 2, no meeting scheduled on the 4th and 8th days.
    //
    // Input: days = 5, meetings = [[2,4],[1,3]]
    // Output: 1, no meeting scheduled on the 5th day.
    //
    // 会议重叠会导致计算天数交叉
    // 1 2 3 .............. 57
    //    3...........49
    //       23....44
    //      21.............56
    //         26.........55
    //       23..........52
    //   2..9
    // 1.........48
    //    3...31
    //
    // O(M*log(M) + M) M为会议的数量
    // O(1)
    public static int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(m -> m[0]));

        int count = 0;
        int index= 0;
        while (index < meetings.length) {
            int start = meetings[index][0];
            int end = meetings[index][1];

            int nextIndex = index + 1;
            while (nextIndex < meetings.length && end >= meetings[nextIndex][0]) {
                // 保证end值被更新到最大值
                if (end < meetings[nextIndex][1]) {
                    end = meetings[nextIndex][1];
                }
                nextIndex++;
            }

            // 统计会议所占的区间片段的长度
            count += end - start + 1;
            index = nextIndex;
        }
        // 最后返回剩余的没有会议的天数
        return days - count;
    }

    // TODO. Time Limit Exceeded 直接记录所有的Index值会造成无效值的循环
    public static int countDaysKO(int days, int[][] meetings) {
        Set<Integer> set = new HashSet<>();
        for (int index = 1; index <= days; index++) {
            set.add(index);
        }
        for (int[] meeting: meetings) {
            for (int index = meeting[0]; index <= meeting[1]; index++) {
                set.remove(index);
            }
        }
        return set.size();
    }
}
