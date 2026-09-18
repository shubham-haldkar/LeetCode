// 1520. Maximum Number of Non-Overlapping Substrings

import java.util.*;

public class MaximumNumberNonOverlappingSubstrings {
    public List<String> maxNumOfSubstrings(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);

        for (int i = 0; i < n; i++) {
            int c = chars[i] - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }

        // Pack valid ranges into a primitive long array: (left << 32) | right
        long[] validIntervals = new long[26];
        int intervalCount = 0;

        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;

            int l = first[i];
            int r = last[i];
            boolean isValid = true;

            for (int j = l; j <= r; j++) {
                int c = chars[j] - 'a';
                if (first[c] < l) {
                    isValid = false; // Expanded before current start boundary
                    break;
                }
                r = Math.max(r, last[c]);
            }

            if (isValid) {
                validIntervals[intervalCount++] = (((long) l) << 32) | (r & 0xFFFFFFFFL);
            }
        }

        // Primitive Insertion Sort on the packed long array (sort by end boundary ascending)
        // Upper 32 bits = Left boundary, Lower 32 bits = Right boundary
        for (int i = 1; i < intervalCount; i++) {
            long key = validIntervals[i];
            int keyEnd = (int) key; // Extract lower 32 bits
            int j = i - 1;

            while (j >= 0 && ((int) validIntervals[j]) > keyEnd) {
                validIntervals[j + 1] = validIntervals[j];
                j--;
            }
            validIntervals[j + 1] = key;
        }

        // Greedy interval selection
        List<String> result = new ArrayList<>();
        int prevEnd = -1;

        for (int i = 0; i < intervalCount; i++) {
            long packed = validIntervals[i];
            int l = (int) (packed >>> 32); // Extract upper 32 bits
            int r = (int) packed;          // Extract lower 32 bits

            if (l > prevEnd) {
                result.add(s.substring(l, r + 1));
                prevEnd = r;
            }
        }

        return result;
    }
}
