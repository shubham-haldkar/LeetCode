// 41477. Find Two Non-overlapping Sub-arrays Each With Target Sum

import java.util.Arrays;

public class TwoNonOverlappingSubArrays {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        
        // minLen[i] stores the min length of a sub-array with sum = target in arr[0 ... i]
        int[] minLen = new int[n];
        Arrays.fill(minLen, Integer.MAX_VALUE / 2); // Avoid overflow during addition

        int left = 0;
        int windowSum = 0;
        int minTotalLength = Integer.MAX_VALUE / 2;
        int currentMinLen = Integer.MAX_VALUE / 2;

        for (int right = 0; right < n; right++) {
            windowSum += arr[right];

            // Shrink window if sum exceeds target
            while (windowSum > target && left <= right) {
                windowSum -= arr[left];
                left++;
            }

            // Valid sub-array found
            if (windowSum == target) {
                int currentLen = right - left + 1;

                // Check if a valid non-overlapping sub-array exists to the left
                if (left > 0 && minLen[left - 1] != Integer.MAX_VALUE / 2) {
                    minTotalLength = Math.min(minTotalLength, currentLen + minLen[left - 1]);
                }

                currentMinLen = Math.min(currentMinLen, currentLen);
            }

            // Update prefix minimum length array
            minLen[right] = Math.min(right > 0 ? minLen[right - 1] : Integer.MAX_VALUE / 2, currentMinLen);
        }

        return minTotalLength >= Integer.MAX_VALUE / 2 ? -1 : minTotalLength;
    }
}
