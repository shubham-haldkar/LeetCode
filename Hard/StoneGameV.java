package Hard;

// 1563. Stone Game V

public class StoneGameV {
    
    public static void main(String[] args) {
        
    }

    private int[][] memo;
    private int[] pref;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        memo = new int[n][n];
        pref = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + stoneValue[i];
        }

        return solve(0, n - 1);
    }

    private int solve(int l, int r) {
        if (l == r) return 0;
        if (memo[l][r] != 0) return memo[l][r];

        int maxScore = 0;
        for (int k = l; k < r; k++) {
            int leftSum = pref[k + 1] - pref[l];
            int rightSum = pref[r + 1] - pref[k + 1];

            if (leftSum < rightSum) {
                maxScore = Math.max(maxScore, leftSum + solve(l, k));
            } else if (rightSum < leftSum) {
                maxScore = Math.max(maxScore, rightSum + solve(k + 1, r));
            } else {
                maxScore = Math.max(maxScore, leftSum + Math.max(solve(l, k), solve(k + 1, r)));
            }
        }

        return memo[l][r] = maxScore;
    }

}
