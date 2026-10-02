// 22. Generate Parentheses


import java.util.ArrayList;
import java.util.List;

class Solution GenerateParenthesis{
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        // Single flat scratchpad buffer shared across backtracks
        char[] buffer = new char[2 * n];
        backtrack(result, buffer, 0, 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, char[] buffer, int pos, int open, int close, int n) {
        // Terminal state: fully populated balanced string
        if (pos == 2 * n) {
            result.add(new String(buffer));
            return;
        }

        // Branch 1: Append open parenthesis if budget remains
        if (open < n) {
            buffer[pos] = '(';
            backtrack(result, buffer, pos + 1, open + 1, close, n);
        }

        // Branch 2: Append close parenthesis if valid pairing exists
        if (close < open) {
            buffer[pos] = ')';
            backtrack(result, buffer, pos + 1, open, close + 1, n);
        }
    }
}
