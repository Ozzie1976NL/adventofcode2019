package com.ozzie.advantofcode.moons;

import java.util.Objects;

public class Point3d {
    private final int x;
    private final  int y;
    private final int z;

    public Point3d(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3d add(Point3d other) {
        return new Point3d(x + other.x, y + other.y, z + other.z);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int absXYZ() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    @Override
    public String toString() {
        return "Point3d{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3d point3d = (Point3d) o;
        return x == point3d.x &&
                y == point3d.y &&
                z == point3d.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
