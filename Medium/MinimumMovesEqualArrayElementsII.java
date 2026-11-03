
// 462. Minimum Moves to Equal Array Elements II

import java.util.Arrays;

public class MinimumMovesEqualArrayElementsII {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int median = nums[nums.length / 2];
        long moves = 0;

        // Accumulate absolute differences from the median using primitive registers
        for (int i = 0; i < nums.length; i++) {
            moves += Math.abs((long) nums[i] - median);
        }

        return (int) moves;
    }
}
