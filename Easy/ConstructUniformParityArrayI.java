package Easy ;

// 3875. Construct Uniform Parity Array I

public class ConstructUniformParityArrayI {
    public boolean uniformArray(int[] nums) {
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;
        int oddCount = 0;
        int evenCount = 0;

        for (int num : nums) {
            if (num % 2 != 0) {
                oddCount++;
                minOdd = Math.min(minOdd, num);
            } else {
                evenCount++;
                minEven = Math.min(minEven, num);
            }
        }

        // Already uniform parity
        if (oddCount == 0 || evenCount == 0) {
            return true;
        }

        // If minOdd exists and is smaller than minEven, we can convert all elements
        return minOdd < minEven || oddCount > 0;
    }
}
