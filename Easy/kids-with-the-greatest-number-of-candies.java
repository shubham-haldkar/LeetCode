// https://leetcode.com/problems/kids-with-the-greatest-number-of-candies

class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = -1;
        for (int i:candies){
            if(maxCandies<i){
                maxCandies=i;
            }
        }
        List<Boolean> result = new ArrayList<Boolean>() ;
        for(int i : candies){
            result.add(i+extraCandies >= maxCandies) ;
        }
        return result;
    }
}