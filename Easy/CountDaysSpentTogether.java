
// 2409. Count Days Spent Together

public class CountDaysSpentTogether {
    // Non-leap year days per month
    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int aliceStart = parseDayOfYear(arriveAlice);
        int aliceEnd = parseDayOfYear(leaveAlice);
        int bobStart = parseDayOfYear(arriveBob);
        int bobEnd = parseDayOfYear(leaveBob);

        // Find the overlapping arrival and departure day-of-year indices
        int start = Math.max(aliceStart, bobStart);
        int end = Math.min(aliceEnd, bobEnd);

        // Calculate overlap duration
        return Math.max(0, end - start + 1);
    }

    // Fast O(1) date parser avoiding string splits and Integer.parseInt()
    private int parseDayOfYear(String date) {
        int month = (date.charAt(0) - '0')  *10 + (date.charAt(1) - '0');
        int day = (date.charAt(3) - '0') * 10 + (date.charAt(4) - '0');

        int dayOfYear = day;
        for (int i = 0; i < month - 1; i++) {
            dayOfYear += DAYS_IN_MONTH[i];
        }

        return dayOfYear;
    }
}
