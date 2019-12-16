package com.ozzie.advantofcode.monitoringstation;

import java.util.*;

public class AsteroidMap {

    private final Set<Point> points = new HashSet<>();
    private final Map<Point, Set<Double>> pointAtan2Other = new HashMap<>();
    private final Map<Integer, List<Point>> noDetectablePointList = new HashMap<>();

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
