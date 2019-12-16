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

    def "Day 10: Monitoring station puzzle 1"() {
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
    }
}
