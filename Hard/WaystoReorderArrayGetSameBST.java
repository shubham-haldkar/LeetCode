import java.util.ArrayList;
import java.util.List;

// 1569. Number of Ways to Reorder Array to Get Same BST

public class WaystoReorderArrayGetSameBST {
    private static final int MOD = 1_000_000_007;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // Step 1: Pre-compute combinations (Pascal's Triangle) with modulo arithmetic
        comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>(n);
        for (int num : nums) {
            list.add(num);
        }

        // Subtract 1 to exclude the original arrangement configuration
        return (int) ((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> sub) {
        int size = sub.size();
        if (size <= 2) {
            return 1;
        }

        int root = sub.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Partition elements into left and right subtrees
        for (int i = 1; i < size; i++) {
            int val = sub.get(i);
            if (val < root) {
                left.add(val);
            } else {
                right.add(val);
            }
        }

        long leftWays = dfs(left);
        long rightWays = dfs(right);

        int leftSize = left.size();
        int rightSize = right.size();

        // Combine ways using binomial coefficient: C(leftSize + rightSize, leftSize)
        long ways = comb[leftSize + rightSize][leftSize];
        ways = (ways * leftWays) % MOD;
        ways = (ways * rightWays) % MOD;

        return ways;
    }
}
