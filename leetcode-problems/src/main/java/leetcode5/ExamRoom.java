package leetcode5;

import java.util.ArrayList;
import java.util.List;

// Exam Room
// There is an exam room with n seats in a single row labeled from 0 to n - 1.
// When a student enters the room, they must sit in the seat
// that maximizes the distance to the closest person.
// If there are multiple such seats, they sit in the seat with the lowest number.
// If no one is in the room, then the student sits at seat number 0.
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

    int[] seats;
    List<Integer> seatsTaken;

    public ExamRoom(int n) {
        this.seats = new int[n];
        this.seatsTaken = new ArrayList<>();
    }

    // O(n * log(n)) 时间复杂度
    public int seat() {
        List<Integer> nums = new ArrayList<>();
        int maxGap = 0;
        int start = 0;
        for (int index = 0; index < nums.size() - 1; index++) {
            int gap = nums.get(index + 1) - nums.get(index);
            if (maxGap + 1 < gap) {
                maxGap = gap;
                start = nums.get(index);
            }
        }
        return 0;
    }

    // O(n) 时间复杂度
    public void leave(int p) {
        this.seats[p] = 0;
        seatsTaken.remove(p);
    }
}
