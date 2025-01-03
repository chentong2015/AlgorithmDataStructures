package collections.tree.segment_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class learnSegmentTree {

    // Build segment tree
    // 1. 先从最下面一层的Leaf Node叶子节点开始构建值
    // 2. 根据Complete Binary Tree的结构，上层的node值直接由它的下面子树的和构成 !!
    private void buildSegmentTree(int[] nums) {
        if (nums == null || nums.length < 1) return;
        int n = nums.length;
        int[] tree = new int[nums.length * 2];
        for (int i = n, j = 0; i < 2 * n; i++, j++) {
            tree[i] = nums[j];
        }
        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    private void updateSegmentTree(int[] tree, int length, int pos, int val) {
        pos += length;
        tree[pos] = val;
        while (pos > 0) {
            int left = pos;
            int right = pos;
            if (pos % 2 == 0) {
                right = pos + 1;
            } else {
                left = pos - 1;
            }
            // parent is updated after child is updated
            tree[pos / 2] = tree[left] + tree[right];
            pos /= 2;
        }
    }

    // Count of Smaller Numbers After Self
    // return a new counts array, where counts[i] is the number of smaller elements to the right of nums[i]
    // nums = [5,2,6,1] -> [2,1,1,0] 统计每个位置的右边小于该位置值的数目
    //         Array [2,4,5,7]       ==> 使用位置区间中的和，来构建Segment Tree片段树
    //            18[0,3]
    //     6[0,1]         12[2,3]
    // 2[0]    4[1]    5[2]      7[3]

    // 算法的理解
    // 1. 首先根据约定的数据值范围设置buckets数组, 和nums数组的元素数目没有关系
    // 2. 由于值的区间包含正负, 数组的下标是非0的, 因此需要shift移动: nums[i] + offset
    // 3. 由于需要统计的是index右边所有小于index位置的树，所有应该从右往左读取到buckets数组中记录 ==> 每个位置的数目是原始数出现的次数
    // 4. 然后统计buckets数组中，在nums[index] + offset位置左边的所有的数目的总和
    public List<Integer> countSmaller(int[] nums) {
        int offset = 10000;       // offset negative to non-negative
        int size = 2 * 10000 + 1; // total possible values in nums
        int[] tree = new int[size * 2];
        List<Integer> result = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int shiftIndex = nums[i] + offset;
            int count = querySegmentTree(0, shiftIndex, tree, size);
            result.add(count);
            updateSegmentTree(shiftIndex, tree, size);
        }
        Collections.reverse(result);
        return result;
    }

    private int querySegmentTree(int left, int right, int[] tree, int size) {
        int result = 0;  // return sum of [left, right)
        left += size;    // shift the index to the leaf
        right += size;
        while (left < right) {
            if (left % 2 == 1) {  // if left is a right node, bring the value and move to parent's right node
                result += tree[left];
                left++;
            }
            left /= 2;            // directly move to parent
            if (right % 2 == 1) { // if right is a right node, bring the value of the left node and move to parent
                right--;
                result += tree[right];
            }
            right /= 2;
        }
        return result;
    }

    private void updateSegmentTree(int index, int[] tree, int size) {
        index += size;     // shift the index to the leaf
        tree[index] += 1;  // update from leaf to root
        while (index > 1) {
            index /= 2;
            tree[index] = tree[index * 2] + tree[index * 2 + 1];
        }
    }
}
