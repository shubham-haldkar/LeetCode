// https://leetcode.com/problems/can-place-flowers/

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        for ( int i=0 ; (n > 0 && i < len) ; i++ ) {
            if (flowerbed[i] == 1) {
                i += 1;
            } else if (i == len - 1 || flowerbed[i + 1] == 0) {
                n--;
                i += 1;
            }
        }
        return n <= 0;
    }
}