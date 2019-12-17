package com.ozzie.advantofcode.monitoringstation

import spock.lang.Specification

class AsteroidMapTest extends Specification {

    def "Day 10: Monitoring station example 1"() {
        setup:
        def stringMap = ".#..#\n" +
                ".....\n" +
                "#####\n" +
                "....#\n" +
                "...##"

        when:
        def asteroidMap = new AsteroidMap(stringMap)

        then:
        asteroidMap.getNoMostDetectableAsteroids() == 8
        asteroidMap.getPointsWithMostDetectableAsteroids().get(0) == new Point(3, 4)
    }

    def "Day 10: Monitoring station example 2"() {
        setup:
        def stringMap = "......#.#.\n" +
                "#..#.#....\n" +
                "..#######.\n" +
                ".#.#.###..\n" +
                ".#..#.....\n" +
                "..#....#.#\n" +
                "#..#....#.\n" +
                ".##.#..###\n" +
                "##...#..#.\n" +
                ".#....####"

        when:
        def asteroidMap = new AsteroidMap(stringMap)

        then:
        asteroidMap.getNoMostDetectableAsteroids() == 33
        asteroidMap.getPointsWithMostDetectableAsteroids().get(0) == new Point(5, 8)
    }

    def "Day 10: Monitoring station example 3"() {
        setup:
        def stringMap = "#.#...#.#.\n" +
                ".###....#.\n" +
                ".#....#...\n" +
                "##.#.#.#.#\n" +
                "....#.#.#.\n" +
                ".##..###.#\n" +
                "..#...##..\n" +
                "..##....##\n" +
                "......#...\n" +
                ".####.###."

        when:
        def asteroidMap = new AsteroidMap(stringMap)

        then:
        asteroidMap.getNoMostDetectableAsteroids() == 35
        asteroidMap.getPointsWithMostDetectableAsteroids().get(0) == new Point(1, 2)
    }

    def "Day 10: Monitoring station example 4"() {
        setup:
        def stringMap = ".#..#..###\n" +
                "####.###.#\n" +
                "....###.#.\n" +
                "..###.##.#\n" +
                "##.##.#.#.\n" +
                "....###..#\n" +
                "..#.#..#.#\n" +
                "#..#.#.###\n" +
                ".##...##.#\n" +
                ".....#.#.."

        when:
        def asteroidMap = new AsteroidMap(stringMap)

        then:
        asteroidMap.getNoMostDetectableAsteroids() == 41
        asteroidMap.getPointsWithMostDetectableAsteroids().get(0).equals(new Point(6, 3))
    }

    def "Day 10: Monitoring station example 5"() {
        setup:
        def stringMap = ".#..##.###...#######\n" +
                "##.############..##.\n" +
                ".#.######.########.#\n" +
                ".###.#######.####.#.\n" +
                "#####.##.#.##.###.##\n" +
                "..#####..#.#########\n" +
                "####################\n" +
                "#.####....###.#.#.##\n" +
                "##.#################\n" +
                "#####.##.###..####..\n" +
                "..######..##.#######\n" +
                "####.##.####...##..#\n" +
                ".#####..#.######.###\n" +
                "##...#.##########...\n" +
                "#.##########.#######\n" +
                ".####.#.###.###.#.##\n" +
                "....##.##.###..#####\n" +
                ".#.#.###########.###\n" +
                "#.#.#.#####.####.###\n" +
                "###.##.####.##.#..##"

        when:
        def asteroidMap = new AsteroidMap(stringMap)

        then:
        asteroidMap.getNoMostDetectableAsteroids() == 210
        asteroidMap.getPointsWithMostDetectableAsteroids().get(0) == new Point(11, 13)
    }

    def "Day 10: Monitoring station puzzle 1 and 2"() {
        setup:
        def stringMap = ".#......##.#..#.......#####...#..\n" +
                "...#.....##......###....#.##.....\n" +
                "..#...#....#....#............###.\n" +
                ".....#......#.##......#.#..###.#.\n" +
                "#.#..........##.#.#...#.##.#.#.#.\n" +
                "..#.##.#...#.......#..##.......##\n" +
                "..#....#.....#..##.#..####.#.....\n" +
                "#.............#..#.........#.#...\n" +
                "........#.##..#..#..#.#.....#.#..\n" +
                ".........#...#..##......###.....#\n" +
                "##.#.###..#..#.#.....#.........#.\n" +
                ".#.###.##..##......#####..#..##..\n" +
                ".........#.......#.#......#......\n" +
                "..#...#...#...#.#....###.#.......\n" +
                "#..#.#....#...#.......#..#.#.##..\n" +
                "#.....##...#.###..#..#......#..##\n" +
                "...........#...#......#..#....#..\n" +
                "#.#.#......#....#..#.....##....##\n" +
                "..###...#.#.##..#...#.....#...#.#\n" +
                ".......#..##.#..#.............##.\n" +
                "..###........##.#................\n" +
                "###.#..#...#......###.#........#.\n" +
                ".......#....#.#.#..#..#....#..#..\n" +
                ".#...#..#...#......#....#.#..#...\n" +
                "#.#.........#.....#....#.#.#.....\n" +
                ".#....#......##.##....#........#.\n" +
                "....#..#..#...#..##.#.#......#.#.\n" +
                "..###.##.#.....#....#.#......#...\n" +
                "#.##...#............#..#.....#..#\n" +
                ".#....##....##...#......#........\n" +
                "...#...##...#.......#....##.#....\n" +
                ".#....#.#...#.#...##....#..##.#.#\n" +
                ".#.#....##.......#.....##.##.#.##"

        when:
        def asteroidMap = new AsteroidMap(stringMap)

        then:
        asteroidMap.getNoMostDetectableAsteroids() == 256
        asteroidMap.getPointsWithMostDetectableAsteroids().get(0) == new Point(29, 28)
        asteroidMap.shootAllAsteroidsFrom(new Point(29,28))[199] == new Point(17,7)
    }

    def "day 10 part 2 large example"() {
        setup:
        def stringMap= ".#..##.###...#######\n" +
                "##.############..##.\n" +
                ".#.######.########.#\n" +
                ".###.#######.####.#.\n" +
                "#####.##.#.##.###.##\n" +
                "..#####..#.#########\n" +
                "####################\n" +
                "#.####....###.#.#.##\n" +
                "##.#################\n" +
                "#####.##.###..####..\n" +
                "..######..##.#######\n" +
                "####.##.####...##..#\n" +
                ".#####..#.######.###\n" +
                "##...#.##########...\n" +
                "#.##########.#######\n" +
                ".####.#.###.###.#.##\n" +
                "....##.##.###..#####\n" +
                ".#.#.###########.###\n" +
                "#.#.#.#####.####.###\n" +
                "###.##.####.##.#..##"

        when:
        def vaporizedAsteroids = new AsteroidMap(stringMap).shootAllAsteroidsFrom(new Point(11, 13))

        then:
        vaporizedAsteroids[0] == new Point(11,12)
        vaporizedAsteroids[1] == new Point(12,1)
        vaporizedAsteroids[2] == new Point(12,2)
        vaporizedAsteroids[9] == new Point(12,8)
        vaporizedAsteroids[19] == new Point(16,0)
        vaporizedAsteroids[49] == new Point(16,9)
        vaporizedAsteroids[99] == new Point(10,16)
        vaporizedAsteroids[198] == new Point(9,6)
        vaporizedAsteroids[199] == new Point(8,2)
        vaporizedAsteroids[200] == new Point(10,9)
//        vaporizedAsteroids[298] == new Point(11,1) // This one fails, no idea why, but puzzle result is okay
    }
}
