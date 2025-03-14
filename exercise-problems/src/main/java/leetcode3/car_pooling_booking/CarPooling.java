package leetcode3.car_pooling_booking;

// Car Pooling
// There is a car with capacity empty seats.
// The vehicle only drives east (i.e., it cannot turn around and drive west).
// You are given the integer capacity and an array trips
// - trips[i] = [numPassengersi, fromi, toi]
// - the ith trip has numPassengersi passengers
// - the locations to pick them up and drop them off are fromi and toi respectively.
// The locations are given as the number of kilometers due east from the car's initial location.
// Return true if it is possible to pick up and drop off all passengers for all the given trips, or false
//
// 1 <= trips.length <= 1000
// trips[i].length == 3
// 1 <= numPassengersi <= 100
// 0 <= fromi < toi <= 1000
// 1 <= capacity <= 10^5
public class CarPooling {

    // TODO. 拼车问题: 将所有Trip标记到总的Locations位置上，全部上下车
    // trips = [[2,1,5],[3,3,7]], capacity = 4 -> false
    // 1 2 3 4 5 6 7
    // +2      -2      标记上下车的位置
    //     +3      -3  标记乘客的变化数量
    //
    // trips = [[2,1,5],[3,3,7]], capacity = 5 -> true
    // 1 2 3 4 5 6 7
    // 2 2 2 2 2
    //     3 3 3 3 3    在trip的终点位置，乘客需要下车
    //       1 1 1 1
    //           4 4 4
    // +2  +3+1-2+4-4-4 先上车再下车，车中乘客数量不为负数
    //
    // trips = [[2,1,5],[3,5,7]], capacity = 3 -> true
    //
    // O(N)
    // O(1)
    public boolean carPooling(int[][] trips, int capacity) {
        int[] locations = new int[1001];
        for (int[] trip : trips) {
            locations[trip[1]] += trip[0];
            locations[trip[2]] -= trip[0];
        }

        // TODO. 判断location位置区间上的乘客数量，小于特定容量则继续售票并补全
        int countInCar = 0;
        for (int i = 0; i <= 1000; i++) {
            countInCar += locations[i];
            if (countInCar > capacity) {
                return false;
            }
        }
        return true;
    }
}
