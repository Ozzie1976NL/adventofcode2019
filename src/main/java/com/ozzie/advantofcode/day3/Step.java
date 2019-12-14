package com.ozzie.advantofcode.day3;

public class Step {

    private final Direction direction;
    private final Integer   steps;

    public Step(String cmd) {
        direction = Direction.of(cmd.substring(0,1));
        steps = Integer.valueOf(cmd.substring(1));
    }

    public Direction getDirection() {
        return direction;
    }

    public Integer getSteps() {
        return steps;
    }
}
