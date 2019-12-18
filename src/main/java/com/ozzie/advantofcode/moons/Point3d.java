package com.ozzie.advantofcode.moons;

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

    public Point3d applyAsGravity(Point3d other) {
        return new Point3d()
    }

    private static int applyGravity(int a, int b) {
        if(a == b) {
            return a;
        } else if()
    }
}
