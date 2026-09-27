// 1807. Evaluate the Bracket Pairs of a String


import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Pre-build Knowledge Map for O(1) key lookups
        // Map capacity sized to load factor to prevent dynamic rehashing
        Map<String, String> map = new HashMap<>((int)(knowledge.size() / 0.75f) + 1);
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        int n = s.length();
        // StringBuilder pre-allocated to avoid array resizing overhead
        StringBuilder sb = new StringBuilder(n);

        int i = 0;
        while (i < n) {
            char c = s.charAt(i);

            if (c == '(') {
                int startKey = i + 1;
                // Scan forward to locate key closing boundary
                while (i < n && s.charAt(i) != ')') {
                    i++;
                }

                // Extract key slice
                String key = s.substring(startKey, i);
                String val = map.get(key);

                if (val != null) {
                    sb.append(val);
                } else {
                    sb.append('?');
                }
                i++; // Skip closing ')'
            } else {
                sb.append(c);
                i++;
            }
        }

        return sb.toString();
    }
}
