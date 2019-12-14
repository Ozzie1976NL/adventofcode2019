package com.ozzie.advantofcode.day4;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class SecureContainer {


    public static long noValidPinCodesFor(IntStream intStream) {
        return intStream.boxed()
                .map(SecureContainer::toDigits)
                .filter(SecureContainer::neverDecreasingDigits)
                .filter(SecureContainer::minimumTwoAdjacentDoubleDigits)
                .count();
    }

    public static boolean neverDecreasingDigits(Integer[] digits) {
        if(digits.length < 6) {
            throw new IllegalArgumentException("Minumum of 6 digits expected!");
        }
        for(int i = 1; i < digits.length; i++) {
            if(digits[i-1] >digits[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean minimumTwoAdjacentDoubleDigits(Integer[] digits) {
        if(digits.length < 6) {
            throw new IllegalArgumentException("Minumum of 6 digits expected!");
        }
        boolean doubleDigitFound = false;
        int lastDoubleDigit = -1;
        for(int i = 1; i < digits.length; i++) {
            if(digits[i-1].equals(digits[i])) {
                if(digits[i].equals(lastDoubleDigit)) {
                    doubleDigitFound = false;
                } else {
                    doubleDigitFound = true;
                }
                lastDoubleDigit = digits[i];
            } else {
                if(doubleDigitFound) {
                    return true;
                }
            }
        }
        return doubleDigitFound;
    }

    public static Integer[] toDigits(int pinCode) {
        List<Integer> digits = new LinkedList<>();
        for(char c : String.valueOf(pinCode).toCharArray())
        {
            digits.add(Integer.parseInt(String.valueOf(c)));
        }
        return digits.toArray(new Integer[6]);
    }
}
