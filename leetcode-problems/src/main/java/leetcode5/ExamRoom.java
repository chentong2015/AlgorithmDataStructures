package leetcode5;

import java.util.*;

// Exam Room
// There is an exam room with n seats in a single row labeled from 0 to n - 1.
// When a student enters the room,
// - they must sit in the seat that maximizes the distance to the closest person.
// - If there are multiple such seats, they sit in the seat with the lowest number.
// - If no one is in the room, then the student sits at seat number 0.
//
// Design a class that simulates the mentioned exam room.
// Implement the ExamRoom class:
// - ExamRoom(int n) Initializes the object of the exam room with the number of the seats n.
// - int seat() Returns the label of the seat at which the next student will set.
// - void leave(int p) Indicates that the student sitting at seat p will leave the room.
//   It is guaranteed that there will be a student sitting at seat p.
//
// 1 <= n <= 10^9
// It is guaranteed that there is a student sitting at seat p.
// At most 10^4 calls will be made to seat and leave.
public class ExamRoom {

    // TODO. 根据位置变动动态的规划下一个位置
    // 0 1 2 3 4 5 6 7 8 9
    // 0   2   4         9  进入四个新人
    // 0   2             9  出去4号位的人
    // 0   2     5       9  进入一个新的人

    private int n;
    private ArrayList<Integer> list;

    public ExamRoom(int n) {
        this.n = n;
        this.list = new ArrayList<>();
    }

    // O(P) P是当前存储的学生数目，基于当前数目来插入
    public int seat() {
        if (list.isEmpty()){
            list.add(0);
            return 0;
        }

        int length = list.size();
        int distLast = n - 1 - list.get(length - 1);
        int distMax = Math.max(list.get(0), distLast);
        for (int i = 0; i < length - 1; i++){
            int distCurrent = (list.get(i + 1) - list.get(i)) / 2;
            distMax = Math.max(distMax, distCurrent);
        }
        // 特殊情况，只能追加在开头
        if (distMax == list.get(0)){
            list.add(0, 0);
            return 0;
        }

        // TODO. 找到最大Dist位置往后添加一个数据
        for (int i = 0; i < length - 1; i++){
            int distCurrent = (list.get(i + 1) - list.get(i)) / 2;
            if (distMax == distCurrent){
                list.add(i + 1, list.get(i) + distCurrent);
                return list.get(i + 1);
            }
        }

        list.add(n - 1);
        return n - 1;
    }

    // O(P)
    public void leave(int p) {
        for (int i = 0; i < list.size(); i++){
            if (list.get(i) == p){
                list.remove(i);
                break;
            }
        }
    }
}
