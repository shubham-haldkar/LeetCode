
public class LexPalindromicPermutation {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        // 1. Feasibility check for palindrome construction
        int oddChar = -1;
        int oddCount = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 != 0) {
                oddCount++;
                oddChar = i;
            }
        }
        if (oddCount > 1) return "";

        int half = n / 2;
        int[] halfCnt = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCnt[i] = cnt[i] / 2;
        }

        // 2. Try exact prefix match first
        int[] left = new int[half];
        int[] remain = halfCnt.clone();
        boolean match = true;
        for (int i = 0; i < half; i++) {
            int c = target.charAt(i) - 'a';
            if (remain[c] > 0) {
                left[i] = c;
                remain[c]--;
            } else {
                match = false;
                break;
            }
        }

        if (match) {
            String candidate = buildPalindrome(left, oddChar, n);
            if (candidate.compareTo(target) > 0) return candidate;
        }

        // 3. Backtrack from rightmost divergence point pos in the left half
        for (int pos = half - 1; pos >= 0; pos--) {
            remain = halfCnt.clone();
            int[] tempLeft = new int[half];
            boolean ok = true;

            // Match prefix up to pos - 1
            for (int i = 0; i < pos; i++) {
                int c = target.charAt(i) - 'a';
                if (remain[c] > 0) {
                    tempLeft[i] = c;
                    remain[c]--;
                } else {
                    ok = false;
                    break;
                }
            }
            if (!ok) continue;

            // Try placing a character strictly greater than target[pos]
            int targetChar = target.charAt(pos) - 'a';
            boolean found = false;
            for (int c = targetChar + 1; c < 26; c++) {
                if (remain[c] > 0) {
                    tempLeft[pos] = c;
                    remain[c]--;
                    found = true;
                    break;
                }
            }
            if (!found) continue;

            // Fill remaining left half positions with smallest available characters
            for (int i = pos + 1; i < half; i++) {
                for (int c = 0; c < 26; c++) {
                    if (remain[c] > 0) {
                        tempLeft[i] = c;
                        remain[c]--;
                        break;
                    }
                }
            }

            String candidate = buildPalindrome(tempLeft, oddChar, n);
            if (candidate.compareTo(target) > 0) return candidate;
        }

        return "";
    }

    private String buildPalindrome(int[] left, int oddChar, int n) {
        int half = left.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < half; i++) sb.append((char) (left[i] + 'a'));
        if (n % 2 == 1) sb.append((char) (oddChar + 'a'));
        for (int i = half - 1; i >= 0; i--) sb.append((char) (left[i] + 'a'));
        return sb.toString();
    }
}
