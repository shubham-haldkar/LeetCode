
// 3014. Minimum Number of Pushes to Type Word I

public class minimumPushesMain{
    public static void main(String[] args) {
        
    }
    
    public int minimumPushes(String word) {
        
        int len = word.length() ;
        int result = 0 ;
        for(int i=0;i<len ; i++){
            result = result + ((i+8)/8);
        }
        return result ;


    }
}