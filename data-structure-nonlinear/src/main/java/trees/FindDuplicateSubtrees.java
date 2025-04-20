package trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// Find Duplicate Subtrees
// Given the root of a binary tree, return all duplicate subtrees
// For each kind of duplicate subtrees, you only need to return the root node of any one of them
// Two trees are duplicate if they have the same structure with the same node values
//
// The number of the nodes in the tree will be in the range [1, 5000]
// -200 <= Node.val <= 200
public class FindDuplicateSubtrees {

    // TODO. 将Sub子树的判断转换成String键值的判断, 递归查找
    //  1(2(4),3(2(4),4))
    // root = [1,2,3,4,null,2,4,null,null,4] -> [[2,4],[4]]
    //     1
    //   2   3
    // 4    2  4
    //     4
    //
    // root = [2,2,2,3,null,3,null] -> [[2,3],[3]]
    //    2
    //  2    2
    // 3    3
    //
    // O(N) 递归遍历所有的Node节点
    // O(N) 栈空间的开销
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
         HashMap<String, Integer> subTreeStrMap = new HashMap<>();
         List<TreeNode> result = new LinkedList<>();
         process(root, subTreeStrMap, result);
         return result;
    }

    // TODO. 在递归过程中构建SubTree的字符串，判断是否存在
    private String process(TreeNode currentRoot, HashMap<String, Integer> subTreeStrMap, List<TreeNode> result) {
        if (currentRoot == null) {
            return "";
        }
        // 构建Root根节点树的字符串
        int currentValue = currentRoot.val;
        String leftSubTreeStr = process(currentRoot.left, subTreeStrMap, result);
        String rightSubTreeStr = process(currentRoot.right, subTreeStrMap, result);
        String subTreeStr = currentValue + "(" + leftSubTreeStr + ")" + rightSubTreeStr;

        // 只会统计一次相同的SubTree更节点
        int foundCount = subTreeStrMap.getOrDefault(subTreeStr, 0);
        if (foundCount == 1) {
            result.add(currentRoot);
        }
        subTreeStrMap.put(subTreeStr, foundCount+1);
        return subTreeStr;
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
