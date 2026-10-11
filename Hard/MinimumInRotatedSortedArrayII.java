
// 154. Find Minimum in Rotated Sorted Array II

public class MinimumInRotatedSortedArrayII {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + ((right - left) >> 1);

            if (nums[mid] > nums[right]) {
                // Minimum is in the right partition
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                // Minimum is in the left partition including mid
                right = mid;
            } else {
                // Duplicates encountered: safely shrink right boundary
                right--;
            }
        }

        return nums[left];
    }
}
