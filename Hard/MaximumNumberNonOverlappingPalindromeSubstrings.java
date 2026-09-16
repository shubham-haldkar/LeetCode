
// 2472. Maximum Number of Non-overlapping Palindrome Substrings


public class MaximumNumberNonOverlappingPalindromeSubstrings {
    public int maxPalindromes(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        
        // dp[i] = max non-overlapping palindromes in s[0 ... i - 1]
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            // Carry forward previous optimal result
            dp[i] = dp[i - 1];

            // Check if a minimal palindrome of length k ends at index i - 1
            if (i >= k && isPalindrome(chars, i - k, i - 1)) {
                dp[i] = Math.max(dp[i], dp[i - k] + 1);
            }

            // Check if a minimal palindrome of length k + 1 ends at index i - 1
            if (i >= k + 1 && isPalindrome(chars, i - k - 1, i - 1)) {
                dp[i] = Math.max(dp[i], dp[i - k - 1] + 1);
            }
        }

        return dp[n];
    }

    private boolean isPalindrome(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
