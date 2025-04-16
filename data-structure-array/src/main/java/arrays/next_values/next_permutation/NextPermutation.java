package arrays.next_values.next_permutation;

// Next Permutation
// The next permutation of an array of integers is
// the next lexicographically greater permutation of its integer.
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 100
public class NextPermutation {

    // TODO. 算法的本质是找到能够升值的下一个break point位置
    // [1,2,3] -> [1,3,2].
    // [1,1,5] -> [1,5,1].
    // [1,5,1] -> [5,1,1]
    //
    // 4 2 5 3 -> 4 3 2 5
    // 4 3 2 1 -> 1 2 3 4
    // 1 0 0 0 -> 0 0 0 1
    //
    // O(N + N + N)
    // O(N)
    public void nextPermutation(int[] nums) {
        int ind1 = -1;
        for(int i = nums.length - 2; i>=0; i--){
            if(nums[i] < nums[i+1]){
                ind1 = i;
                break;
            }
        }
        if(ind1 == -1){
            reverse(nums,0);
            return;
        }

        // 第二次再循环找到要升值到的目标值
        int ind2 = -1;
        for(int right = nums.length-1; right > ind1; right--){
            if(nums[ind1] < nums[right] ){
                ind2 = right;
                break;
            }
        }
        swap(nums, ind1, ind2);

        // 将后续剩余的数据排序最小化处理
        reverse(nums,ind1 + 1);
    }

    // TODO. 因为从start开始后面的数据都<=start位置值，直接反转即可
    private void reverse(int[] nums,int start){
        int i = start;
        int j = nums.length-1;
        while(i < j){
            swap(nums,i,j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
