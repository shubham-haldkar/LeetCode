public class UniqueXorTripletsMain {
    
    public static void main(String[] args) {
        
    }

     public int uniqueXorTriplets(int[] nums) {
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }

        int size = 1;
        while (size <= max) {
            size <<= 1;
        }

        boolean[] one = new boolean[size];
        boolean[] two = new boolean[size];
        boolean[] three = new boolean[size];

        // XOR using one element
        for (int x : nums) {
            one[x] = true;
        }

        // XOR using two elements
        for (int x : nums) {
            for (int y = 0; y < size; y++) {
                if (one[y]) {
                    two[x ^ y] = true;
                }
            }
        }

        // XOR using three elements
        for (int x : nums) {
            for (int y = 0; y < size; y++) {
                if (two[y]) {
                    three[x ^ y] = true;
                }
            }
        }

        int ans = 0;
        for (boolean possible : three) {
            if (possible) {
                ans++;
            }
        }

        return ans;
    }
}
