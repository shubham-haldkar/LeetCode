// 940. Distinct Subsequences II

public class DistinctSubsequencesII {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int total = 0;
        int[] lastAdded = new int[26];

        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            
            // Calculate new subsequences formed ending with character 'ch'
            int newSubseq = (total + 1 - lastAdded[idx]) % MOD;
            if (newSubseq < 0) {
                newSubseq += MOD; // Keep modulo result positive
            }

            // Update total distinct count and track character's contribution
            total = (total + newSubseq) % MOD;
            lastAdded[idx] = (lastAdded[idx] + newSubseq) % MOD;
        }

        return total;
    }
}
