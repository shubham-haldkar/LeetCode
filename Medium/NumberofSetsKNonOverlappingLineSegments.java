
// 1621. Number of Sets of K Non-Overlapping Line Segments

public class NumberofSetsKNonOverlappingLineSegments {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        // dp[j][0] = ways to place j segments when NOT actively drawing
        // dp[j][1] = ways to place j segments when ACTIVELY drawing
        int[][] dp = new int[k + 1][2];

        // Base cases: when k = 0 (all segments drawn), exactly 1 valid way exists
        for (int i = n - 1; i >= 0; i--) {
            int[][] nextDp = new int[k + 1][2];
            nextDp[0][0] = 1;
            nextDp[0][1] = 1;

            for (int j = 1; j <= k; j++) {
                // State 1: Not drawing at point i
                // Option A: Skip point i -> dp[j][0]
                // Option B: Start drawing a segment at point i -> dp[j][1]
                long notDrawing = (dp[j][0] + dp[j][1]) % MOD;

                // State 2: Actively drawing at point i
                // Option A: Keep drawing past point i -> dp[j][1]
                // Option B: Stop drawing at point i (decrease remaining count j) -> nextDp[j - 1][0]
                long drawing = (dp[j][1] + nextDp[j - 1][0]) % MOD;

                nextDp[j][0] = (int) notDrawing;
                nextDp[j][1] = (int) drawing;
            }

            dp = nextDp;
        }

        return dp[k][0];
    }
}
