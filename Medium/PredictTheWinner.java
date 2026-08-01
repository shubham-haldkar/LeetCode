package Medium;

public class PredictTheWinner {

    public static void main(String[] args) {

    }

    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        Integer[][] memo = new Integer[n][n];
        return maxScoreDiff(nums, 0, n - 1, memo) >= 0;
    }

    private int maxScoreDiff(int[] nums, int left, int right, Integer[][] memo) {
        // Base case: only one element left
        if (left == right) {
            return nums[left];
        }

        // Return cached result if available
        if (memo[left][right] != null) {
            return memo[left][right];
        }

        // Option 1: Pick from left end
        int pickLeft = nums[left] - maxScoreDiff(nums, left + 1, right, memo);

        // Option 2: Pick from right end
        int pickRight = nums[right] - maxScoreDiff(nums, left, right - 1, memo);

        // Maximize current player's relative score
        memo[left][right] = Math.max(pickLeft, pickRight);
        return memo[left][right];
    }
}
