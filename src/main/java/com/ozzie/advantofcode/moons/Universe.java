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
//        System.out.println("Initial values:");
        for (Moon moon : moons) {
            System.out.println(moon.toString());
        }
        for (int i = 0; i < noSteps; i++) {
//            System.out.println();
//            System.out.println(String.format("Step %d ", i + 1));
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
//        System.out.println("Velocity updated:");
//        for (Moon moon : moons) {
//            System.out.println(moon.getVelocity().toString());
//        }

        // Update Position
        for (Moon moon : moons) {
            moon.applyVelocity();
        }
//        System.out.println("Position updated:");
//        for (Moon moon : moons) {
//            System.out.println(moon.getPosition().toString());
//        }
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
        final long multiples[] = new long[Moon.Plane.values().length];
        for (Moon.Plane plane : Moon.Plane.values()) {
            final Moon[] currentMoons = copy(moons);
            int step = 0;
            do {
                step ++;
                for (Moon moon : currentMoons) {
                    for (Moon otherMoon : currentMoons) {
                        if (moon != otherMoon) {
                            moon.applyGravity(otherMoon, plane);
                        }
                    }
                }
                for (Moon moon : currentMoons) {
                    moon.applyVelocity(plane);
                }
            } while (!compare(moons, currentMoons, plane));
            multiples[plane.ordinal()] = step;
        }
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

    private static boolean compare(Moon[] initial, Moon[] current, Moon.Plane plane) {
        for (int i = 0; i < initial.length; i++) {
            long initialValue, currentValue;
            switch (plane) {
                case X_AXIS:
                    initialValue = initial[i].getPosition().getX();
                    currentValue = current[i].getPosition().getX();
                    break;
                case Y_AXIS:
                    initialValue = initial[i].getPosition().getY();
                    currentValue = current[i].getPosition().getY();
                    break;
                case Z_AXIS:
                    initialValue = initial[i].getPosition().getZ();
                    currentValue = current[i].getPosition().getZ();
                    break;
                default:
                    throw new IllegalArgumentException("Oh oh, this should not happen!");
            }
            if (currentValue != initialValue) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return Arrays.stream(moons).map(Moon::toString).reduce((a, b) -> a + b).orElse("empty");
    }
}
