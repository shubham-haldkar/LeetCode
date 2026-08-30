publc class MinimumDeletions(int {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;

        int minIndex = 0;
        int maxIndex = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIndex]) minIndex = i;
            if (nums[i] > nums[maxIndex]) maxIndex = i;
        }

        int left = Math.min(minIndex, maxIndex);
        int right = Math.max(minIndex, maxIndex);

        // Option 1: Remove both from front
        int removeBothFront = right + 1;
        // Option 2: Remove both from back
        int removeBothBack = n - left;
        // Option 3: Remove left from front and right from back
        int removeBothSides = (left + 1) + (n - right);

        return Math.min(removeBothFront, Math.min(removeBothBack, removeBothSides));
    }
}
