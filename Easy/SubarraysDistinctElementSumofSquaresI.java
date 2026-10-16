// 2913. Subarrays Distinct Element Sum of Squares I

import java.util.List;

class SubarraysDistinctElementSumofSquaresI {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        
        // Unpack List into a flat primitive int[] buffer to eliminate boxing overhead in hot loops
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums.get(i);
        }

        int totalSum = 0;

        // Outer loop defines the starting index of the subarray
        for (int i = 0; i < n; i++) {
            // Primitive frequency array tracking elements up to constraints (value <= 100)
            int[] freq = new int[101];
            int distinctCount = 0;

            // Inner loop extends the ending index of the subarray
            for (int j = i; j < n; j++) {
                int val = arr[j];
                
                // If element is seen for the first time in this subarray, increment distinct count
                if (freq[val] == 0) {
                    distinctCount++;
                }
                freq[val]++;

                // Accumulate the square of the distinct count into the scalar register
                totalSum += (distinctCount * distinctCount);
            }
        }

        return totalSum;
    }
}
