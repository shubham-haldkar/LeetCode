
// 678. Valid Parenthesis String
public class ValidParenthesisString {
    public boolean checkValidString(String s) {
        int n = s.length();
        int cmin = 0; // Minimum possible required '(' count
        int cmax = 0; // Maximum possible allowable '(' count

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                cmin++;
                cmax++;
            } else if (c == ')') {
                cmin--;
                cmax--;
            } else { // c == '*'
                cmin--; // Treating '*' as ')'
                cmax++; // Treating '*' as '('
            }

            // More ')' than '(' and '*' combined
            if (cmax < 0) {
                return false;
            }

            // Clamp cmin to 0 (cannot have negative open bracket requirement)
            if (cmin < 0) {
                cmin = 0;
            }
        }

        // Valid if a net zero balance of open brackets is achievable
        return cmin == 0;
    }
}
