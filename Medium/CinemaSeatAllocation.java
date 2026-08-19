package Medium ;

// 1386. Cinema Seat Allocation


import java.util.HashMap;
import java.util.Map;


public class CinemaSeatAllocation{
    public static void main(String[] args) {
        CinemaSeatAllocation solution = new CinemaSeatAllocation();
        int n = 3;
        int[][] reservedSeats = {{1, 2}, {1, 3}, {1, 8}, {2, 6}, {3, 1}, {3, 10}};
        System.out.println(solution.maxNumberOfFamilies(n, reservedSeats)); // Output: 4
    }

    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMasks = new HashMap<>();

        // Map reserved seats to row bitmasks
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << col));
        }

        // Each completely empty row can accommodate 2 families
        int maxFamilies = (n - rowMasks.size()) * 2;

        // Pre-defined bitmasks for valid 4-seat blocks
        int left = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);   // 60
        int right = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);  // 960
        int middle = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7); // 240

        for (int mask : rowMasks.values()) {
            boolean canLeft = (mask & left) == 0;
            boolean canRight = (mask & right) == 0;

            if (canLeft && canRight) {
                maxFamilies += 2;
            } else if (canLeft || canRight || (mask & middle) == 0) {
                maxFamilies += 1;
            }
        }

        return maxFamilies;
    }
}