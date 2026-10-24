
// 3644. Maximum K to Sort a Permutation

public class MaximumKSortPermutation {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        // Initialize ans with all bits set to 1 (-1 in two's complement)
        int ans = -1;
        boolean isSorted = true;

        // Single-pass scan over the permutation array
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                isSorted = false;
                ans &= nums[i]; // Accumulate bitwise AND of all misplaced elements
            }
        }

        // If already sorted, return 0; otherwise return the accumulated bitwise AND
        return isSorted ? 0 : Math.max(ans, 0);
    }
}
