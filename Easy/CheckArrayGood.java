// 2784. Check if Array is Good

public class CheckArrayGood {
    public boolean isGood(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return false;
        }

        int maxVal = len - 1; // Expected maximum element (n - 1)
        int[] freq = new int[len];

        // Pass 1: Count frequencies with immediate bounds checking
        for (int i = 0; i < len; i++) {
            int val = nums[i];
            if (val < 1 || val > maxVal) {
                return false;
            }
            freq[val]++;
        }

        // Pass 2: Validate permutation rules
        // Numbers from 1 to maxVal - 1 must appear exactly once
        for (int i = 1; i < maxVal; i++) {
            if (freq[i] != 1) {
                return false;
            }
        }

        // maxVal must appear exactly twice
        return freq[maxVal] == 2;
    }
}
