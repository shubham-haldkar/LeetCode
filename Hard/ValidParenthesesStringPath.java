// 2267. Check if There Is a Valid Parentheses String Path

public class ValidParenthesesStringPath {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxLen = m + n - 1;

        // Path length must be even and start with '(' / end with ')'
        if (maxLen % 2 != 0 || grid[0][0] != '(' || grid[m - 1][n - 1] != ')') {
            return false;
        }

        int maxBalance = maxLen / 2;

        // Flattened 1D visited array: size M * N * (maxBalance + 1)
        // Access index: (r * N + c) * (maxBalance + 1) + balance
        int kStride = maxBalance + 1;
        boolean[] visited = new boolean[m * n * kStride];

        return dfs(0, 0, 0, grid, m, n, kStride, visited);
    }

    private boolean dfs(int r, int c, int bal, char[][] grid, int m, int n, int kStride, boolean[] visited) {
        bal += (grid[r][c] == '(') ? 1 : -1;

        // Invalid path: balance dropped below zero or exceeded maximum possible open brackets
        if (bal < 0 || bal >= kStride) {
            return false;
        }

        // Reached destination cell
        if (r == m - 1 && c == n - 1) {
            return bal == 0;
        }

        // Compute offset in 1D primitive table
        int stateIdx = (r * n + c) * kStride + bal;
        if (visited[stateIdx]) {
            return false;
        }
        visited[stateIdx] = true;

        // Right transition
        if (c + 1 < n && dfs(r, c + 1, bal, grid, m, n, kStride, visited)) {
            return true;
        }

        // Down transition
        if (r + 1 < m && dfs(r + 1, c, bal, grid, m, n, kStride, visited)) {
            return true;
        }

        return false;
    }
}
