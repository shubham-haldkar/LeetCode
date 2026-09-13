
// 835. Image Overlap


public class ImageOverlap {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;

        // Step 1: Compress 2D binary matrices into 1D primitive bitmask rows
        int[] rowMasks1 = new int[n];
        int[] rowMasks2 = new int[n];

        for (int i = 0; i < n; i++) {
            int mask1 = 0;
            int mask2 = 0;
            for (int j = 0; j < n; j++) {
                mask1 |= (img1[i][j] << j);
                mask2 |= (img2[i][j] << j);
            }
            rowMasks1[i] = mask1;
            rowMasks2[i] = mask2;
        }

        int maxOverlap = 0;

        // Step 2: Iterate over all possible 2D translations (yShift, xShift)
        for (int yShift = -n + 1; yShift < n; yShift++) {
            for (int xShift = -n + 1; xShift < n; xShift++) {
                int currentOverlap = 0;

                // Evaluate overlap across rows for the given shift
                for (int i = 0; i < n; i++) {
                    int targetRow = i + yShift;
                    if (targetRow >= 0 && targetRow < n) {
                        int r1 = rowMasks1[i];
                        int r2 = rowMasks2[targetRow];

                        // Perform horizontal shift via bitwise shift
                        int shiftedR2 = (xShift >= 0) ? (r2 << xShift) : (r2 >>> -xShift);
                        
                        // Mask overlap and compute 1s using hardware popcount
                        currentOverlap += Integer.bitCount(r1 & shiftedR2);
                    }
                }

                if (currentOverlap > maxOverlap) {
                    maxOverlap = currentOverlap;
                }
            }
        }

        return maxOverlap;
    }
}
