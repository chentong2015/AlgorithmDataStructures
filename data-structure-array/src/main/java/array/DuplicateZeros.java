package array;

// Duplicate Zeros
// Given a fixed-length integer array arr, duplicate each occurrence of zero,
// shifting the remaining elements to the right.
//
// Note that elements beyond the length of the original array are not written.
// Do the above modifications to the input array in place and do not return anything.
//
// 1 <= arr.length <= 10^4
// 0 <= arr[i] <= 9
public class DuplicateZeros {

    // TODO. 利用统计的0数量来确定元素的最终移动位置
    // arr = [1,0,2,3,0,4,5,0] -> [1,0,0,2,3,0,0,4]
    // arr = [1,2,3] -> [1,2,3]
    //
    public void duplicateZeros(int[] arr) {
        int countZeros = 0;
        for (int j : arr) {
            countZeros += (j == 0) ? 1 : 0;
        }

        for(int i = arr.length-1; i>=0; i--){
            // 如果当前位置为0，则往后再填一个0
            if(arr[i] == 0){
                countZeros--;
                if(i + countZeros + 1 < arr.length) {
                    arr[i+countZeros+1] = 0;
                }
            }

            // 当前index位置的移动位置为index+前面0的数目
            if(i + countZeros < arr.length) {
                arr[i+countZeros] = arr[i];
            }
        }
    }

    // TODO. 使用空间换时间, 最优的时间复杂度
    // O(N) O(N)
    public void duplicateZerosTest(int[] arr) {
        int index = 0;
        int[] temp = new int[arr.length];
        for (int num : arr) {
            temp[index] = num;
            if (num == 0) {
                if (index < arr.length - 1) {
                    index++;
                    temp[index] = num;
                }
            }
            index++;
            if (index == arr.length) {
                break;
            }
        }
        System.arraycopy(temp, 0, arr, 0, arr.length);
    }
}
