package com.ozzie.advantofcode.moons;

import java.util.List;

public class Universe {

    private final List<Moon> moons;

    public Universe(List<Moon> moons) {
        this.moons = moons;
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
                moon.applyGravity(otherMoon);
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
        return moons.stream().map(Moon::totalEnergy).mapToInt(Integer::intValue).sum();
    }

    public long firstDuplicateSteps() {
        String start = toString();
        long i = 0;
        do {
            step();
            i++;
            if(i % 100_000_000 == 0) {
                System.out.println(String.format("%50d",i));
                System.out.println(Universe.this.toString());
            }
        } while (!start.equals(toString()));
        System.out.println(String.format("%50d",i));
        System.out.println(toString());
        return i;
    }

    public String toString() {
        return moons.stream().map(Moon::toString).reduce((a,b) -> a + b).orElse("empty");
    }
}
