package Easy ;

// 3471. Find the Largest Almost Missing Integer

public class LargestMissingInteger {

    public static void main(String[] args) {
        LargestMissingInteger solution = new LargestMissingInteger();
        int[] nums = {3, 2, 1, 5, 4};
        int k = 2;
        System.out.println(solution.largestInteger(nums, k));
        
    }
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        if (n == k) {
            int res = nums[0];
            for (int x : nums) {
                res = Math.max(res, x);
            }
            return res;
        }
        int[] count = new int[51];
        for (int x : nums) {
            count[x]++;
        }
        if (k == 1) {
            for (int i = 50; i >= 0; --i) {
                if (count[i] == 1) {
                    return i;
                }
            }
            return -1;
        }
        int res = -1;
        if (count[nums[0]] == 1) {
            res = Math.max(res, nums[0]);
        }
        if (count[nums[n - 1]] == 1) {
            res = Math.max(res, nums[n - 1]);
        }
        return res;
    }
}