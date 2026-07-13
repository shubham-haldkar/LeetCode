// https://leetcode.com/problems/move-zeroes/

class Solution {
    public void moveZeroes(int[] nums) {
        int pos=0;
        int len = nums.length ;
        for (int i=0 ; i<len ; i++ ){
            if(nums[i] != 0){
                nums[pos] = nums[i];
                pos++;
            }
        }
        for(int i = pos; i<len ;i++){
            nums[i] = 0;
        }
    }
}