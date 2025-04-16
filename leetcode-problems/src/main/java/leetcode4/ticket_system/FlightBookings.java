package leetcode4.ticket_system;

// Corporate Flight Bookings
// There are n flights that are labeled from 1 to n.
// You are given an array of flight bookings bookings
// bookings[i] = [firsti, lasti, seatsi] represents
// a booking for flights firsti through lasti (inclusive)
// with seatsi seats reserved for each flight in the range.
//
// Return an array answer of length n
// where answer[i] is the total number of seats reserved for flight i
//
// 1 <= n <= 2 * 10^4
// 1 <= bookings.length <= 2 * 10^4
// bookings[i].length == 3
// 1 <= firsti <= lasti <= n
// 1 <= seatsi <= 10^4
public class FlightBookings {

    // TODO. 等效于Car Pooling拼车算法，统计订票的上下位置
    // bookings = [[1,2,10],[2,3,20],[2,5,25]], n=5
    // 1  2  3  4  5
    // 10 10
    //    20 20
    //    25 25 25 25
    // [10,55,45,25,25]
    //
    // bookings = [[1,2,10],[2,2,15]], n = 2
    // 1  2
    // 10 10
    //    15
    // [10,25]
    //
    // O(M + N) M is number of Bookings, N is number of flights
    // O(N) 结果数据占的空间复杂度
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] result = new int[n];
        for (int[] booking : bookings) {
            result[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                result[booking[1]] -= booking[2];
            }
        }

        int countSeats = 0;
        for (int index = 0; index < n; index++) {
            countSeats += result[index];
            result[index] = countSeats;
        }
        return result;
    }
}
