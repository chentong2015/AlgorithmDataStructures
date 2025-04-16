package next_values.next_permutation;

// Next Greater Element III
// Given a positive integer n, find the smallest integer
// which has exactly the same digits existing in the integer n and is greater in value than n.
// If no such positive integer exists, return -1.
//
// Note that the returned integer should fit in 32-bit integer,
// if there is a valid answer but it does not fit in 32-bit integer, return -1.
//
// 1 <= n <= 2^31 - 1
public class NextGreaterElementIII {

    // TODO. 将Digit转换成“字符比较”, 本质上等效于直接“数字比较”
    // if current number is greatest number possible with given digits return -1
    // if next permutation exceeds Integer limit return -1
    //
    // 12 -> 21
    // 101 -> 110
    // 123 -> 132
    // 1324 -> 1342
    //
    // 21 ->  -1
    // 54321 -> -1
    //
    // 53654 -> 54356
    // 53621 -> 56123
    // 230241 -> 230412
    public int nextGreaterElement(int n) {
        char[] arr = String.valueOf(n).toCharArray();

        // 从后往前找第一梯度下降的位置
        int i = arr.length-2;
        while(i >= 0 && arr[i] >= arr[i+1]) {
            i--;
        }
        if(i == -1) {
            return -1;
        }

        // 从后往前找到第一个比i位置值大的数据
        int k = arr.length - 1;
        while(arr[i] >= arr[k]) {
            k--;
        }

        swap(arr, i, k);

        // TODO. 从开和尾分别取字符构建最后的结果
        StringBuilder ans = new StringBuilder();
        for(int j=0; j<=i; j++) {
            ans.append(arr[j]);
        }
        for(int j=arr.length-1; j>i; j--) {
            ans.append(arr[j]);
        }

        // 解析字符串后判断是否在Integer有效值内
        long result = Long.parseLong(ans.toString());
        return (result > Integer.MAX_VALUE) ? -1 : (int)result;
    }

    void swap(char[] arr,int i,int j) {
        char temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
