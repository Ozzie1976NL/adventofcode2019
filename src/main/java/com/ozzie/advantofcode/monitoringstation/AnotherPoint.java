package com.ozzie.advantofcode.monitoringstation;

import com.ozzie.advantofcode.common.Point;

import java.math.BigDecimal;
import java.util.Objects;

public class AnotherPoint extends Point {
    private final BigDecimal degree;
    private final BigDecimal distance;
    private final BigDecimal angleRadians;
    private final Point      origin;

    public AnotherPoint(final Point other, final Point origin) {
        this(other.getX(), other.getY(), origin);
    }

    public AnotherPoint(int x, int y, final Point origin) {
        super(x, y);
        this.degree = origin.degrees(this);
        this.distance = origin.distance(this);
        this.angleRadians = origin.angleRadians(this);
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

    public BigDecimal getAngleRadians() {
        return angleRadians;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AnotherPoint that = (AnotherPoint) o;
        return getDegree().equals(that.getDegree()) &&
                getDistance().equals(that.getDistance()) &&
                getAngleRadians().equals(that.getAngleRadians()) &&
                getOrigin().equals(that.getOrigin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDegree(), getDistance(), getAngleRadians(), getOrigin());
    }
}
