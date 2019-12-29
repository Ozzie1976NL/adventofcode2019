package com.ozzie.advantofcode.moons;

import java.util.Arrays;

public class Util {

    /**
     * See https://en.wikipedia.org/wiki/Least_common_multiple, https://www.geeksforgeeks.org/lcm-of-given-array-elements/
     *
     * @param numbers array of numbers of which to determine the least common multiple
     * @return the lest common multiple of the given numbers
     */
    public static long lcm(long... numbers) {
        long answer = numbers[0];
        for(int i = 0; i < numbers.length; i ++) {
            answer = (((numbers[i] * answer)) /
                    (gcd(numbers[i], answer)));
        }
        return answer;
    }

    // See https://www.geeksforgeeks.org/lcm-of-given-array-elements/
    public static long gcd(long a, long b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    /**
     * Returns true when all the numbers in the array are the same
     * @param numbers array with numbers to check
     * @return true when all numbers are equal.
     */
    private static boolean allTheSameValue(long[] numbers) {
        final long firstValue = numbers[0];
        for (long number : numbers) {
            if (number != firstValue) {
                return false;
            }
        }
        return true;
    }
}
