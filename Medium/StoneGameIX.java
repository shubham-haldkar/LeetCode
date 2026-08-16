package Medium;

// 2029. Stone Game IX

public class StoneGameIX {

        public static void main(String[] args) {
            StoneGameIX solution = new StoneGameIX();
            int[] stones = {2, 1,5,1,2,4,3};
            boolean result = solution.stoneGameIX(stones);
            System.out.println(result); // Output: true            
        }
    
        public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];
        for (int stone : stones) {
            count[stone % 3]++;
        }
        if (count[0] % 2 == 0) {
            return count[1] > 0 && count[2] > 0;
        }
        return Math.abs(count[1] - count[2]) > 2;
    }
}
