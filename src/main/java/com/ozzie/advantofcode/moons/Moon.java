package com.ozzie.advantofcode.moons;

public class Moon {
    private Point3d position;
    private Point3d velocity;

    public Moon(int x, int y, int z) {
        this.position = new Point3d(x, y, z);
        this.velocity = new Point3d(0,0,0);
    }

    public void applyGravity(Moon otherMoon) {
        velocity = new Point3d(
                velocity.getX() - compareAxis(position.getX(), otherMoon.getPosition().getX()),
                velocity.getY() - compareAxis(position.getY(), otherMoon.getPosition().getY()),
                velocity.getZ() - compareAxis(position.getZ(), otherMoon.getPosition().getZ()));
    }

    public void applyVelocity() {
        position = position.add(velocity);
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
}
