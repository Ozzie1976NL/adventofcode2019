package com.ozzie.advantofcode.moons;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class Universe {

    private final Moon[] moons;

    public Universe(List<Moon> moons) {
        this.moons = moons.toArray(new Moon[0]);
    }

    public void step(int noSteps) {
        for (Moon moon : moons) {
            System.out.println(moon.toString());
        }
        for (int i = 0; i < noSteps; i++) {
            step();
        }
    }

    public void step() {

        // Update Velocity by applying gravity
        for (Moon moon : moons) {
            for (Moon otherMoon : moons) {
                if (moon != otherMoon) {
                    moon.applyGravity(otherMoon);
                }
            }
        }

        // Update Position
        for (Moon moon : moons) {
            moon.applyVelocity();
        }
    }

    public int totalEnergy() {
        return Arrays.stream(moons).map(Moon::totalEnergy).mapToInt(Integer::intValue).sum();
    }

    public long firstDuplicateStepsBruteForce() {
        final Moon[] initialState = copy(moons);
        Instant start = Instant.now();
        long i = 0;
        do {
            step();
            i++;
            if (i % 1_000_000_000 == 0) {
                Instant now = Instant.now();
                long durationSeconds = now.getEpochSecond() - start.getEpochSecond();
                long averageStepsPerSecond = i / (durationSeconds == 0 ? 1 : durationSeconds);
                System.out.println(String.format("Already processed %d steps, working at it for %d minutes, average %d steps per second", i, durationSeconds / 60, averageStepsPerSecond));
                System.out.println(Universe.this.toString());

            }
        } while (!compare(initialState, moons));
        System.out.println(String.format("%50d", i));
        Instant now = Instant.now();

        long durationSeconds = now.getEpochSecond() - start.getEpochSecond();
        long averageStepsPerSecond = i / (durationSeconds == 0 ? 1 : durationSeconds);
        System.out.println(String.format("Took %d minutes for %d steps at an average of %d per second", durationSeconds / 60, i, averageStepsPerSecond));
        System.out.println(toString());
        return i;
    }

    public long firstDuplicateStepLCM() {
        final long multiples[] = new long[Axis.values().length];
        final Moon[] start = copy(moons);
        int stepNo = 0;
        do {
            stepNo++;
            step();
            for (Axis axis : Axis.values()) {
                if (multiples[axis.ordinal()] == 0 && allValuesEqualOnAxis(start, moons, axis)) {
                    multiples[axis.ordinal()] = stepNo;
                    System.out.println(String.format("All moons reach their initial position after %d steps on Axis %s", stepNo, axis.toString()));
                }
            }
        } while (Arrays.stream(multiples).filter(m -> m > 0).count() != Axis.values().length);

        return Util.lcm(multiples);
    }

    private static Moon[] copy(Moon[] moons) {
        final Moon[] initialState = new Moon[moons.length];
        for (int i = 0; i < moons.length; i++) {
            initialState[i] = moons[i].copy();
        }
        return initialState;
    }

    private static boolean compare(Moon[] initial, Moon[] current) {
        for (int i = 0; i < initial.length; i++) {
            if (!initial[i].equals(current[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean allValuesEqualOnAxis(Moon[] aMoons, Moon[] bMoons, Axis axis) {
        for (int i = 0; i < aMoons.length; i++) {
            if (!allValuesEqualOnAxis(aMoons[i], bMoons[i], axis)) {
                return false;
            }
        }
        return true;
    }

    private static boolean allValuesEqualOnAxis(Moon moonA, Moon moonB, Axis axis) {
        return allValuesEqualOnAxis(moonA.getPosition(), moonB.getPosition(), axis) &&
                allValuesEqualOnAxis(moonA.getVelocity(), moonB.getVelocity(), axis);
    }

    private static boolean allValuesEqualOnAxis(Point3d pointA, Point3d pointB, Axis axis) {
        int a;
        int b;
        switch (axis) {
            case X:
                a = pointA.getX();
                b = pointB.getX();
                break;
            case Y:
                a = pointA.getY();
                b = pointB.getY();
                break;
            case Z:
                a = pointA.getZ();
                b = pointB.getZ();
                break;
            default:
                throw new IllegalArgumentException("Unknown back to the future axis encountered!");
        }
        return a == b;
    }

    public String toString() {
        return Arrays.stream(moons).map(Moon::toString).reduce((a, b) -> a + b).orElse("empty");
    }
}
