// 2265. Count Nodes Equal to Average of Subtree

 
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class CountNodesEqualAverageOfSubtree {
    private int matchingNodeCount = 0;

    public int averageOfSubtree(TreeNode root) {
        matchingNodeCount = 0;
        postOrder(root);
        return matchingNodeCount;
    }

    // Returns a packed long: upper 32 bits = sum, lower 32 bits = node count
    private long postOrder(TreeNode node) {
        if (node == null) {
            return 0L;
        }

        long left = postOrder(node.left);
        long right = postOrder(node.right);

        int leftSum = (int) (left >>> 32);
        int leftCount = (int) left;

        int rightSum = (int) (right >>> 32);
        int rightCount = (int) right;

        int currentSum = leftSum + rightSum + node.val;
        int currentCount = leftCount + rightCount + 1;

        if (node.val == (currentSum / currentCount)) {
            matchingNodeCount++;
        }

        // Pack currentSum and currentCount into a single long primitive
        return (((long) currentSum) << 32) | (currentCount & 0xFFFFFFFFL);
    }
}
