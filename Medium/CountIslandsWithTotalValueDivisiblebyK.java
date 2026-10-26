
// 3619. Count Islands With Total Value Divisible by K
public class CountIslandsWithTotalValueDivisiblebyK {
    public int countIslands(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        int maxCells = rows * cols;

        // Pre-allocate flat primitive stacks for iterative DFS to eliminate recursion and heap allocation
        int[] stackR = new int[maxCells];
        int[] stackC = new int[maxCells];

        int validIslands = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] > 0) {
                    long islandSum = 0;
                    int stackTop = 0;

                    // Push root
                    stackR[stackTop] = r;
                    stackC[stackTop] = c;
                    stackTop++;

                    long rootVal = grid[r][c];
                    grid[r][c] = 0; // Sink land in-place
                    islandSum += rootVal;

                    while (stackTop > 0) {
                        stackTop--;
                        int currR = stackR[stackTop];
                        int currC = stackC[stackTop];

                        // Check 4-directional neighbors (Up, Down, Left, Right)
                        if (currR > 0 && grid[currR - 1][currC] > 0) {
                            long val = grid[currR - 1][currC];
                            grid[currR - 1][currC] = 0;
                            islandSum += val;
                            stackR[stackTop] = currR - 1;
                            stackC[stackTop] = currC;
                            stackTop++;
                        }
                        if (currR + 1 < rows && grid[currR + 1][currC] > 0) {
                            long val = grid[currR + 1][currC];
                            grid[currR + 1][currC] = 0;
                            islandSum += val;
                            stackR[stackTop] = currR + 1;
                            stackC[stackTop] = currC;
                            stackTop++;
                        }
                        if (currC > 0 && grid[currR][currC - 1] > 0) {
                            long val = grid[currR][currC - 1];
                            grid[currR][currC - 1] = 0;
                            islandSum += val;
                            stackR[stackTop] = currR;
                            stackC[stackTop] = currC - 1;
                            stackTop++;
                        }
                        if (currC + 1 < cols && grid[currR][currC + 1] > 0) {
                            long val = grid[currR][currC + 1];
                            grid[currR][currC + 1] = 0;
                            islandSum += val;
                            stackR[stackTop] = currR;
                            stackC[stackTop] = currC + 1;
                            stackTop++;
                        }
                    }

                    if (islandSum % k == 0) {
                        validIslands++;
                    }
                }
            }
        }

        return validIslands;
    }
}
