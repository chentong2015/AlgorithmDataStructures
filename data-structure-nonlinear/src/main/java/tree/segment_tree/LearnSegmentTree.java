package tree.segment_tree;

public class LearnSegmentTree {

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
}
