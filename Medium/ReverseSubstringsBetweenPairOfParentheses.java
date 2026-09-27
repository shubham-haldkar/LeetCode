
// 1190 Reverse Substrings Between Each Pair of Parentheses
class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        char[] str = s.toCharArray();

        // Primitive stack and pair map to avoid Integer/Node object allocations
        int[] pair = new int[n];
        int[] stack = new int[n];
        int top = 0;

        // Pass 1: Build bidirectional wormhole links between matching parentheses
        for (int i = 0; i < n; i++) {
            if (str[i] == '(') {
                stack[top++] = i;
            } else if (str[i] == ')') {
                int openIndex = stack[--top];
                pair[openIndex] = i;
                pair[i] = openIndex;
            }
        }

        // Pass 2: Traverse string with direction toggling on wormhole entry
        char[] result = new char[n];
        int resIdx = 0;
        int i = 0;
        int direction = 1; // 1 = Left-to-Right, -1 = Right-to-Left

        while (i < n) {
            if (str[i] == '(' || str[i] == ')') {
                i = pair[i];        // Teleport to matching parenthesis
                direction = -direction; // Invert traversal direction
            } else {
                result[resIdx++] = str[i];
            }
            i += direction;
        }

        return new String(result, 0, resIdx);
    }
}
