package amazon.question;

import java.util.ArrayList;
import java.util.List;

// Find Good Days to Rob the Bank
// given a 0-indexed integer array security[i] is the number of guards on duty on the ith day
// The days are numbered starting from 0. You are also given an integer time.
//
// this means day i is a good day to rob the bank if and only if
// security[i-time] >= security[i-time+1] >= ... >= security[i] <= ... <= security[i+time-1] <= security[i+time].
//
// security = [5,3,3,3,5,6,2], time = 2 -> [2,3]
// On day 2, we have security[0] >= security[1] >= security[2] <= security[3] <= security[4].
// On day 3, we have security[1] >= security[2] >= security[3] <= security[4] <= security[5].
//
// 1 <= security.length <= 10^5
// 0 <= security[i], time <= 10^5
public class GoodDaysRobBank {

    // TODO. 如何将之前遍历过的数据特征留下来用于后续计算
    // 查找Index位置点使得[index-time, index, index+time]构成理论凹形结构 !
    // - 测试特殊情况time=0时，所有的数据都应该返回
    // - 测试极限情况security.length=1时，只有一种结果
    //
    // 5,3,3,3,5,6,2 根据值特征来统计
    // d d x x     d 内层while循环
    //     x x u u
    // i   f j   k
    //
    // 5 3 6 7 8
    // d d u u u
    //
    // O(n) 只需要遍历一遍所有数据
    // 0(n) 存储结果数据的空间复杂度
    public static List<Integer> goodDaysToRobBank(int[] security, int time) {
        List<Integer> resultDays = new ArrayList<>();
        if (time * 2 + 1 > security.length) {
            return resultDays;
        }
        if (time == 0) {
            for (int index=0; index < security.length; index++) {
                resultDays.add(index);
            }
            return resultDays;
        }
        if (security.length == 1) {
            return time == 1 ? List.of(0): resultDays;
        }

        int index = 0;
        while (index < security.length) {
            int j = index;
            int firstEqualIndex = -1;
            while (j < security.length - 1 && security[j] >= security[j + 1]) {
                if (security[j] == security[j + 1] && firstEqualIndex == -1) {
                    firstEqualIndex = j;
                }
                j++;
            }
            if (j - index < time) {
                index = j==index ? j+1: j;
                continue;
            }
            if (firstEqualIndex == -1) {
                firstEqualIndex = j;
            }

            int k = j;
            while (k < security.length - 1 && security[k] <= security[k + 1]) {
                if (k - time >= firstEqualIndex && k - time <= j) {
                    resultDays.add(k - time);
                }
                k++;
            }
            //  因为跳出while循环的条件可能是后面出现下降，但是当前k位置仍然需要再判断
            if (k - time >= firstEqualIndex && k - time <= j) {
                resultDays.add(k - time);
            }

            index = k;
        }
        return resultDays;
    }

    public static void main(String[] args) {
        int[] security1 = {5,3,3,3,5,6,2};
        int[] security2 = {1,1,1,1,1};
        int[] security3 = {1,2,3,4,5,6};
        int[] security4 = {1,2,3,4};
        int[] security5 = {1,2,5,4,1,0,2,4,5,3,1, 2, 4, 3, 2, 4, 8};
        //                 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
        // [5,10,14]

        List<Integer> results = goodDaysToRobBank(security5, 2);
        for (int i: results) {
            System.out.println(i);
        }
    }
}
