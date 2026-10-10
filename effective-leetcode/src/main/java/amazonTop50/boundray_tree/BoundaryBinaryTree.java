package amazonTop50.boundray_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Boundary of a binary tree (Locked)
// The boundary of a binary tree is the concatenation of
//   the root,
//   the left boundary,
//   the leaves ordered from left-to-right,
//   the reverse order of the right boundary.
//
// The leaves are nodes that do not have any children
// Given the root of a binary tree, return the values of its boundary.
//
// The number of nodes in the tree is in the range [1, 104].
// -1000 <= Node.val <= 1000
public class BoundaryBinaryTree {

    // TODO. 返回二叉树的一圈边界节点: DFS遍历树中所有满足的节点
    // [1,null,2,3,4] -> [1] + [2,3] + [4]
    //   1
    //     2
    //   3   4
    //
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        result.add(root.val);
        if (root.left == root.right) {
            return result;
        }

        dfsLeftNode(result, root.left);
        dfsLeafNode(result, root);

        List<Integer> right = new ArrayList<>();
        dfsRightNode(right, root.right);
        Collections.reverse(right);

        result.addAll(right);
        return result;
    }

    private void dfsLeftNode(List<Integer> result, TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != node.right) { // 不是叶子节点
            result.add(node.val);
            if (node.left != null) {   // 优先往左子树边界
                dfsLeftNode(result, node.left);
            } else {
                dfsLeftNode(result, node.right);
            }
        }
    }

    private void dfsLeafNode(List<Integer> result, TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left == node.right) { // 是叶子节点
            result.add(node.val);
        } else {                       // 同时往左右子树递归
            dfsLeftNode(result, node.left);
            dfsLeftNode(result, node.right);
        }
    }

    private void dfsRightNode(List<Integer> result, TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != node.right) { // 不是叶子节点
            result.add(node.val);
            if (node.right != null) {  // 优先递归右子树边界
                dfsRightNode(result, node.right);
            } else {
                dfsRightNode(result, node.left);
            }
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
