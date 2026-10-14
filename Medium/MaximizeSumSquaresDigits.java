
// 3723. Maximize Sum of Squares of Digits

class MaximizeSumSquaresDigits {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > num * 9){ 
            return "";
        }
        int q = sum / 9, r = sum % 9;
        StringBuilder sb = new StringBuilder().repeat('9', q);
        if (r > 0){
            sb.append(r);
        }
        sb.repeat('0', num - sb.length());
        return sb.toString();
    }
}
