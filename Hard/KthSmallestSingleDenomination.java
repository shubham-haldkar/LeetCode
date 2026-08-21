package Hard;

// 3116. Kth Smallest Amount With Single Denomination Combination

public class KthSmallestSingleDenomination {

    public static void main(String[] args) {
        KthSmallestSingleDenomination solution = new KthSmallestSingleDenomination();
        int[] coins = {2, 3, 5};
        int k = 5;
        long result = solution.findKthSmallest(coins, k);
        System.out.println("The " + k + "th smallest amount that can be formed is: " + result);
    }
    
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int numSubsets = 1 << n;
        
        long[] lcms = new long[numSubsets];
        int[] signs = new int[numSubsets];
        
        lcms[0] = 1;
        signs[0] = -1;

        // Precompute LCMs and signs (+1 for odd subset size, -1 for even) for all subsets
        for (int mask = 1; mask < numSubsets; mask++) {
            int lastBit = Integer.numberOfTrailingZeros(mask);
            int prevMask = mask ^ (1 << lastBit);
            
            long prevLcm = lcms[prevMask];
            if (prevLcm == -1) {
                lcms[mask] = -1;
            } else {
                long currentLcm = lcm(prevLcm, coins[lastBit]);
                lcms[mask] = currentLcm;
            }
            signs[mask] = -signs[prevMask];
        }

        // Binary search for the kth smallest amount
        long minCoin = coins[0];
        for (int coin : coins) minCoin = Math.min(minCoin, coin);

        long low = 1;
        long high = minCoin * k;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (countValidAmounts(mid, lcms, signs, numSubsets) >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private long countValidAmounts(long target, long[] lcms, int[] signs, int numSubsets) {
        long count = 0;
        for (int mask = 1; mask < numSubsets; mask++) {
            if (lcms[mask] != -1) {
                count += signs[mask] * (target / lcms[mask]);
            }
        }
        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        long g = gcd(a, b);
        // Avoid overflow if LCM exceeds bounds
        if (a / g > (Long.MAX_VALUE / b)) return -1;
        return (a / g) * b;
    }


}
