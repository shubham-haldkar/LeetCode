// 2770. Maximum Number of Jumps to Reach the Last Index


import java.util.Arrays;

class Solution MaxiJumpstoReachLastIndex{
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        // Primitive DP array to store max jumps to reach each index
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = 0; // Base case: 0 jumps to reach start

        for (int i = 0; i < n; i++) {
            // Prune unreachable states immediately
            if (dp[i] == -1) {
                continue;
            }

            int currentNum = nums[i];
            int currentJumps = dp[i];

            for (int j = i + 1; j < n; j++) {
                long diff = (long) nums[j] - currentNum;
                if (diff >= -target && diff <= target) {
                    if (currentJumps + 1 > dp[j]) {
                        dp[j] = currentJumps + 1;
                    }
                }
            }
        }

        return dp[n - 1];
    }
}
