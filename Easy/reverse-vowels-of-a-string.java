// https://leetcode.com/problems/reverse-vowels-of-a-string/

class Solution {
    public String reverseVowels(String s) {
        int len = s.length();
        int left = 0, right = len - 1;
        StringBuilder sb = new StringBuilder(s);
        while (left < right) {
            while ((!isVovel(sb.charAt(left))) && left < right) {
                left += 1;
            }
            while (( !isVovel(sb.charAt(right))) && left < right) {
                right -= 1;
            }
            char tmp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, tmp);
            right -= 1;
            left += 1;
        }
        return sb.toString();
    }

    Boolean isVovel(char c){
        if(c == 'a' ||c == 'e' ||c == 'i' ||c == 'o' ||c == 'u' ||c == 'A' ||c == 'E' ||c == 'I' ||c == 'O' ||c == 'U' ){
            return true;
        }return false;
    }
}