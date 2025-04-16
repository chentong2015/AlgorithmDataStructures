package leetcode4;

public class ArrayPairsDivisibleByK2 {

    // TODO. 通过数组来存储遍历过程中的余数 => 4ms 最优时间复杂度，空间复杂度一般
    //
    // O(N + K) 两次循环
    // O(K) 所有的余数都可能存储
    public boolean canArrange(int[] arr, int k) {
        int[] remainings = new int[k];
        for(int num : arr){
            // TODO. 将负数余数转换成正数的余数
            int remain = (num % k + k) % k;
            remainings[remain]++ ;
        }

        // 整除的数据频率必须是偶数
        if(remainings[0] % 2 != 0) {
            return false;
        }
        // 其余的余数数据两两组合
        for(int remain = 1 ; remain <= k / 2 ; remain++){
            if(remainings[remain] != remainings[k - remain]) {
                return false;
            }
        }
        return true ;
    }
}
