// https://leetcode.com/problems/product-of-array-except-self

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result ;

        for(int i=0; i<nums.length-1 ; i++){
            int res = 1;
            for(int j=0; j<nums.length-1 ; j++){
                if(j!=i){
                    res *= nums[j];
                }
            }
            result[i] = res;
        }
        return result;
    }
}