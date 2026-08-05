package Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RemainingMethods {

    public static void main(String[] args) {
        
    }
    
     public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] inv : invocations) {
            adj.get(inv[0]).add(inv[1]);
        }

        // Step 1: Traverse graph starting from k to find all suspicious methods
        boolean[] isSuspicious = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(k);
        isSuspicious[k] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : adj.get(current)) {
                if (!isSuspicious[neighbor]) {
                    isSuspicious[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        // Step 2: Check if any non-suspicious method calls a suspicious method
        for (int u = 0; u < n; u++) {
            if (!isSuspicious[u]) {
                for (int v : adj.get(u)) {
                    if (isSuspicious[v]) {
                        // Isolated group rule violated: return all methods
                        List<Integer> allMethods = new ArrayList<>();
                        for (int i = 0; i < n; i++) {
                            allMethods.add(i);
                        }
                        return allMethods;
                    }
                }
            }
        }

        // Step 3: Collect and return only non-suspicious methods
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isSuspicious[i]) {
                result.add(i);
            }
        }

        return result;
    }
}

 
