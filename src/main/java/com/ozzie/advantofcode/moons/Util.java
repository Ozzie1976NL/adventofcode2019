package com.ozzie.advantofcode.moons;

import java.util.Arrays;

public class Util {

    /**
     * See https://en.wikipedia.org/wiki/Least_common_multiple
     *
     * @param numbers array of numbers of which to determine the least common multiple
     * @return the lest common multiple of the given numbers
     */
    public static long lcm(long... numbers) {
        long[] multiples = Arrays.copyOf(numbers, numbers.length);

        long lowest = 0;
        do {
            for(int i = 0; i < numbers.length; i++) {
                if(lowest < multiples[i]) {
                    lowest = multiples[i];
                }
                if(multiples[i] < lowest) {
                    multiples[i] += numbers[i];
                }
            }
        } while (!allTheSameValue(multiples));
        return lowest;
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
