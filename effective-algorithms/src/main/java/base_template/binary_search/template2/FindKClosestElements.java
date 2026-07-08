package base_template.binary_search.template2;

import java.util.ArrayList;
import java.util.List;

// Find K Closest Elements
// Given a sorted integer array arr, two integers k and x,
// return the k closest integers to x in the array
//
// The result should also be sorted in ascending order
// |a-x|<|b-x| or |a-x|==|b-x| and a<b
//
// 1 <= k <= arr.length
// 1 <= arr.length <= 10^4
// arr is sorted in ascending order. 有序数据考虑使用二分法查找
// -10^4 <= arr[i], x <= 10^4
public class FindKClosestElements {

    // TODO: 二分法搜索middle中心K个相邻区间[mid, mid+k]的条件
    // [1,2,3,4,5,6,8,9,10], k = 4, x = 3
    // -> [1,2,3,4]   最接近3的4个数，并且有序
    //
    // O(log(n-k)+k)  时间复杂度被划分成两个部分
    // O(k)           结果列表的空间复杂度
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k; // 左边起点的最大位置，给后面的k个位置留下区间
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) { // [mid]-[mid+k]区间片段中
                left = mid + 1;  // 如果[mid+k]右边更接近，则把left位置移动上来
            } else {
                right = mid;     // 如果[mid]左边更接近，则更新right标记
            }
        }

        // 退出条件left==right, 往后取k个序列的值就是结果
        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}
