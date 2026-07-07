class Solution {
    public long sumAndMultiply(int n) 
    {
        if (n == 0) return 0;
        int rev = 0;
        while (n > 0) {
            rev = rev * 10 + (n % 10);
            n /= 10;
        }
        long x = 0;
        int sum = 0;
        while (rev > 0) {
            int digit = rev % 10;
            rev /= 10;
            if (digit != 0) {
                x = x * 10 + digit;
                sum += digit;
            }
        }
        return x * sum;
    }
}