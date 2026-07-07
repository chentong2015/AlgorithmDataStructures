package base_template.binary_search.template2;

import java.util.ArrayList;
import java.util.List;

// Find K Closest Elements
// Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array
// The result should also be sorted in ascending order
//
// |a-x|<|b-x| 或 |a-x|==|b-x| and a<b
// 两种情况说明a比b更加的接近x, 且x不一定在数组中存在
public class FindKClosestElements {

    // TODO: 判断以二分法搜索的middle为起点的K个相邻区间[mid, mid+k]的两端条件 !!
    // arr = [1,2,3,4,5,6,8,9,10], k = 4, x = 3
    // -> [1,2,3,4]   最接近3的4个数，并且有序
    //
    // O(log(n-k)+k)  时间复杂度被划分成两个部分
    // O(k) 结果列表的空间复杂度
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k; // 左边起点的最大位置，给后面的k个位置留下区间
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) { // [mid] ~ [mid + k] 在这区间片段中，如果[mid + k]右边更接近，则把left位置移动上来
                left = mid + 1;  // 反之如果[mid]左边更加接近，则[mid + k]后面的全部的数据都不用再考虑，则把right降低
            } else {
                right = mid;
            }
        }

        // 按照顺序取出k个序列的值 O(k)
        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}
