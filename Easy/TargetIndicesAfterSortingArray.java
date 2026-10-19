// 2089. Find Target Indices After Sorting Array

import java.util.ArrayList;
import java.util.List;

public class TargetIndicesAfterSortingArray {
    public List<Integer> targetIndices(int[] nums, int target) {
        int smallerCount = 0;
        int targetCount = 0;

        // Pass 1: Count elements strictly smaller than target and equal to target
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (val < target) {
                smallerCount++;
            } else if (val == target) {
                targetCount++;
            }
        }

        // Pre-allocate List capacity to prevent internal array resizing overhead
        List<Integer> result = new ArrayList<>(targetCount);

        // Pass 2: Directly generate the contiguous index range in O(targetCount) time
        int startIndex = smallerCount;
        for (int i = 0; i < targetCount; i++) {
            result.add(startIndex++);
        }

        return result;
    }
}
