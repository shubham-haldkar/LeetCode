// 1306. Jump Game III


public class JumpGameIII {
    public boolean canReach(int[] arr, int start) {
        // If index is outside the array or already visited
        if (start < 0 || start >= arr.length || arr[start] == -1) {
            return false;
        }

        // If we find zero, we can reach the target
        if (arr[start] == 0) {
            return true;
        }

        // Mark this index as visited
        int jump = arr[start];
        arr[start] = -1;

        // Try jumping left or right
        return canReach(arr, start + jump) ||
               canReach(arr, start - jump);
    }
}
