// Smallest Missing Integer Greater Than Sequential Prefix Sum (LeetCode 2996)

package Easy ;

public class MissingInteger {


    public int missingInteger(int[] nums) {

        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        int x = sum;

        while (true) {
            boolean found = false;

            for (int num : nums) {
                if (num == x) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return x;
            }

            x++;
        }
    }
}