
// 3414. Maximum Score of Non-overlapping Intervals

  import java.util.*;

publicclass MaximumScoreOfNonOverlappingIntervals  {

    public int[] maximumWeight(List<List<Integer>> intervalsInput) {
        int n = intervalsInput.size();
        
        // Pack into primitive parallel arrays to avoid object wrappers
        // startsEnds[i] -> upper 32 bits: start, lower 32 bits: end
        // weightsIndices[i] -> upper 32 bits: weight, lower 32 bits: originalIndex
        long[] startsEnds = new long[n];
        long[] weightsIndices = new long[n];
        
        // Primitive index tracking for sorting
        Integer[] order = new Integer[n];

        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervalsInput.get(i);
            int start = interval.get(0);
            int end = interval.get(1);
            int weight = interval.get(2);

            startsEnds[i] = (((long) start) << 32) | (end & 0xFFFFFFFFL);
            weightsIndices[i] = (((long) weight) << 32) | (i & 0xFFFFFFFFL);
            order[i] = i;
        }

        // Sort indices based on start time, then end time, then original index
        Arrays.sort(order, (a, b) -> {
            int startA = (int) (startsEnds[a] >>> 32);
            int startB = (int) (startsEnds[b] >>> 32);
            if (startA != startB) return Integer.compare(startA, startB);
            
            int endA = (int) startsEnds[a];
            int endB = (int) startsEnds[b];
            if (endA != endB) return Integer.compare(endA, endB);
            
            return Integer.compare(a, b);
        });

        // Sorted primitive arrays
        int[] sortedStarts = new int[n];
        int[] sortedEnds = new int[n];
        long[] sortedWeights = new long[n];
        int[] sortedOrigIndices = new int[n];

        for (int i = 0; i < n; i++) {
            int idx = order[i];
            sortedStarts[i] = (int) (startsEnds[idx] >>> 32);
            sortedEnds[i] = (int) startsEnds[idx];
            sortedWeights[i] = weightsIndices[idx] >>> 32;
            sortedOrigIndices[i] = (int) weightsIndices[idx];
        }

        // Binary search precomputation for non-overlapping transition index
        int[] nextIndex = new int[n];
        for (int i = 0; i < n; i++) {
            nextIndex[i] = findNext(sortedStarts, i + 1, sortedEnds[i]);
        }

        // DP Table: dp[i][k] stores maximum weight from index i with k picks remaining
        long[][] dp = new long[n + 1][5];

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {
                long skipScore = dp[i + 1][k];
                int next = nextIndex[i];
                long pickScore = sortedWeights[i] + dp[next][k - 1];

                dp[i][k] = Math.max(skipScore, pickScore);
            }
        }

        // Reconstruction pass to select lexicographically smallest indices
        int i = 0;
        int k = 4;
        int[] chosen = new int[4];
        int chosenCount = 0;

        while (i < n && k > 0) {
            long skipScore = dp[i + 1][k];
            int next = nextIndex[i];
            long pickScore = sortedWeights[i] + dp[next][k - 1];

            if (pickScore > skipScore) {
                chosen[chosenCount++] = sortedOrigIndices[i];
                i = next;
                k--;
            } else if (pickScore < skipScore) {
                i++;
            } else {
                int[] pickList = getTrajectory(sortedOrigIndices, sortedWeights, nextIndex, dp, i, k, true);
                int[] skipList = getTrajectory(sortedOrigIndices, sortedWeights, nextIndex, dp, i, k, false);

                if (compareLists(pickList, skipList) <= 0) {
                    chosen[chosenCount++] = sortedOrigIndices[i];
                    i = next;
                    k--;
                } else {
                    i++;
                }
            }
        }

        int[] result = Arrays.copyOf(chosen, chosenCount);
        Arrays.sort(result);
        return result;
    }

    private int findNext(int[] starts, int startFrom, int targetEnd) {
        int low = startFrom, high = starts.length;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (starts[mid] > targetEnd) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private int[] getTrajectory(int[] origIndices, long[] weights, int[] nextIndex, long[][] dp, int i, int k, boolean pickFirst) {
        int[] list = new int[k];
        int count = 0;
        int curr = i;
        int remK = k;

        if (pickFirst) {
            list[count++] = origIndices[curr];
            curr = nextIndex[curr];
            remK--;
        } else {
            curr++;
        }

        while (curr < origIndices.length && remK > 0) {
            long skip = dp[curr + 1][remK];
            int next = nextIndex[curr];
            long pick = weights[curr] + dp[next][remK - 1];

            if (pick >= skip) {
                list[count++] = origIndices[curr];
                curr = next;
                remK--;
            } else {
                curr++;
            }
        }

        int[] res = Arrays.copyOf(list, count);
        Arrays.sort(res);
        return res;
    }

    private int compareLists(int[] a, int[] b) {
        int len = Math.min(a.length, b.length);
        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) return Integer.compare(a[i], b[i]);
        }
        return Integer.compare(a.length, b.length);
    }
}
