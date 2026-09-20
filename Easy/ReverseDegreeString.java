// 3498. Reverse Degree of a String

public class ReverseDegreeString {
    public int reverseDegree(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int result = 0;

        for (int i = 0; i < n; i++) {
            // Direct array access eliminates String.charAt bounds-checking
            // 26 - (chars[i] - 'a') calculates reversed alphabet rank in 2 operations
            int position = 26 - (chars[i] - 'a');
            
            // 1-based positional scaling
            result += (i + 1) * position;
        }

        return result;
    }
}
