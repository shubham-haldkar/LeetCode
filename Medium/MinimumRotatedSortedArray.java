
// 153. Find Minimum in Rotated Sorted Array
public class MinimumRotatedSortedArray {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // Binary search for the rotation inflection point
        while (left < right) {
            // Bit-shift division avoids overflow and executes via single-cycle ALU
            int mid = left + ((right - left) >> 1);

            if (nums[mid] > nums[right]) {
                // Minimum must be in the right partition
                left = mid + 1;
            } else {
                // Minimum is in the left partition including mid
                right = mid;
            }
        }

        // Left and right converge on the minimum element index
        return nums[left];
    }
}
