

// 20. Valid Parentheses


import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else {
                if (st.isEmpty()) {
                    return false;
                }

                char popEle = st.pop();

                if ((popEle == '(' && ch != ')') ||
                    (popEle == '{' && ch != '}') ||
                    (popEle == '[' && ch != ']')) {
                    return false;
                }
            }
        }

        return st.isEmpty();
    }
}
