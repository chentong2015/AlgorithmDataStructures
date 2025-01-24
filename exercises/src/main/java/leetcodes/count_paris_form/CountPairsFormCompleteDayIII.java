package leetcodes.count_paris_form;

public class CountPairsFormCompleteDayIII {

    // TODO. 最佳算法: 直接使用数组来统计24小时的offset
    public long countCompleteDayPairs(int[] hours) {
        if(hours == null || hours.length < 2){
            return 0;
        }

        long[] count = new long[24];
        for (int hour : hours){
            count[hour % 24]++;
        }

        // 数据的拼接可以只考虑一半时间
        long res = 0;
        for(int i=1; i<12; i++){
            res += count[i] * count[24-i];
        }

        // 使用数学公式计算特殊的整除数据
        return res + count[0]*(count[0]-1) / 2 + count[12]*(count[12]-1) / 2;
    }
}
