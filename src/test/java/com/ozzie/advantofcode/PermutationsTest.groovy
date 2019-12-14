package com.ozzie.advantofcode

import spock.lang.Specification

class PermutationsTest extends Specification {
    def "test number of permutations"() {
        setup:
        long[] input = [0,1,2,3,4]

        when:
        def result = Permutations.determinePermutations(input)

        then:
        result.size() == 120
    }
}
