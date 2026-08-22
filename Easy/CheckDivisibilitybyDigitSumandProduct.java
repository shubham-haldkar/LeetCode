package Easy;

// 3622. Check Divisibility by Digit Sum and Product 

public class CheckDivisibilitybyDigitSumandProduct {
    
    public static void main(String[] args) {
        CheckDivisibilitybyDigitSumandProduct solution = new CheckDivisibilitybyDigitSumandProduct();
        int n = 123;
        boolean result = solution.checkDivisibility(n);
        System.out.println("Is " + n + " divisible by the sum and product of its digits? " + result);
    }

    public boolean checkDivisibility(int n) {
        int sum = n % 10;
        int product = n % 10;
        int c = n/10;
        while(c > 0) {
            sum += c % 10;
            product *= c % 10;
            c /= 10;
        }
        return n%(sum+product) == 0;
    }
 
}
