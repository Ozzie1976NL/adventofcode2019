package com.ozzie.advantofcode.monitoringstation

import spock.lang.Specification

class AsteroidMapTest extends Specification {

    def "Day 10: Monitoring station example 1"() {
        setup:
        def stringMap =
                ".#..#\n" +
                ".....\n" +
                "#####\n" +
                "....#\n" +
                "...##"

        when:
        def asteroidMap = new AsteroidMap(stringMap)

        then:
        assert true
    }
}
