package com.ozzie.advantofcode.common

import com.ozzie.advantofcode.monitoringstation.AnotherPoint
import spock.lang.Specification
import spock.lang.Unroll

class PointTest extends Specification {

    @Unroll
    def "test various distances: #scenario"() {
        setup:
        def thisPoint = new Point(thisX, thisY)
        def otherPoint = new Point(otherX, otherY)

        expect:
        Math.round(thisPoint.distance(otherPoint)) == expectedDistance

        where:
        scenario                        | thisX | thisY | otherX | otherY | expectedDistance
        "straight line over X positive" | 0     | 0     | 10     | 0      | 10
        "straight line over Y positive" | 0     | 0     | 0      | 10     | 10
        "straight line over X negative" | 0     | 0     | -10    | 0      | 10
        "straight line over Y negative" | 0     | 0     | 0      | -10    | 10
        "diagonal line positive"        | 0     | 0     | 10     | 10     | 14
        "diagonal line negative"        | 0     | 0     | -10    | -10    | 14
        "same location"                 | 2     | 3     | 2      | 3      | 0
    }


    @Unroll
    def "distance and degrees test"() {
        setup:
        def anotherPoint = new AnotherPoint(otherX, otherY, new Point(originX, originY))

        expect:
        verifyAll {
            anotherPoint.getDistance() == distance
            anotherPoint.getDegree() == degrees
            anotherPoint.getAngleRadians() == angleRadians
        }

        where:
        originX | originY | otherX | otherY | distance | degrees | angleRadians
        0       | 0       | 1      | 0      | 1.000    | 0.0     | 0
        0       | 0       | 1      | 1      | 1.414    | 45      | 0.785
        0       | 0       | 0      | 1      | 1.000    | 90.0    | 1.571
        0       | 0       | -1     | 1      | 1.414    | 135.0   | 2.356
        0       | 0       | -1     | 0      | 1.000    | 180.0   | 3.142
        0       | 0       | -1     | -1     | 1.414    | 225.0   | -2.356
        0       | 0       | 0      | -1     | 1.000    | 270.0   | -1.571
        0       | 0       | 1      | -1     | 1.414    | 315.0   | -0.785
    }

    def "how does the clock go"() {
        expect:
        new AnotherPoint(8, 2,new Point(8,3)).getAngleRadians() == -1.571
        new AnotherPoint(9, 2,new Point(8,3)).getAngleRadians() == -0.785
        new AnotherPoint(9, 3,new Point(8,3)).getAngleRadians() == 0.000
        new AnotherPoint(9, 4,new Point(8,3)).getAngleRadians() == 0.785
        new AnotherPoint(8, 4,new Point(8,3)).getAngleRadians() == 1.571
        new AnotherPoint(7, 4,new Point(8,3)).getAngleRadians() == 2.356
        new AnotherPoint(7, 3,new Point(8,3)).getAngleRadians() == 3.142
        new AnotherPoint(7, 2,new Point(8,3)).getAngleRadians() == -2.356




    }
}
