import java.util.HashMap;
import java.util.Map;

// 2958. Length of Longest Subarray With at Most K Frequency

public class MaxSubarrayLength{
    public static void main(String[] args) {
        MaxSubarrayLength solution = new MaxSubarrayLength();
        int[] nums = {1, 2, 2, 3, 3, 3, 4};
        int k = 2;
        int result = solution.maxSubarrayLength(nums, k);
        System.out.println("Length of longest subarray with at most " + k + " frequency is: " + result);
    }
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

            // Shrink window if frequency threshold k is breached
            while (freq.get(nums[right]) > k) {
                freq.put(nums[left], freq.get(nums[left]) - 1);
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}