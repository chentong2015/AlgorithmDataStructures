package others.math_theory;

import java.util.HashSet;
import java.util.Set;

// Distinct Prime Factors of Product of Array
// Given an array of positive integers nums
// return the number of distinct prime factors in the product of the elements of nums.
//
// A number greater than 1 is called prime if it is divisible by only 1 and itself.
// An integer val1 is a factor of another integer val2 if val2 / val1 is an integer.
//
// 一千以内的质数范围是有限的
// 1 <= nums.length <= 10000
// 2 <= nums[i] <= 1000
public class DistinctPrimeFactors {

    // TODO. 只统计构成所有数据的不同质子的数目，和每个因子个数无关
    // 2,4,3,7,10,6 -> 4
    // 2*2*2*3*7*2*5*2*3 = 2^5 * 3^2 * 5 * 7
    //
    // 2,4,8,16 -> 1
    // 2*4*8*16 = 2^(1+2+3+4) = 2^10
    //
    // 2 3 5 7 11 13 17 19
    // 1 1 1 2
    public int distinctPrimeFactors(int[] nums) {
        // TODO. 将指定范围内的所有质数取出来，用于后续循环判断
        Set<Integer> setPrimes = new HashSet<>();
        for (int index = 2; index < 1000; index++) {
            if (isPrime(index)) {
                setPrimes.add(index);
            }
        }
        // TODO. 默认Num是有质数的乘积构成，只判断质数是否是Num的因子
        Set<Integer> setFactors = new HashSet<>();
        for (int num: nums) {
            for (int prime: setPrimes) {
                if (num % prime == 0) {
                    setFactors.add(prime);
                }
            }
        }
        return setFactors.size();
    }

    public boolean isPrime(int digit) {
        if (digit == 1) {
            return false;
        }
        for (int i = 2; i <= (long) Math.sqrt(digit); i++) {
            if (digit % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 最优时间复杂度解法
    public int distinctPrimeFactorsMaster(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            int n = num;

            // 先将2因子排除
            boolean isTwo = false;
            while (n % 2 == 0) {
                isTwo = true;
                n /= 2;
            }
            if(isTwo) {
                set.add(2);
            }

            // 再查找其他可以整除的因子 3,5,7...
            for(int i = 3; i <= Math.sqrt(n); i += 2){
                while(n % i == 0) {
                    set.add(i);
                    n /= i;
                }
            }

            // 最后剩余的n为质数
            if(n > 2) {
                set.add(n);
            }
        }
        return set.size();
    }
}
