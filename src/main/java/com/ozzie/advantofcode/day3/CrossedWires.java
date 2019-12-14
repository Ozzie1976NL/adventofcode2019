package com.ozzie.advantofcode.day3;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CrossedWires {
    private final List<Point> wire1Path     = new LinkedList<>();
    private final List<Point> wire2Path     = new LinkedList<>();
    private final List<Point> intersections = new LinkedList<>();
    private final int manhattenDistance;
    private final int leastSumStepsIntersections;

    public CrossedWires(String wireString1, String wireString2) {
        final Wire wire1 = new Wire(wireString1);
        final Wire wire2 = new Wire(wireString2);
        extractWire(wire1.getStepList(), wire1Path, Collections.emptySet(), intersections);
        extractWire(wire2.getStepList(), wire2Path, new HashSet<>(wire1Path), intersections);
        manhattenDistance = determineClosetMahattenDistanceIntersectionOrigin(intersections).orElse(Integer.MAX_VALUE);
        leastSumStepsIntersections = determineLeastSumStepsIntersections(intersections, wire1Path, wire2Path).orElse(Integer.MAX_VALUE);
    }

    private static Optional<Integer> determineClosetMahattenDistanceIntersectionOrigin(List<Point> intersections) {
        return intersections.stream()
                .map(p -> Math.abs(p.getX()) + Math.abs(p.getY()))
                .sorted()
                .findFirst();
    }

    private static Optional<Integer> determineLeastSumStepsIntersections(List<Point> intersections, List<Point> wire1Path, List<Point> wire2Path) {
        return intersections.stream()
                .map(intersection -> wire1Path.indexOf(intersection) + wire2Path.indexOf(intersection) + 2)
                .sorted()
                .findFirst();
    }

    public int getManhattenDistance() {
        return manhattenDistance;
    }

    public int getLeastSumStepsIntersections() {
        return leastSumStepsIntersections;
    }

    private static void extractWire(List<Step> steps, List<Point> target, Set<Point> other, List<Point> intersections) {
        int posX = 0;
        int posY = 0;
        for (Step step : steps) {
            for (int i = 0; i < step.getSteps(); i++) {
                switch (step.getDirection()) {
                    case UP:
                        posY++;
                        break;
                    case DOWN:
                        posY--;
                        break;
                    case LEFT:
                        posX--;
                        break;
                    case RIGHT:
                        posX++;
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported direction");
                }
                final Point point = new Point(posX, posY);
                target.add(point);
                if(other.contains(point)) {
                    intersections.add(point);
                }
            }
        }
    }
}
