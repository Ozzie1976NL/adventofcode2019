package com.ozzie.advantofcode.monitoringstation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AnotherPoint extends Point {
    private final BigDecimal degree;
    private final BigDecimal distance;
    private final Point origin;

    public AnotherPoint(int x, int y, Point origin) {
        super(x, y);
        this.degree = origin.degrees(this);
        this.distance = origin.distance(this);
        this.origin = origin;
    }

    public BigDecimal getDegree() {
        return degree;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public Point getOrigin() {
        return origin;
    }
}
