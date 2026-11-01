// 453. Minimum Moves to Equal Array Elements

public class Solution MinimumMovesEqualArrayElements{
    public int minMoves(int[] nums) {
        int min = nums[0];
        long sum = nums[0];

        // Single-pass scan to find minimum and sum simultaneously
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
            sum += nums[i];
        }

        // Total moves equals sum minus (n * min)
        return (int) (sum - (long) min * nums.length);
    }
}
