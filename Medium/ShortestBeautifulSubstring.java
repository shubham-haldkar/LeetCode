package Medium;

// 2904. Shortest and Lexicographically Smallest Beautiful String

public class ShortestBeautifulSubstring {

    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        int onesCount = 0;
        String result = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                onesCount++;
            }

            // Shrink window when we reach k ones
            while (onesCount == k) {
                // Remove leading zeros to minimize window size
                while (s.charAt(left) == '0') {
                    left++;
                }

                String current = s.substring(left, right + 1);

                // Update best result if current is shorter OR equal length & lexicographically smaller
                if (result.isEmpty() || current.length() < result.length() || 
                   (current.length() == result.length() && current.compareTo(result) < 0)) {
                    result = current;
                }

                // Advance left pointer to search for next window
                if (s.charAt(left) == '1') {
                    onesCount--;
                }
                left++;
            }
        }

        return result;
    }
}
