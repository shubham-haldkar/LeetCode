

// 921. Minimum Add to Make Parentheses Valid

public class MinimumAddMakeParenthesesValid {
    public int minAddToMakeValid(String s) {
        int n = s.length();
        int openNeeded = 0; // Tracks unmatched ')' requiring an added '('
        int balance = 0;    // Tracks unmatched '(' waiting for a ')'

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                balance++;
            } else { // c == ')'
                if (balance > 0) {
                    balance--; // Match with existing open bracket
                } else {
                    openNeeded++; // Unmatched close bracket needs an added open bracket
                }
            }
        }

        // Total additions = unmatched ')' + unmatched '('
        return openNeeded + balance;
    }
}
