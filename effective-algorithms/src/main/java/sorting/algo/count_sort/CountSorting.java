package sorting.algo.count_sort;

// TODO. Counting Sort 计数排序：稳定的线性时间排序算法
// O(n+k) 当输入的元素是n个0到k之间的整数时
// O(n+k) 需要额外的统计元素频率的数组
public class CountSorting {

    // TODO: 计数排序是用来排序0到100之间的数字的最好的算法
    // 10个年龄不同的人，统计出有8个人的年龄比A小，那A的年龄就排在第9位
    // 1. 找出待排序的数组中最大和最小的元素
    // 2. 统计数组中每个值为i的元素出现的次数，存入数组C的第i项
    // 3. 对所有的计数累加C中的第一个元素开始，每一项和前一项相加
    // 4. 反向填充目标数组：将每个元素i放在新数组的第C[i]项，每放一个元素就将C[i]减去1

    // Input: 1, 4, 1, 2, 7, 5, 2
    //
    // Index:  0  1  2  3  4  5  6  7
    // Counts: 0  2  2  0  1  1  0  1
    //
    // Index:  0  1  2  3  4  5  6  7
    // Counts: 0  2  4  4  5  6  6  7
    // 中间没有的值累加出来的结果没有任何影响，
    // 不会取到该位置position = nums[i] - min
    //
    // Process the input data: 1, 4, 1, 2, 7, 5, 2.
    // Position of 1 is 2. 找到要放置的位置，在放置完成之后，降低位置，以便下一次放到前面
    // Put data 1 at index 2 in output. Decrease count by 1 to place,
    // next data 1 at an index 1 smaller than this index.
    public static int[] countSort(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        for (int i : nums) {
            if (i > max) max = i;
            if (i < min) min = i;
        }
        // 优化解法，压缩统计数组的大小，减少使用的空间复杂度
        int[] counts = new int[max - min + 1];
        for (int num : nums) {
            counts[num - min] += 1;
        }

        // 将每个位置的index累加前面的index之和
        for (int i = 1; i < counts.length; ++i) {
            counts[i] = counts[i] + counts[i - 1];
        }

        int[] numsSorted = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; --i) {
            int position = nums[i] - min;
            // 根据counts中统计的数目来判断nums[i]在结果数组中放置的位置
            numsSorted[counts[position]] = nums[i];
            counts[position]--;
        }
        return numsSorted;
    }
}
