package com.ozzie.advantofcode.hullpaintingrobot;

import com.ozzie.advantofcode.common.Point;
import com.ozzie.advantofcode.intcode.IntComputer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class HullPaintingRobot {
    private final Map<Point, Color> paintedPanels       = new HashMap<>();
    private final Queue<Long>       inputQueue = new LinkedList<>();
    private final Queue<Long>       outputQueue = new LinkedList<>();

    private int x = 0;
    private int y = 0;
    private Direction direction = Direction.UP;


    private final IntComputer intComputer;


    public HullPaintingRobot(long[] program) {
        intComputer = new IntComputer(program, inputQueue, outputQueue);
    }

    public int run(Color colorFirstPanel) {
        paintedPanels.put(new Point(x, y), colorFirstPanel);
        while(!IntComputer.State.HALTED.equals(intComputer.getState())) {
            Point curPoint = new Point(x, y);
            Color curColor = paintedPanels.getOrDefault(curPoint, Color.BLACK);
            inputQueue.add((long)curColor.ordinal());
            intComputer.run();
            paintedPanels.put(curPoint, Color.values()[outputQueue.remove().intValue()]);
            Turn turn = Turn.values()[outputQueue.remove().intValue()];
            // Determine direction
            if(Turn.TURN_LEFT_90_DEGREES.equals(turn)) {
                switch(direction) {
                    case UP:
                        direction = Direction.LEFT;
                        break;
                    case LEFT:
                        direction = Direction.DOWN;
                        break;
                    case DOWN:
                        direction = Direction.RIGHT;
                        break;
                    case RIGHT:
                        direction = Direction.UP;
                        break;
                }
            } else {
                switch(direction) {
                    case UP:
                        direction = Direction.RIGHT;
                        break;
                    case RIGHT:
                        direction = Direction.DOWN;
                        break;
                    case DOWN:
                        direction =Direction.LEFT;
                        break;
                    case LEFT:
                        direction = Direction.UP;
                        break;
                }
            }
            // Move!
            switch (direction) {
                case UP:
                    y--;
                    break;
                case RIGHT:
                    x++;
                    break;
                case DOWN:
                    y++;
                    break;
                case LEFT:
                    x--;
                    break;
            }
        }
        return paintedPanels.size();
    }

    public void drawPoints() {
        int minX = paintedPanels.keySet().stream().mapToInt(Point::getX).min().getAsInt() - 20;
        int maxX = paintedPanels.keySet().stream().mapToInt(Point::getX).max().getAsInt() + 20;
        int minY = paintedPanels.keySet().stream().mapToInt(Point::getY).min().getAsInt() - 20;
        int maxY = paintedPanels.keySet().stream().mapToInt(Point::getY).max().getAsInt() + 20;
        for(int curY = minY ; curY < maxY ; curY++) {
            for(int curX = minX; curX < maxX; curX++) {
                Color color = paintedPanels.getOrDefault(new Point(curX, curY), Color.BLACK);
                if(Color.BLACK.equals(color)) {
                    System.out.print(" ");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }

    public enum Color {
        BLACK,
        WHITE
    }

    public enum Turn {
        TURN_LEFT_90_DEGREES,
        TURN_RIGHT_90_DEGREES
    }

    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }
}
