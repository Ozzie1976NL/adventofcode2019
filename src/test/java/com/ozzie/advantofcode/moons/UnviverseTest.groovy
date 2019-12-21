package com.ozzie.advantofcode.moons

import spock.lang.Specification

class UnviverseTest extends Specification {
    def "Day 12 N-Body problem example 1"() {
        setup:
        def universe = new Unviverse(List.of(new Moon(-1, 0, 2), new Moon(2, -10, -7), new Moon(4, -8, 8), new Moon(3, 5, -1)))

        when:
        universe.step(10)

        then:
        universe.totalEnergy() == 179
    }
}
