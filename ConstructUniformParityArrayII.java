
// 3876. Construct Uniform Parity Array II

public class ConstructUniformParityArrayII {
    public boolean uniformArray(int[] nums1) {
        int minVal = Integer.MAX_VALUE;
        int oddCount = 0;
        int evenCount = 0;

        for (int num : nums1) {
            if (num < minVal) {
                minVal = num;
            }
            if ((num & 1) == 1) {
                oddCount++;
            } else {
                evenCount++;
            }
        }

        // 1. Already uniform parity
        if (oddCount == 0 || evenCount == 0) {
            return true;
        }

        // 2. Uniform parity is achievable if and only if the absolute minimum element is ODD
        return (minVal & 1) == 1;
    }
}
