package Hard;

// 1872. Stone Game VIII

public class StoneGameVIII { 

    public static void main(String[] args) {
        StoneGameVIII game = new StoneGameVIII();
        int[] stones = {3, 7, 2, 3};
        int result = game.stoneGameVIII(stones);
        System.out.println("Maximum score difference: " + result); // Expected output: 5
    }

    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Step 1: Compute prefix sums in-place to save memory
        long[] prefix = new long[n];
        prefix[0] = stones[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }

        // Step 2: Base case - at index n-2, player must take all remaining stones
        // maxDiff tracks dp[i] state moving backwards
        long maxDiff = prefix[n - 1];

        // Step 3: Backward DP sweep to evaluate optimal choices
        for (int i = n - 2; i >= 1; i--) {
            maxDiff = Math.max(maxDiff, prefix[i] - maxDiff);
        }

        return (int) maxDiff;
    }
}
