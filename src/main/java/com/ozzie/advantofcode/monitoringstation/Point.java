package com.ozzie.advantofcode.monitoringstation;

import java.math.BigDecimal;
import java.math.MathContext;
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

    public boolean isInLineOfSight(Point otherPoint, Point obstacle) {
        if (this.equals(otherPoint) || this.equals(obstacle) || otherPoint.equals(obstacle)) {
            return false;
        }
        BigDecimal distanceThisOtherPoint = distance(otherPoint);
        BigDecimal distanceThisObstacle = distance(obstacle);
        BigDecimal distanceOtherPointObstacle = otherPoint.distance(obstacle);
        return !distanceThisObstacle.add(distanceOtherPointObstacle).equals(distanceThisOtherPoint);
    }

    public BigDecimal distance(final Point otherPoint) {
        return BigDecimal.valueOf(otherPoint.x - x).pow(2).add(BigDecimal.valueOf(otherPoint.y - y).pow(2)).sqrt(MathContext.DECIMAL32);
    }
}
