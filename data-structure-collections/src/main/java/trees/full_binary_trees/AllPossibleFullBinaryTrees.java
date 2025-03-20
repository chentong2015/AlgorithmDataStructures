package trees.full_binary_trees;

import java.util.ArrayList;
import java.util.List;

// All Possible Full Binary Trees
// Given an integer n, return a list of all possible full binary trees with n nodes.
// Each node of each tree in the answer must have Node.val == 0.
//
// Each element of the answer is the root node of one possible tree.
// You may return the final list of trees in any order.
// A full binary tree is a binary tree where each node has exactly 0 or 2 children.
//
// 1 <= n <= 20
public class AllPossibleFullBinaryTrees {

    // TODO. 通过递归左右节点将树构建出来
    // n = 1
    // [0]
    //
    // n = 2 偶数的节点数没有结果
    //
    // n = 3
    // [0, 0,0]
    //
    // n = 5
    // [0, 0,0]
    // [0, 0,0, 0,0,null,null]
    // [0, 0,0, null,null,0,0]
    //
    // n = 7
    // [0, 0,0, null,null,0,0, null,null,0,0],
    // [0, 0,0, null,null,0,0, 0,0],
    // [0, 0,0, 0,0,0,0],
    // [0, 0,0, 0,0,null,null, null,null,0,0],
    // [0, 0,0, 0,0,null,null, 0,0]
    //
    // O(n/2 * n) 每种数据组合都要构建全部的节点
    // O(n+)
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> result = new ArrayList<>();
        if (n == 1) {
            result.add(new TreeNode(0));
            return result;
        }

        // TODO. 循环组合左右子树的节点数目(排除root节点)
        for (int i=1; i < n; i+= 2) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(n - 1 - i);

            // 使用左右子树来构建Root节点
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(0, l, r);
                    result.add(root);
                }
            }
        }
        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
