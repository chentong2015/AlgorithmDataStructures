package dfs_search.tree_to_linked_list;

import java.util.Stack;

// Flatten Binary Tree to Linked List
// Given the root of a binary tree, flatten the tree into a "linked list":
//
// The "linked list" should use the same TreeNode class
// where the right child pointer points to the next node in the list
// and the left child pointer is always null.
//
// The "linked list" should be in same order as a pre-order traversal of binary tree.
public class BinaryTreeToLinkedList {

    // pre-order traversal 前序遍历 + DFS 深度优先遍历
    //
    // 直接在Tree上进行变换，将Tree的左边的节点依次甩到右边，然后在把root往右边移动
    // 1. root.right = root.left;
    // 2. root.left = null
    // 3. root = root.right;
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            if (root.right != null)
                stack.push(root.right); // 将right右侧节点暂存到stack中，待后续的取出
            if (root.left == null) {    // left为空说明没有左侧节点了
                if (stack.isEmpty()) {  // stack为空说明没有右侧节点了，排序完成
                    break;
                }
                root.right = stack.pop(); // root的右侧节点从Stack中取出来
            } else {
                root.right = root.left;   // 将左侧节点转移到右侧
                root.left = null;
            }
            root = root.right; // root节点往右侧下沉，形成List链条
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
