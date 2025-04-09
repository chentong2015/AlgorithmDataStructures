package math_theory;

// Count Good Numbers
// A digit string is good if
// - the digits (0-indexed) at even indices are even
// - the digits at odd indices are prime (2, 3, 5, or 7).
//
// "2582" is good
// the digits (2 and 8) at even positions are even
// the digits (5 and 2) at odd positions are prime.
//
// "3245" is not good
// 3 is at an even index but is not even.
//
// Given an integer n, return the total number of good digit strings of length n
// Since the answer may be large, return it modulo 10^9 + 7.
//
// A digit string is a string consisting of digits 0 through 9 that may contain leading zeros.
// 1 <= n <= 10^15
public class CountGoodNumbers {

    // TODO. 二进制指数运算的问题
    // n = 1 -> 5
    // The good numbers of length 1 are "0", "2", "4", "6", "8".
    //
    // n = 2 -> 5 * 4 -> 20
    // n = 3 -> 5 * 4 * 5 -> 100
    // n = 4 -> 5 * 4 * 5 * 4 -> 400
    // n = 10 -> 3200000
    // n = 50 -> 564908303
    // n = 100
    // n = 1000

    public long MOD = 1_000_000_007;

    public int countGoodNumbers(long n) {
        // Calculate the number of even and odd positions
        long odd = n/2;
        long even = (n+1)/2;

        // Calculate the total number of good digit strings
        // multiplying 5 by even because there could only be 5 even numbers between 0-9
        // multiplying 4 by odd because there could only be 4 prime numbers between 0-9
        long result = pow(5, even) * pow(4,odd);
        return (int)(result % MOD);
    }

    // TODO. 手写Pow指数级运算结果，次方级别
    public long pow(long x, long n){
        if(n==0) {
            return 1;
        }
        // Recursively calculate x^(n/2)
        long temp = pow(x,n/2);
        if (n%2 == 0){
            // If n is even, return (x^(n/2))^2
            return (temp * temp)% MOD;
        } else {
            // If n is odd, return (x^(n/2))^2 * x
            return (x * temp * temp)% MOD;
        }
    }
}
