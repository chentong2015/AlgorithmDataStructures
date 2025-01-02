package amazon.Interviews;

import java.util.Comparator;
import java.util.PriorityQueue;

// Maximum Units on a Truck
// You are assigned to put some amount of boxes onto one truck.
// You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
// - numberOfBoxesi is the number of boxes of type i.
// - numberOfUnitsPerBoxi is the number of units in each box of the type i.
//
// You are also given an integer truckSize, which is the maximum number of boxes
// that can be put on the truck. You can choose any boxes to put on the truck
// as long as the number of boxes does not exceed truckSize.
//
// Return the maximum total number of units that can be put on the truck.
//
// 1 <= boxTypes.length <= 1000
// 1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
// 1 <= truckSize <= 10^6
public class MaximumUnitsTruck {

    // TODO. 优先选择Top Unit单元值大的Truck，直到选满truckSize为止
    // boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
    // - 1 box of the first type that contains 3 units.
    // - 2 boxes of the second type that contain 2 units each.
    // - 3 boxes of the third type that contain 1 unit each.
    // 1*3 + 2*2 + 1*1 = 8
    //
    // boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
    // 5*10 + 3*9 + 2*7 = 91
    //
    // O(n*log(n) + size) 在优先队列中插入数据需要logN的复杂度
    // O(n)  优先队列需要相同大小的存储空间
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        PriorityQueue<QueueNode> queue = new PriorityQueue<>(
                new Comparator<QueueNode>() {
                    @Override
                    public int compare(QueueNode o1, QueueNode o2) {
                        if (o1.units < o2.units) {
                            return 1;
                        } else if (o1.units > o2.units) {
                            return -1;
                        }
                        return 0;
                    }
                });

        for (int[] boxType: boxTypes) {
            queue.add(new QueueNode(boxType[0], boxType[1]));
        }

        int maxTotalNumber = 0;
        while (truckSize > 0 && !queue.isEmpty()) {
           QueueNode node = queue.poll(); // Take the head node in queue
           if (node.number >= truckSize) {
               maxTotalNumber += truckSize * node.units;
               break;
           } else {
               maxTotalNumber += node.number * node.units;
               truckSize -= node.number;
           }
        }
        return maxTotalNumber;
    }

    class QueueNode {
        public int number;
        public int units;

        public QueueNode(int number, int units) {
            this.number = number;
            this.units = units;
        }
    }
}
