package com.ozzie.advantofcode.day4;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecureContainerTests {

    @Test
    public void answerDay4Puzzle2() {
        assertEquals(1104, SecureContainer.noValidPinCodesFor(IntStream.range(183564, 657474)));
    }

    @Test
    public void possiblePinCombinations() {
        assertEquals(2, SecureContainer.noValidPinCodesFor(IntStream.of(
                3214444, // No double digits
                111111, // No double digits
                123456, // No two adjacent double digits
                223333,
                122333)));
    }

    @Test
    public void neverDecreasingDigits() {
        assertAll("neverDecreasingDigits",
                () -> assertTrue(SecureContainer.neverDecreasingDigits(new Integer[]{1, 2, 3, 3, 4, 6})),
                () -> assertTrue(SecureContainer.neverDecreasingDigits(new Integer[]{1, 1, 1, 1, 1, 1})),
                () -> assertFalse(SecureContainer.neverDecreasingDigits(new Integer[]{1, 2, 3, 4, 5, 4})),
                () -> assertFalse(SecureContainer.neverDecreasingDigits(new Integer[]{1, 2, 3, 2, 3, 4})),
                () -> assertFalse(SecureContainer.neverDecreasingDigits(new Integer[]{2, 1, 2, 3, 4, 5})),
                () -> assertFalse(SecureContainer.neverDecreasingDigits(new Integer[]{6, 5, 4, 3, 2, 1})),
                () -> assertFalse(SecureContainer.neverDecreasingDigits(new Integer[]{2, 2, 3, 4, 5, 0}))
        );
    }

    @Test
    public void minimumTwoAdjacentDoubleDigits() {
        assertAll("minimumTwoAdjacentDoubleDigits",
                () -> assertTrue(SecureContainer.minimumTwoAdjacentDoubleDigits(new Integer[]{1, 2, 3, 3, 4, 5})),
                () -> assertTrue(SecureContainer.minimumTwoAdjacentDoubleDigits(new Integer[]{1, 2, 3, 4, 5, 5})),
                () -> assertTrue(SecureContainer.minimumTwoAdjacentDoubleDigits(new Integer[]{1, 1, 2, 3, 4, 5})),
                () -> assertFalse(SecureContainer.minimumTwoAdjacentDoubleDigits(new Integer[]{1, 1, 1, 1, 1, 1})),
                () -> assertTrue(SecureContainer.minimumTwoAdjacentDoubleDigits(new Integer[]{1, 1, 1, 1, 2, 2})),
                () -> assertTrue(SecureContainer.minimumTwoAdjacentDoubleDigits(new Integer[]{2, 2, 3, 3, 3, 3})),
                () -> assertFalse(SecureContainer.minimumTwoAdjacentDoubleDigits(new Integer[]{1, 2, 3, 4, 4, 4})),
                () -> assertFalse(SecureContainer.minimumTwoAdjacentDoubleDigits(new Integer[]{1, 2, 3, 4, 5, 6})),
                () -> assertFalse(SecureContainer.minimumTwoAdjacentDoubleDigits(new Integer[]{1, 2, 3, 7, 8, 9}))
        );
    }
}