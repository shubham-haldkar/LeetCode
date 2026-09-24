
// 3550. Smallest Index With Digit Sum Equal to Index

public class SmallestIndexDigitSumEqualIndex {
    public int smallestIndex(int[] nums) {
        
        for(int i=0 ; i<nums.length ; i++){
            if(getSum(nums[i]) == i ){
                return i ;
            }
        }
        return -1 ;
    }

    public int getSum(int n){
        int sum = 0 ;
        while(n > 0){
            sum = sum + (n%10) ;
            n = n/10 ;
        }
        return sum ;
    }
}
