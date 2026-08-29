import java.util.*;

public class LexicographicallySmallestArray {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // Pair each number with its original index
        int[][] paired = new int[n][2];
        for (int i = 0; i < n; i++) {
            paired[i][0] = nums[i];
            paired[i][1] = i;
        }

        // Sort pairs by value
        Arrays.sort(paired, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[n];
        int start = 0;

        // Group into connected components where adjacent diff <= limit
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || paired[i + 1][0] - paired[i][0] > limit) {
                // Collect original indices for current component
                List<Integer> indices = new ArrayList<>();
                for (int j = start; j <= i; j++) {
                    indices.add(paired[j][1]);
                }

                // Sort indices to place values in earliest positions
                Collections.sort(indices);

                // Reassign sorted component values to sorted target positions
                for (int j = 0; j < indices.size(); j++) {
                    result[indices.get(j)] = paired[start + j][0];
                }

                start = i + 1;
            }
        }

        return result;
    }
}
