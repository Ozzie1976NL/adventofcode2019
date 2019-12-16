package com.ozzie.advantofcode.monitoringstation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public BigDecimal atan2(final Point otherPoint) {
        return BigDecimal.valueOf(Math.atan2(otherPoint.y - (double)y, otherPoint.x - (double)x)).setScale(3, RoundingMode.HALF_UP);
    }

    public BigDecimal degrees(final Point otherPoint) {
        double degrees = Math.toDegrees(atan2(otherPoint).doubleValue());
        if(degrees < 0) {
            degrees += 360;
        }
        return BigDecimal.valueOf(degrees).setScale(1, RoundingMode.HALF_UP);
    }

    public BigDecimal distance(final Point otherPoint) {
        return BigDecimal.valueOf(Math.sqrt(Math.pow(otherPoint.getX() - (double)x,2) + Math.pow(otherPoint.getY() - (double)y,2))).setScale(3, RoundingMode.HALF_UP);
    }
}
