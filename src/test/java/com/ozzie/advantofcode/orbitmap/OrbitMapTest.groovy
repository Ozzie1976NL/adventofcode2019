package com.ozzie.advantofcode.orbitmap

import spock.lang.Specification

class OrbitMapTest extends Specification {

    def "dummy test"() {
        setup:
        List<String> orbitMapData = [
            "COM)B","B)C","C)D","D)E","E)F","B)G","G)H","D)I","E)J","J)K","K)L"
        ]

        when:
        def orbitMap = new OrbitMap(orbitMapData)

        then:
        orbitMap.noOrbits() == 42
    }

    def "day 6 part 1"() {
        setup:
        def orbitMapData = this.getClass().getResourceAsStream("/day6_input.txt").readLines();

        when:
        def orbitMap = new OrbitMap(orbitMapData)

        then:
        orbitMap.noOrbits() == 278744
    }

    def "day 6 part 2 example"() {
        setup:
        def orbitMapData = this.getClass().getResourceAsStream("/day6_part_2_example.txt").readLines();

        when:
        def orbitMap = new OrbitMap(orbitMapData)

        then:
        orbitMap.noOrbitTransfers("YOU", "SAN") == 4
    }

    def "day 6 part 2 final"() {
        setup:
        def orbitMapData = this.getClass().getResourceAsStream("/day6_input.txt").readLines();

        when:
        def orbitMap = new OrbitMap(orbitMapData)

        then:
        orbitMap.noOrbitTransfers("YOU", "SAN") == 475
    }

}
