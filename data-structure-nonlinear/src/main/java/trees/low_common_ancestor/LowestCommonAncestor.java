package trees.low_common_ancestor;

import tree.bean.TreeNode;

public class LowestCommonAncestor {

    // TODO. DFS递归到左右子数进行查找，元素本身也是自己的前继
    // LCA(Lowest Common Ancestor) of a Binary Tree
    // Given a binary tree, find the lowest common ancestor of two given nodes in the tree
    // 1. All Node.val are unique
    // 2. p != q,
    // 3. p and q will exist in the tree
    //
    // Time: O(n) visit each node exactly once in the DFS.
    // Space: O(h) Maximum recursion depth/height. O(log n) for a balanced tree.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.getLeft(), p, q);
        TreeNode right = lowestCommonAncestor(root.getRight(), p, q);

        // 因为P，Q一定存在树节点中:
        // - 如果在左右子树单独找到，则返回先找到的P或Q将是它们公共前继
        // - 如果在左右子树都找到，则说明P，Q各自一边，则root节点才是它们公共前继
        if(left == null){
            return right;
        } else if(right == null){
            return left;
        } else{
            return root;
        }
    }
}
