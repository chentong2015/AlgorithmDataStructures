package trees.delete_leaf_node;

// Delete Leaves With a Given Value
// Given a binary tree root and an integer target,
// delete all the leaf nodes with value target.
//
// Note that once you delete a leaf node with value target,
// if its parent node becomes a leaf node and has the value target, it should also be deleted
//
// The number of nodes in the tree is in the range [1, 3000].
// 1 <= Node.val, target <= 1000
public class DeleteLeafNodeValue {

    // TODO. 对二叉树进行递归Node节点删除
    // root = [1,2,3,2,null,2,4], target = 2
    // Output: [1,null,3,null,4]
    //
    // root = [1,3,3,3,2], target = 3
    // Output: [1,3,null,null,2]
    //
    //
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        return recursion(root, target);
    }

    // 左右递归删除后再设置到左右子树
    // 然后判断Parent节点是否需要被删除(变为叶子节点)
    private TreeNode recursion(TreeNode node, int target) {
        if (node == null) {
            return node;
        }
        if (node.left != null) {
            node.left = recursion(node.left, target);
        }
        if (node.right != null) {
            node.right = recursion(node.right, target);
        }
        if (node.left == null && node.right == null && node.val == target) {
            // remove the leaf node
            return null;
        }
        return node;
    }

    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode() {}
       TreeNode(int val) { this.val = val; }
       TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
       }
    }
}
