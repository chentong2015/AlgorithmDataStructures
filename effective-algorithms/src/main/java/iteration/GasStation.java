package iteration;

// Gas Station
// There are n gas stations along a circular route,
// where the amount of gas at the ith station is gas[i].
// You have a car with an unlimited gas tank and cost[i] of gas to travel
// from the ith station to its next (i + 1)th station.
// return starting index if you can travel around circuit once in clockwise direction, otherwise -1
public class GasStation {

    // TODO. 加油的本质: 迭代计算剩余的油量
    // gas = [1, 2, 3, 4, 5],
    // cost = [3, 4, 5, 1, 2] -> 3
    // tmp =  -2,-2,-2,3, 3
    //
    // gas = [3,3,4],
    // cost = [3,4,4]
    // 0,-1,0
    //
    // O(N) O(1)
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        for(int i = 0; i < gas.length; i++){
            totalGas += gas[i];
            totalCost += cost[i];
        }
        // 理论上加油的gas总量必须不能低于消耗的总量
        if(totalGas < totalCost) {
            return -1;
        }

        // TODO. 只要维持剩余的gas不为0，便可以继续前进
        int remainsGas = 0;
        int start = 0;
        for(int i = 0; i < gas.length; i++){
            remainsGas = remainsGas + (gas[i] - cost[i]);
            if(remainsGas < 0){
                start = i + 1;
                remainsGas = 0;
            }
        }
        return start;
    }
}
