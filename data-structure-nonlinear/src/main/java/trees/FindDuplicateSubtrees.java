package trees;

import tree.bean.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// Find Duplicate Subtrees
// Given the root of a binary tree, return all duplicate subtrees
// For each kind of duplicate subtrees, you only need to return the root node of any one of them
// Two trees are duplicate if they have the same structure with the same node values
public class FindDuplicateSubtrees {

    // root = [1,2,3,4,null,2,4,null,null,4] -> [[2,4],[4]]
    //     1       本质上是要构建每个subtree的唯一key键值的表示，通过key确定子树是否已经存在 !!
    //   2   3     计算子树"序列化"的值来作为key     1(2(4),3(2(4),4)) 3(2(4),4)
    // 4    2  4   必须要考虑值在左边或右边的子树上面
    //     4       首先确定以什么方式遍历完整个树 ??  只能使用前序和后序遍历 !!
    private int curId = 1;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> serialToId = new HashMap<>();
        Map<Integer, Integer> idToCount = new HashMap<>();
        List<TreeNode> res = new LinkedList<>();
        postorder(root, serialToId, idToCount, res);
        return res;
    }

    // 用后续遍历构建出当前node的Serial序列, 满足重复序列的则提取到结果list中
    private int postorder(TreeNode root, Map<String, Integer> serialToId,
                          Map<Integer, Integer> idToCount, List<TreeNode> res) {
        if (root == null) return 0;
        int leftId = postorder(root.getLeft(), serialToId, idToCount, res);
        int rightId = postorder(root.getRight(), serialToId, idToCount, res);
        String curSerial = leftId + "," + root.getVal() + "," + rightId;

        int serialId = serialToId.getOrDefault(curSerial, curId);
        if (serialId == curId) curId++;
        serialToId.put(curSerial, serialId);

        int serialIdCount = idToCount.getOrDefault(serialId, 0);
        idToCount.put(serialId, serialIdCount + 1);
        if (idToCount.get(serialId) == 2) res.add(root);
        return serialId;
    }
}
