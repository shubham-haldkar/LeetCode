

// 856. Score of Parentheses
public class ScoreOfParentheses {
    public int scoreOfParentheses(String s) {
        int n = s.length();
        int score = 0;
        int depth = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
                // Check if this closing bracket forms a core "()" pair
                if (s.charAt(i - 1) == '(') {
                    score += (1 << depth); // ALU bit-shift equivalent to Math.pow(2, depth)
                }
            }
        }

        return score;
    }
}
