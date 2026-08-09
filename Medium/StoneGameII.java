package Medium;

public class StoneGameII{
 
    private int[][] dp;
    private int[] suffix;
    private int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;
        dp = new int[n][n + 1];
        suffix = new int[n];

        // Compute suffix sums
        suffix[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        return dfs(0, 1);
    }

    private int dfs(int i, int M) {
        if (i >= n) return 0;

        // Can take all remaining piles
        if (2 * M >= n - i) {
            return suffix[i];
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int best = 0;

        for (int x = 1; x <= 2 * M; x++) {
            best = Math.max(best,
                    suffix[i] - dfs(i + x, Math.max(M, x)));
        }

        dp[i][M] = best;
        return best;
    }
 

}
