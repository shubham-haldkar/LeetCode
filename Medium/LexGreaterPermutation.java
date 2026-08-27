
package Medium;

// 3720. Lexicographically Smallest Permutation Greater Than Target

public class LexGreaterPermutation {

    public static void main(String[] args) {
        LexGreaterPermutation solution = new LexGreaterPermutation();
        System.out.println(solution.lexGreaterPermutation("abc", "acb")); // Expected: "bca"
    }

    public String lexGreaterPermutation(String s, String target) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < target.length(); i++) {
            int idx = target.charAt(i) - 'a';

            // Try to match target[i]
            if (freq[idx] > 0) {
                sb.append(target.charAt(i));
                freq[idx]--;
                continue;
            }

            // Can't match target[i].
            // Find the smallest character greater than target[i].
            for (int j = idx + 1; j < 26; j++) {
                if (freq[j] > 0) {
                    sb.append((char) ('a' + j));
                    freq[j]--;

                    appendRemaining(sb, freq);
                    return sb.toString();
                }
            }

            // Need to backtrack.
            break;
        }

        // Backtrack through the already matched prefix
        for (int i = sb.length() - 1; i >= 0; i--) {
            char old = sb.charAt(i);
            freq[old - 'a']++;

            int idx = old - 'a';

            // Find smallest character greater than old
            for (int j = idx + 1; j < 26; j++) {
                if (freq[j] > 0) {
                    sb.setLength(i);
                    sb.append((char) ('a' + j));
                    freq[j]--;

                    appendRemaining(sb, freq);
                    return sb.toString();
                }
            }
        }

        return "";
    }

    private void appendRemaining(StringBuilder sb, int[] freq) {
        for (int i = 0; i < 26; i++) {
            sb.append(String.valueOf((char) ('a' + i)).repeat(freq[i]));
        }
    }
}