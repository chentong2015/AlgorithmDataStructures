package amazon.Interviews.systems;

// Design Parking System
// Design a parking system for a parking lot.
// The parking lot has three kinds of parking spaces: big, medium, and small,
// with a fixed number of slots for each size.
//
// 0 <= big, medium, small <= 1000
// carType is 1, 2, or 3
// At most 1000 calls will be made to addCar
public class ParkingSystem {

    // TODO. 只需要一个变量暂存剩余存储空间的大小
    //  变量越少则比较的次数越少，耗费的时间复杂度越少
    int bigCarsRemaining;
    int mediumCarRemaining;
    int smallCarRemaining;

    public ParkingSystem(int big, int medium, int small) {
       this.bigCarsRemaining = big;
       this.mediumCarRemaining = medium;
       this.smallCarRemaining = small;
    }

    public boolean addCar(int carType) {
        if (carType == 1) {
            if (this.bigCarsRemaining == 0) {
                return false;
            }
            this.bigCarsRemaining--;
        } else if (carType == 2) {
            if (this.mediumCarRemaining == 0) {
                return false;
            }
            this.mediumCarRemaining--;
        } else {
            if (this.smallCarRemaining == 0) {
                return false;
            }
            this.smallCarRemaining--;
        }
        return true;
    }
}
