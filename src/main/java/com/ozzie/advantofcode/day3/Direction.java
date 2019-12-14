package com.ozzie.advantofcode.day3;

public enum Direction {
    LEFT("L"),
    RIGHT("R"),
    UP("U"),
    DOWN("D");

    private final String letter;

    Direction(String letter) {
        this.letter = letter;
    }

    public static Direction of(String letter) {
        switch (letter) {
            case "R":
                return RIGHT;
            case "L":
                return LEFT;
            case "U":
                return UP;
            case "D":
                return DOWN;
            default:
                return null;
        }
    }
}
