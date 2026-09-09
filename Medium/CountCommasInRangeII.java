3871. Count Commas in Range II

public class CountCommasInRangeII {
    public long countCommas(long n) {
        if (n < 1000) {
            return 0L;
        }

        long totalCommas = 0L;
        long threshold = 1000L;

        while (n >= threshold) {
            // Accumulate numbers in range [threshold, n] crossing this comma boundary
            totalCommas += (n - threshold + 1L);

            // Prevent Long overflow prior to 1000x multiplication
            if (threshold > Long.MAX_VALUE / 1000L) {
                break;
            }
            threshold *= 1000L;
        }

        return totalCommas;
    }
}
