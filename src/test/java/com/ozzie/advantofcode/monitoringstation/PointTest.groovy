package com.ozzie.advantofcode.monitoringstation

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
    def "test various situations whether two points and an obstacle are in sight: #scenario"() {
        setup:
        def thisPoint = new Point(thisX, thisY)
        def otherPoint = new Point(otherX, otherY)
        def obstacle = new Point(obstacleX, obstacleY)

        expect:
        thisPoint.isInLineOfSight(otherPoint, obstacle) == isInLineOfSight

        where:
        scenario                                  | thisX | thisY | otherX | otherY | obstacleX | obstacleY | isInLineOfSight
        "obstacle is in between on x axis"        | 0     | 0     | 10     | 0      | 8         | 0         | false
        "obstacle is in on other side on x axis"  | 0     | 0     | 10     | 0      | -8        | 0         | true
        "obstacle is after other point on x axis" | 0     | 0     | 4      | 0      | 8         | 0         | true

        "obstacle is in between on y axis"        | 0     | 0     | 0      | 10     | 0         | 8         | false
        "obstacle is in on other side on y axis"  | 0     | 0     | 0      | 10     | 0         | -8        | true
        "obstacle is after other point on y axis" | 0     | 0     | 0      | 4      | 0         | 8         | true

        "obstacle is the same as other"           | 1     | 1     | 2      | 2      | 2         | 2         | false

        "from example"                            | 3     | 4     | 1      | 0      | 2         | 2         | false

    }
}
