package Medium;
//  https://leetcode.com/problems/reverse-words-in-a-string/submissions/
class Solution {
    public String reverseWords(String s) {
        String[] rev = s.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (int i = rev.length - 1; i >= 0; i--) {
            result.append(rev[i].trim()  );
            if (i != 0) result.append( " ");
        }
        return result.toString().trim();
    }
}