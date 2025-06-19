class Solution {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder result = new StringBuilder() ;

        int len;
        len = (word1.length()>word2.length() ? word2.length() : word1.length()  ) ;
        
        for(int i=0;i<len;i++){
            result.append(word1.charAt(i) )  ;
            result.append(word2.charAt(i) )  ;
        }

        if(word1.length()>word2.length()){
            result.append(word1.substring(len) ) ;
        }else{
            result.append(word2.substring(len) ) ;
        }

        return result.toString();
    }
}