package sorting.algo.count_sort;

public class CountSortingExample {

    public static int[] countSort(int[] nums){
        int max = nums[0];
        int min = nums[0];
        //finding the maximum and minimum value of the array
        for(int i : nums){
            max = Math.max(i, max);
            min = Math.min(i ,min);
        }

        //we need a frequencey array
        int[] freqArr = new int[max - min + 1];

        //stores the frequency into the freqArray
        for(int i = 0;i<nums.length;i++){
            int currentFreq = freqArr[nums[i] - min]; //its the current freq of nums[i]
            freqArr[nums[i] - min] = currentFreq + 1; //update the freq of nums[i]
        }

        int[] ans = new int[nums.length];
        int k = 0;
        for(int i = 0; i< freqArr.length; i++){
            int currentFreq = freqArr[i];
            while(currentFreq > 0){
                ans[k] = i + min;
                k++;
                currentFreq--;
            }
        }
        return ans;
    }
}
