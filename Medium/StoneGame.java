package Medium;

public class StoneGame {
    public static void main(String[] args) {
        
    }

    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        Integer[][] memo = new Integer[n][n];
        return maxScoreDiff(piles, 0, n - 1, memo) > 0;
    }

    private int maxScoreDiff(int[] piles, int i, int j, Integer[][] memo) {
        if (i == j) {
            return piles[i];
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int pickLeft = piles[i] - maxScoreDiff(piles, i + 1, j, memo);
        int pickRight = piles[j] - maxScoreDiff(piles, i, j - 1, memo);

        return memo[i][j] = Math.max(pickLeft, pickRight);
    }
}
