package sorting.count_sorting;

// 查找h-index的分界位置标识
// 使数组中有h个元素至少有h个papers，其余n-h个元素拥有不超过h个paper
public class HIndexQuestion {

    // TODO: Counting Sort的测试解法，通项公式条件
    //  citations[index] >= length(citations) - index
    // H-Index
    // Given an array of integers citations
    // citations[i] is the number of citations a researcher received for their ith paper
    // return compute the researcher's h-index
    // If there are several possible values for h, the maximum one is taken as the h-index
    // citations = [3,0,6,1,5] -> 5 papers -> 3 papers with at least 3 citations -> 3
    // citations = [1,3,1]     -> 3 papers -> 1 paper with at least 1 citation   -> 1
    //
    // O(n) 以空间换取时间
    // O(n)
    public int findHIndex(int[] citations) {
        int len = citations.length;
        int[] counts = new int[len + 1];
        for (int citation : citations) {
            if (citation > len) {
                counts[len]++;
            } else {
                counts[citation]++;
            }
        }

        // 从后往前取并累加，优先考虑最大的zIndex !!
        int total = 0;
        for (int i = len; i >= 0; i--) {
            total += counts[i];
            if (total >= i) {
                return i;
            }
        }
        return 0;
    }

    // H-Index II
    // Citations is sorted in ascending order, return compute the researcher's h-index
    // citations = [0,1,3,5,6] -> 3
    // citations = [0]         -> 0
    // citations = [0,0]       -> 0
    //
    // O(nlog(n)) O(1) 将原本混乱的数据排序之后，可以去掉空间复杂度
    // O(n)       O(1) 最终的时间复杂度是O(2n)=O(n)，数组中的数据最多只能被遍历一遍
    public int hIndex(int[] citations) {
        if (citations == null || citations.length < 1) return 0;
        int count = 0;
        int right = citations.length - 1;
        for (int index = citations.length; index >= 0; index--) {
            while (right >= 0 && citations[right] >= index) {
                count++;
                right--;
            }
            if (count >= index) {
                return index;
            }
        }
        return 0;
    }

    // TODO: 具有排序的数据考虑使用二分法来查找一定的特征值(结果)
    // 0 1 2 3 4 5 6 7 8 9   length=10
    // l       m         r
    //
    // O(log(n)
    // O(1)
    public int hIndexBinarySearch(int[] citations) {
        int left = 0;
        int length = citations.length;
        int right = length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (length - mid == citations[mid]) {
                // (length - mid)表示该mid位置的右边有多少的数目(包括自身), mid是满足条件的zIndex(唯一的平衡点)
                return length - mid;
            } else if (length - mid > citations[mid]) {
                // (length - mid)右边的数目比该位置的值更大，则说明右边有一个平衡点，往上移动以找到最大的zIndex !!
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return length - left;
    }
}
