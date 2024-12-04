package templates.sorting.quick_sort;

// 快速排序步骤:
// 1. 从数列中挑出一个元素称为pivot基准
// 2. 重新排序数列，比基准值小的放在基准前，比基准值大的放在基准后
// 3. 以基准点最后所在位置划分，递归将左右两边分区快速排序
public class QuickSorting {

    public void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(nums, left, right);
            quickSort(nums, left, partitionIndex - 1); // 左边分区
            quickSort(nums, partitionIndex + 1, right); // 右边分区
        }
    }

    // TODO: 基准点的选择算法可能导致不同的时间复杂度
    // 1. 直接取首尾元素的值作为pivot基准值
    // 2. 在指定的范围中随机选择一个值作为基准值
    public int partition(int[] nums, int left, int right) {
        int pivotValue = nums[right];

        int countIndex = left-1;
        for (int j=left; j < right; j++) {
            if (nums[j] <= pivotValue) {
                countIndex++;
                swap(nums, countIndex, right);
            }
        }
        countIndex++;
        swap(nums, countIndex, right);
        return countIndex;
    }

    public void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
