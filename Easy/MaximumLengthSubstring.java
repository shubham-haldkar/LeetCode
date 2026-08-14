package Easy ;

// 3090. Maximum Length Substring With Two Occurrences

public class MaximumLengthSubstring{

    public static void main(String[] args) {
        MaximumLengthSubstring solution = new MaximumLengthSubstring();
        System.out.println(solution.maximumLengthSubstring("bcbbbd")); // Output: 4 
    }
 
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            count[c - 'a']++;
            
            while (count[c - 'a'] > 2) {
                char leftChar = s.charAt(left);
                count[leftChar - 'a']--;
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
 
}

