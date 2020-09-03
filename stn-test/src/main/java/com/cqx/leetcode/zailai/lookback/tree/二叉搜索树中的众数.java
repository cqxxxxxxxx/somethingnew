package com.cqx.leetcode.zailai.lookback.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 二叉搜索树中的众数 {
    //给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
//
// 假定 BST 有如下定义：
//
//
// 结点左子树中所含结点的值小于等于当前结点的值
// 结点右子树中所含结点的值大于等于当前结点的值
// 左子树和右子树都是二叉搜索树
//
//
// 例如：
//给定 BST [1,null,2,2],
//
//    1
//    \
//     2
//    /
//   2
//
//
// 返回[2].
//
// 提示：如果众数超过1个，不需考虑输出顺序
//
// 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
// Related Topics 树
// 👍 141 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public int[] findMode(TreeNode root) {
            Map<Integer, Integer> valCountMap = new HashMap<>();
            recursion(root, valCountMap);
            int max = 0;
            for (Integer value : valCountMap.values()) {
                max = Math.max(max, value);
            }
            List<Integer> rr = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : valCountMap.entrySet()) {
                if (entry.getValue().equals(max)) {
                    rr.add(entry.getKey());
                }
            }
            int[] r = new int[rr.size()];
            int index = 0;
            for (Integer integer : rr) {
                r[index] = integer;
                index++;
            }
            return r;
        }

        private void recursion(TreeNode node, Map<Integer, Integer> valCountMap) {
            //termination
            if (node == null) {
                return;
            }
            //processing
            Integer count = valCountMap.get(node.val);
            if (count == null) {
                valCountMap.put(node.val, 1);
            } else {
                valCountMap.put(node.val, count + 1);
            }
            //drill down
            recursion(node.left, valCountMap);
            recursion(node.right, valCountMap);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
