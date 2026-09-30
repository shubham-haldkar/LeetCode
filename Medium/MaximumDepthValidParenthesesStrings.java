
// 1111. Maximum Nesting Depth of Two Valid Parentheses Strings

public class MaximumDepthValidParenthesesStrings {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ans = new int[n];
        int depth = 0;

        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);

            if (c == '(') {
                // Assign group based on depth parity, then increment depth
                ans[i] = depth & 1;
                depth++;
            } else {
                // Decrement depth first, then assign group matching corresponding '('
                depth--;
                ans[i] = depth & 1;
            }
        }

        return ans;
    }
}
