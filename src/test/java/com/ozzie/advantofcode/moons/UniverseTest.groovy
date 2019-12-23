package com.ozzie.advantofcode.moons

import spock.lang.Ignore
import spock.lang.Specification

class UniverseTest extends Specification {
    def "Day 12 N-Body problem example 1"() {
        setup:
        def universe = new Universe(List.of(
                new Moon(-1, 0, 2),
                new Moon(2, -10, -7),
                new Moon(4, -8, 8),
                new Moon(3, 5, -1)))

        when:
        universe.step(10)

        then:
        universe.totalEnergy() == 179
    }

    def "Day 12 N-Body problem example 2"() {
        setup:
        def universe = new Universe(List.of(
                new Moon(-8, -10, 0),
                new Moon(5, 5, 10),
                new Moon(2, -7, 3),
                new Moon(9, -8, -3)))

        when:
        universe.step(100)

        then:
        universe.totalEnergy() == 1940
    }

    def "Day 12 N-Body problem puzzle 1"() {
        setup:
        def universe = new Universe(List.of(
                new Moon(13, 9, 5),
                new Moon(8, 14, -2),
                new Moon(-5, 4, 11),
                new Moon(2, -6, 1)))

        when:
        universe.step(1000)

        then:
        universe.totalEnergy() == 6490
    }

    def "Day 12 N-Body problem no steps for duplicates example 1"() {
        setup:
        def universe = new Universe(List.of(
                new Moon(-1, 0, 2),
                new Moon(2, -10, -7),
                new Moon(4, -8, 8),
                new Moon(3, 5, -1)))

        when:
        def noSteps = universe.firstDuplicateSteps()

        then:
        noSteps == 2772
    }

    @Ignore
    def "Day 12 N-Body problem no steps for duplicates example 2"() {
        setup:
        def universe = new Universe(List.of(
                new Moon(-8, -10, 0),
                new Moon(5, 5, 10),
                new Moon(2, -7, 3),
                new Moon(9, -8, -3)))

        when:
        def noSteps = universe.firstDuplicateSteps()

        then:
        noSteps == 4686774924
    }

    def "Day 12 N-Body problem no steps for duplicates puzzle 2"() {
        setup:
        def universe = new Universe(List.of(
                new Moon(13, 9, 5),
                new Moon(8, 14, -2),
                new Moon(-5, 4, 11),
                new Moon(2, -6, 1)))

        when:
        def noSteps = universe.firstDuplicateSteps()

        then:
        noSteps == 4686774924
    }


}
