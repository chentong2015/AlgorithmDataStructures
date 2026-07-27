package googleTop50;

// Step-By-Step Directions From a Binary Tree Node to Another
// Find the shortest path starting from node s and ending at node t.
// Generate step-by-step directions of such path as a string
// consisting of only the uppercase letters 'L', 'R', and 'U'.
//
// Each letter indicates a specific direction:
// 'L' means to go from a node to its left child node.
// 'R' means to go from a node to its right child node.
// 'U' means to go from a node to its parent node.
public class BinaryTreeNodesDirection {

    // TODO. 基于root找到节点路径，再构建从上往下通路即可
    // 利用Lowest Common Ancestor最低公共前继的概念
    //
    //      5
    //   1     2
    // 3     6   4
    //
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder startPath = new StringBuilder();
        StringBuilder destPath = new StringBuilder();
        findPath(root, startValue, startPath);
        findPath(root, destValue, destPath);

        // Find the length of the common path
        int commonPathLength = 0;
        while (commonPathLength < startPath.length() && commonPathLength < destPath.length() &&
                startPath.charAt(commonPathLength) == destPath.charAt(commonPathLength)) {
            commonPathLength++;
        }

        // Build the direction path
        StringBuilder directions = new StringBuilder();
        for (int i = 0; i < startPath.length() - commonPathLength; i++) {
            directions.append("U");
        }
        for (int i = commonPathLength; i < destPath.length(); i++) {
            directions.append(destPath.charAt(i));
        }
        return directions.toString();
    }

    // O(N)
    // O(N) stack memory
    private boolean findPath(TreeNode node, int target, StringBuilder path) {
        if (node == null) {
            return false;
        }
        if (node.val == target) {
            return true;
        }

        path.append("L");
        if (findPath(node.left, target, path)) {
            return true;
        }
        path.deleteCharAt(path.length() - 1); // Remove last character

        path.append("R");
        if (findPath(node.right, target, path)) {
            return true;
        }
        path.deleteCharAt(path.length() - 1); // Remove last character
        return false;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

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
