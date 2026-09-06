

// 115. Distinct Subsequences
public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        // Edge case: t cannot be formed if it's longer than s
        if (m > n) return 0;

        // dp[j] stores the count of distinct subsequences matching t[0...j-1]
        int[] dp = new int[m + 1];
        dp[0] = 1; // Base case: Empty target t can always be formed in 1 way

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        for (int i = 1; i <= n; i++) {
            char sc = sChars[i - 1];
            // Traverse backwards to avoid overwriting state needed for current iteration
            for (int j = m; j >= 1; j--) {
                if (sc == tChars[j - 1]) {
                    // dp[j] can overflow 32-bit int in some edge cases, 
                    // but LeetCode constraints guarantee answer fits in standard int
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[m];
    }
}
