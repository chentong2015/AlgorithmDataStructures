package array.merge_array;

public class MergeSortedArray {

    // TODO. 从last index坐标位置依次往后前填充
    // Merge Sorted Array
    // Merge nums2 into nums1 as one sorted array
    // nums1 has a size equal to m + n such that
    // it has enough space to hold additional elements from nums2
    //
    // nums1 = [1,2,3,0,0,0], m = 3
    // nums2 = [2,5,6],       n = 3
    // Output =[1,2,2,3,5,6]
    //
    // O(m + n) m标明nums1数组中的初始元素个数
    // O(1)
    public void mergeSortedArray(int[] nums1, int m, int[] nums2, int n) {
        int last = m + n - 1;
        int right1 = m - 1;
        int right2 = n - 1;
        // 第一个数组为空，则直接填充第二个数组数据
        if (m == 0) {
            while (right2 >= 0) {
                nums1[last--] = nums2[right2--];
            }
        }

        while (right1 >= 0 && right2 >= 0) {
            if (nums1[right1] < nums2[right2]) {
                nums1[last--] = nums2[right2--];
                // nums2中的数据移动完成后，right2变为-1直接退出while循环
                // 此时num1中剩余的数据无需移动，自然构成最后的结果
            } else {
                nums1[last--] = nums1[right1--];
                // 当nums1中数据移动完成后，迁移nums2中剩余数据
                if (right1 < 0) {
                    while (right2 >= 0) {
                        nums1[last--] = nums2[right2--];
                    }
                }
            }
        }
    }
}
