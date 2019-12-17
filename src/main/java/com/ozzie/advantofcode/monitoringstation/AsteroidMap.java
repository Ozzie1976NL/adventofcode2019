package com.ozzie.advantofcode.monitoringstation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class AsteroidMap {

    private final Set<Point> points = new HashSet<>();
    private final Map<Point, Set<Double>> pointAtan2Other = new HashMap<>();
    private final Map<Integer, List<Point>> noDetectablePointList = new HashMap<>();
    private  int maxX;
    private  int maxY;

    public AsteroidMap(String stringMap) {
        processMap(stringMap);
        fillAsteroidDetectableAsteroidMap();
        fillNoDetectablePointList();
    }

    private void fillNoDetectablePointList() {
        pointAtan2Other.keySet().forEach(key -> {
            int size = pointAtan2Other.get(key).size();
            List<Point> pointList = noDetectablePointList.getOrDefault(size, new LinkedList<>());
            pointList.add(key);
            noDetectablePointList.put(size, pointList);
        });
    }

    private void fillAsteroidDetectableAsteroidMap() {
        points.forEach(curPoint -> {
            points
                    .stream()
                    .filter(otherPoint -> !curPoint.equals(otherPoint))
                    .map(otherPoint -> Math.atan2(otherPoint.getY() - (double)curPoint.getY(), otherPoint.getX() - (double)curPoint.getX()))
                    .forEach(atan2 -> {
                        Set<Double> atan2Set = pointAtan2Other.getOrDefault(curPoint, new HashSet<>());
                        atan2Set.add(atan2);
                        pointAtan2Other.put(curPoint, atan2Set);
                    });
        });
    }

    private void processMap(String stringMap) {
        String[] lines = stringMap.split("\n");
        for (int y = 0; y < lines.length; y++) {
            char[] chars = lines[y].toCharArray();
            for (int x = 0; x < chars.length; x++) {
                if (chars[x] == '#') {
                    points.add(new Point(x, y));
                }
            }
        }
        maxY = lines.length;
        maxX = lines[0].length();
    }


    public List<Point> shootAllAsteroidsFrom(Point laserStation) {
        final Map<BigDecimal, Set<AnotherPoint>> allAngleRadiansOtherPointsMap = new HashMap<>();
        points.stream()
                .filter(asteroid -> !asteroid.equals(laserStation))
                .map(asteroid -> new AnotherPoint(asteroid,laserStation))
        .forEach(anotherPoint -> {
            final Set<AnotherPoint> anotherPointSet = allAngleRadiansOtherPointsMap.getOrDefault(anotherPoint.getAngleRadians(), new HashSet<>());
            anotherPointSet.add(anotherPoint);
            allAngleRadiansOtherPointsMap.put(anotherPoint.getAngleRadians(), anotherPointSet);

        });
        List<Point> allAsteroidsVaporized = new LinkedList<>();
        while (!allAngleRadiansOtherPointsMap.isEmpty()) {
            for(double i = -1.571;  i < 3.142; i += 0.001d ) { // Three quarters
                vaporizeAsteroidWhenInLineOfSight(allAngleRadiansOtherPointsMap, BigDecimal.valueOf(i).setScale(3, RoundingMode.HALF_UP), allAsteroidsVaporized);
            }
            for(double i = -3.142;  i < -1.571; i += 0.001d ) { // Last quarter
                vaporizeAsteroidWhenInLineOfSight(allAngleRadiansOtherPointsMap, BigDecimal.valueOf(i).setScale(3, RoundingMode.HALF_UP), allAsteroidsVaporized);
            }
        }
        return allAsteroidsVaporized;
    }

    private void vaporizeAsteroidWhenInLineOfSight(Map<BigDecimal, Set<AnotherPoint>> allAngleRadiansOtherPointsMap, BigDecimal angleRadians, List<Point> vaporizedAsteroids) {
        if(allAngleRadiansOtherPointsMap.containsKey(angleRadians)) {
            Set<AnotherPoint> anotherPointsSet = allAngleRadiansOtherPointsMap.get(angleRadians);
            AnotherPoint nearestAnotherPoint = anotherPointsSet.stream().min(Comparator.comparing(AnotherPoint::getDistance)).get();
            anotherPointsSet.remove(nearestAnotherPoint);
            vaporizedAsteroids.add(new Point(nearestAnotherPoint.getX(), nearestAnotherPoint.getY()));
            if(anotherPointsSet.isEmpty()) {
                allAngleRadiansOtherPointsMap.remove(angleRadians);
            }
        }
    }

    public List<Point> getPointsWithMostDetectableAsteroids() {
        return noDetectablePointList.get(noDetectablePointList.keySet().stream().mapToInt(Integer::intValue).max().getAsInt());
    }

    public int getNoMostDetectableAsteroids() {
        return noDetectablePointList.keySet().stream().mapToInt(Integer::intValue).max().getAsInt();
    }

    public Set<Point> getPoints() {
        return Collections.unmodifiableSet(points);
    }
}
