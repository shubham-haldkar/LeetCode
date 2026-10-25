
// 1254. Number of Closed Islands

public class NumberClosedIslands {
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Step 1: Eliminate all land cells connected to the grid boundaries
        for (int r = 0; r < rows; r++) {
            if (grid[r][0] == 0) dfs(grid, r, 0, rows, cols);
            if (grid[r][cols - 1] == 0) dfs(grid, r, cols - 1, rows, cols);
        }
        for (int c = 0; c < cols; c++) {
            if (grid[0][c] == 0) dfs(grid, 0, c, rows, cols);
            if (grid[rows - 1][c] == 0) dfs(grid, rows - 1, c, rows, cols);
        }

        int closedCount = 0;

        // Step 2: Scan the interior for unvisited closed islands
        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < cols - 1; c++) {
                if (grid[r][c] == 0) {
                    closedCount++;
                    dfs(grid, r, c, rows, cols);
                }
            }
        }

        return closedCount;
    }

    // In-place DFS flood fill to sink connected land components
    private void dfs(int[][] grid, int r, int c, int rows, int cols) {
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == 1) {
            return;
        }
        // Mark as visited by sinking land to water in-place
        grid[r][c] = 1;

        dfs(grid, r + 1, c, rows, cols);
        dfs(grid, r - 1, c, rows, cols);
        dfs(grid, r, c + 1, rows, cols);
        dfs(grid, r, c - 1, rows, cols);
    }
}
