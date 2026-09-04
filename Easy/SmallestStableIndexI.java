// 3903. Smallest Stable Index I

publilc class SmallestStableIndexI {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return -1;

        // Precompute suffix minimums
        int[] minSuffix = new int[n];
        minSuffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minSuffix[i] = Math.min(nums[i], minSuffix[i + 1]);
        }

        // Find the smallest stable index
        int maxPrefix = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxPrefix = Math.max(maxPrefix, nums[i]);
            if (maxPrefix <= minSuffix[i] + k) {
                return i;
            }
        }

        return -1;
    }
}
