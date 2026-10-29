
// 3841. Palindromic Path Queries in a Tree

import java.util.*;

public class PalindromicPathQueriesTree {
    private int n, LOG, timer;
    private List<Integer>[] graph;
    private int[][] up;
    private int[] depth, tin, tout, bit;
    private char[] chars;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        this.n = n;
        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        up = new int[LOG][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        bit = new int[n + 2];
        chars = s.toCharArray();

        int[] parent = new int[n];
        int[] iter = new int[n];
        Arrays.fill(parent, -1);

        int[] stack = new int[n];
        int top = 0;
        stack[top++] = 0;
        parent[0] = 0;

        timer = 0;

        // Iterative DFS
        while (top > 0) {
            int u = stack[top - 1];

            if (iter[u] == 0) {
                tin[u] = ++timer;
                up[0][u] = parent[u];

                for (int k = 1; k < LOG; k++) {
                    up[k][u] = up[k - 1][up[k - 1][u]];
                }
            }

            if (iter[u] < graph[u].size()) {
                int v = graph[u].get(iter[u]++);

                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            } else {
                tout[u] = timer;
                top--;
            }
        }

        // Root-to-node prefix parity masks
        int[] prefix = new int[n];

        for (int u = 0; u < n; u++) {
            prefix[u] = 1 << (chars[u] - 'a');

            if (u != 0) {
                prefix[u] ^= prefix[parent[u]];
            }
        }

        for (int u = 0; u < n; u++) {
            add(tin[u], prefix[u]);
            add(tin[u] + 1, prefix[u]);
        }

        List<Boolean> result = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");

            int u = Integer.parseInt(parts[1]);

            if (parts[0].equals("update")) {
                char c = parts[2].charAt(0);

                int delta = (1 << (chars[u] - 'a'))
                          ^ (1 << (c - 'a'));

                rangeXor(tin[u], tout[u], delta);
                chars[u] = c;

            } else {
                int v = Integer.parseInt(parts[2]);
                int ancestor = lca(u, v);

                int mask = pointQuery(tin[u])
                         ^ pointQuery(tin[v])
                         ^ (1 << (chars[ancestor] - 'a'));

                result.add((mask & (mask - 1)) == 0);
            }
        }

        return result;
    }

    private void add(int index, int value) {
        while (index <= n) {
            bit[index] ^= value;
            index += index & -index;
        }
    }

    private int pointQuery(int index) {
        int result = 0;

        while (index > 0) {
            result ^= bit[index];
            index -= index & -index;
        }

        return result;
    }

    private void rangeXor(int left, int right, int value) {
        add(left, value);
        add(right + 1, value);
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < LOG; k++) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }
}
