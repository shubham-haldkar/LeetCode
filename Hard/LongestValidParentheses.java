package Hard ;

public class LongestValidParentheses{
    public static void main(String[] args) {
        
    }

    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int ans = 0;

        for (int i = 1; i < n; i++) {

            if (s.charAt(i) == ')') {

                // Case 1: "...()"
                if (s.charAt(i - 1) == '(') {
                    dp[i] = 2;
                    if (i >= 2) {
                        dp[i] += dp[i - 2];
                    }
                }

                // Case 2: "...))"
                else {
                    int j = i - dp[i - 1] - 1;

                    if (j >= 0 && s.charAt(j) == '(') {
                        dp[i] = dp[i - 1] + 2;

                        if (j > 0) {
                            dp[i] += dp[j - 1];
                        }
                    }
                }

                ans = Math.max(ans, dp[i]);
            }
        }

        return ans;
    }
}