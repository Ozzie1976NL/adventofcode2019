package com.ozzie.advantofcode.moons;

import java.util.Objects;

public class Moon {
    private Point3d position;
    private Point3d velocity;

    public Moon(int x, int y, int z) {
        this.position = new Point3d(x, y, z);
        this.velocity = new Point3d(0, 0, 0);
    }

    private Moon(final Point3d position, final Point3d velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public void applyGravity(final Moon otherMoon) {
        velocity = new Point3d(
                velocity.getX() - compareAxis(position.getX(), otherMoon.getPosition().getX()),
                velocity.getY() - compareAxis(position.getY(), otherMoon.getPosition().getY()),
                velocity.getZ() - compareAxis(position.getZ(), otherMoon.getPosition().getZ()));
    }

    public void applyGravity(final Moon otherMoon, final Plane plane) {
        switch (plane) {
            case X_AXIS:
                velocity = new Point3d(
                        velocity.getX() - compareAxis(position.getX(), otherMoon.getPosition().getX()),
                        velocity.getY(),
                        velocity.getZ());
                break;
            case Y_AXIS:
                velocity = new Point3d(
                        velocity.getX(),
                        velocity.getY() - compareAxis(position.getY(), otherMoon.getPosition().getY()),
                        velocity.getZ());
                break;
            case Z_AXIS:
                velocity = new Point3d(
                        velocity.getX(),
                        velocity.getY(),
                        velocity.getZ() - compareAxis(position.getZ(), otherMoon.getPosition().getZ()));
                break;
        }
    }

    public void applyVelocity() {
        position = position.add(velocity);
    }

    public void applyVelocity(final Plane plane) {
        switch (plane) {
            case X_AXIS:
                position = position.addX(velocity.getX());
                break;
            case Y_AXIS:
                position = position.addY(velocity.getY());
                break;
            case Z_AXIS:
                position = position.addZ(velocity.getZ());
                break;
        }
    }

    private static int compareAxis(int cur, int other) {
        return Integer.compare(cur, other);
    }

    public Point3d getPosition() {
        return position;
    }

    public Point3d getVelocity() {
        return velocity;
    }

    public int totalEnergy() {
        return position.absXYZ() * velocity.absXYZ();
    }

    @Override
    public String toString() {
        return "Moon{" +
                "position=" + position +
                ", velocity=" + velocity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moon moon = (Moon) o;
        return position.equals(moon.position) &&
                velocity.equals(moon.velocity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, velocity);
    }


    public Moon copy() {
        return new Moon(position.copy(), velocity.copy());
    }

    public enum Plane {
        X_AXIS,
        Y_AXIS,
        Z_AXIS
    }
}
