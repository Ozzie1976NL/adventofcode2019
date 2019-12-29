package com.ozzie.advantofcode.moons

import spock.lang.Specification

class UtilTest extends Specification {

    def "various lcm tests"() {
        expect:
        Util.lcm(4L, 6L) == 12
        Util.lcm(8L, 9L, 21L) == 504
        Util.lcm(268296L, 161428L, 102356L) == 277068010964808
    }

    def "various gcd tests"() {
        expect:
        Util.gcd(8, 12) == 4
    }
}
