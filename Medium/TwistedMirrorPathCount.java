// 3665. Twisted Mirror Path Count

public class TwistedMirrorPathCount {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        // dp[c][0] and dp[c][1] track path counts entering column c from different orientations
        int[][] dp = new int[n + 1][2];
        dp[1][0] = 1;
        dp[1][1] = 1;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    // Mirror cell deflects incoming trajectories
                    int next0 = dp[c + 1][1];
                    int next1 = dp[c][0];
                    dp[c + 1][0] = next0;
                    dp[c + 1][1] = next1;
                } else {
                    // Empty cell accumulates paths from top and left
                    int sum = (dp[c + 1][1] + dp[c][0]) % MOD;
                    dp[c + 1][0] = sum;
                    dp[c + 1][1] = sum;
                }
            }
        }

        return dp[n][0];
    }
}
