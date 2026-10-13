// 3727. Maximum Alternating Sum of Squares

public class MaxAlternatingSumSquares {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length ;
        for(int i=0 ; i< n; i++){
            nums[i] = nums[i]*nums[i] ; 
        }
        Arrays.sort(nums);
        long sum = 0;
        int i=0 ; 
        while (i< n/2 ){
             sum = sum - nums[i];
              i++ ;
        }
        while (i< n ){
             sum = sum + nums[i];
             i++ ;
        }

        return sum ;

    }
}
