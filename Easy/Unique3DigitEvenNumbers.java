// 3483. Unique 3-Digit Even Numbers

public class Unique3DigitEvenNumbers {
    public int totalNumbers(int[] digits) {
        // Step 1: Count digit frequencies (Zero-Allocation Heap Footprint)
        int[] freq = new int[10];
        for (int d : digits) {
            freq[d]++;
        }

        int uniqueCount = 0;

        // Step 2: Iterate through all valid 3-digit even numbers
        for (int num = 100; num < 1000; num += 2) {
            int d1 = num / 100;       // Hundreds place
            int d2 = (num / 10) % 10; // Tens place
            int d3 = num % 10;        // Units place (always even)

            // Temporarily decrement available digit counts
            freq[d1]--;
            freq[d2]--;
            freq[d3]--;

            // Verify if digits were available
            if (freq[d1] >= 0 && freq[d2] >= 0 && freq[d3] >= 0) {
                uniqueCount++;
            }

            // Restore counts for next iteration
            freq[d1]++;
            freq[d2]++;
            freq[d3]++;
        }

        return uniqueCount;
    }
}
