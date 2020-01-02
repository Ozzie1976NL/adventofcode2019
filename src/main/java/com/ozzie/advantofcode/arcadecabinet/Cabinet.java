package com.ozzie.advantofcode.arcadecabinet;

import com.ozzie.advantofcode.intcode.IntComputer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Queue;

public class Cabinet extends Application {
    private final Queue<Long> inputQueue = new LinkedList<>();
    private final Queue<Long> outputQueue = new LinkedList<>();
    private final long[] program = {1, 380, 379, 385, 1008, 2267, 610415, 381, 1005, 381, 12, 99, 109, 2268, 1101, 0, 0, 383, 1101, 0, 0, 382, 20102, 1, 382, 1, 20101, 0, 383, 2, 21101, 37, 0, 0, 1106, 0, 578, 4, 382, 4, 383, 204, 1, 1001, 382, 1, 382, 1007, 382, 37, 381, 1005, 381, 22, 1001, 383, 1, 383, 1007, 383, 22, 381, 1005, 381, 18, 1006, 385, 69, 99, 104, -1, 104, 0, 4, 386, 3, 384, 1007, 384, 0, 381, 1005, 381, 94, 107, 0, 384, 381, 1005, 381, 108, 1105, 1, 161, 107, 1, 392, 381, 1006, 381, 161, 1101, -1, 0, 384, 1106, 0, 119, 1007, 392, 35, 381, 1006, 381, 161, 1101, 0, 1, 384, 21001, 392, 0, 1, 21102, 1, 20, 2, 21101, 0, 0, 3, 21102, 138, 1, 0, 1105, 1, 549, 1, 392, 384, 392, 21002, 392, 1, 1, 21101, 0, 20, 2, 21101, 3, 0, 3, 21101, 161, 0, 0, 1106, 0, 549, 1101, 0, 0, 384, 20001, 388, 390, 1, 20101, 0, 389, 2, 21102, 1, 180, 0, 1105, 1, 578, 1206, 1, 213, 1208, 1, 2, 381, 1006, 381, 205, 20001, 388, 390, 1, 21002, 389, 1, 2, 21101, 205, 0, 0, 1106, 0, 393, 1002, 390, -1, 390, 1102, 1, 1, 384, 21002, 388, 1, 1, 20001, 389, 391, 2, 21102, 228, 1, 0, 1106, 0, 578, 1206, 1, 261, 1208, 1, 2, 381, 1006, 381, 253, 20101, 0, 388, 1, 20001, 389, 391, 2, 21101, 253, 0, 0, 1105, 1, 393, 1002, 391, -1, 391, 1101, 1, 0, 384, 1005, 384, 161, 20001, 388, 390, 1, 20001, 389, 391, 2, 21102, 1, 279, 0, 1105, 1, 578, 1206, 1, 316, 1208, 1, 2, 381, 1006, 381, 304, 20001, 388, 390, 1, 20001, 389, 391, 2, 21101, 304, 0, 0, 1105, 1, 393, 1002, 390, -1, 390, 1002, 391, -1, 391, 1101, 1, 0, 384, 1005, 384, 161, 20102, 1, 388, 1, 20101, 0, 389, 2, 21101, 0, 0, 3, 21102, 338, 1, 0, 1105, 1, 549, 1, 388, 390, 388, 1, 389, 391, 389, 20102, 1, 388, 1, 20101, 0, 389, 2, 21101, 0, 4, 3, 21102, 1, 365, 0, 1106, 0, 549, 1007, 389, 21, 381, 1005, 381, 75, 104, -1, 104, 0, 104, 0, 99, 0, 1, 0, 0, 0, 0, 0, 0, 265, 16, 17, 1, 1, 18, 109, 3, 21202, -2, 1, 1, 21201, -1, 0, 2, 21102, 1, 0, 3, 21102, 414, 1, 0, 1105, 1, 549, 22101, 0, -2, 1, 21202, -1, 1, 2, 21101, 429, 0, 0, 1105, 1, 601, 2102, 1, 1, 435, 1, 386, 0, 386, 104, -1, 104, 0, 4, 386, 1001, 387, -1, 387, 1005, 387, 451, 99, 109, -3, 2106, 0, 0, 109, 8, 22202, -7, -6, -3, 22201, -3, -5, -3, 21202, -4, 64, -2, 2207, -3, -2, 381, 1005, 381, 492, 21202, -2, -1, -1, 22201, -3, -1, -3, 2207, -3, -2, 381, 1006, 381, 481, 21202, -4, 8, -2, 2207, -3, -2, 381, 1005, 381, 518, 21202, -2, -1, -1, 22201, -3, -1, -3, 2207, -3, -2, 381, 1006, 381, 507, 2207, -3, -4, 381, 1005, 381, 540, 21202, -4, -1, -1, 22201, -3, -1, -3, 2207, -3, -4, 381, 1006, 381, 529, 22101, 0, -3, -7, 109, -8, 2106, 0, 0, 109, 4, 1202, -2, 37, 566, 201, -3, 566, 566, 101, 639, 566, 566, 2102, 1, -1, 0, 204, -3, 204, -2, 204, -1, 109, -4, 2105, 1, 0, 109, 3, 1202, -1, 37, 593, 201, -2, 593, 593, 101, 639, 593, 593, 21001, 0, 0, -2, 109, -3, 2105, 1, 0, 109, 3, 22102, 22, -2, 1, 22201, 1, -1, 1, 21102, 1, 409, 2, 21102, 1, 463, 3, 21102, 1, 814, 4, 21102, 1, 630, 0, 1106, 0, 456, 21201, 1, 1453, -2, 109, -3, 2105, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 2, 2, 0, 2, 2, 0, 2, 2, 2, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 2, 0, 0, 0, 0, 1, 1, 0, 0, 2, 0, 0, 2, 2, 0, 0, 0, 2, 2, 0, 2, 2, 0, 0, 0, 2, 2, 0, 2, 2, 2, 2, 0, 0, 2, 2, 0, 0, 2, 0, 2, 0, 1, 1, 0, 0, 2, 2, 2, 0, 0, 0, 2, 2, 2, 2, 0, 0, 2, 2, 0, 2, 0, 2, 0, 2, 2, 0, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 0, 1, 1, 0, 2, 0, 0, 2, 2, 2, 0, 2, 2, 0, 2, 0, 2, 2, 2, 2, 0, 0, 2, 2, 2, 0, 2, 2, 0, 0, 0, 0, 2, 2, 0, 0, 2, 0, 1, 1, 0, 2, 2, 2, 0, 0, 0, 2, 2, 2, 0, 2, 2, 2, 2, 2, 0, 0, 0, 2, 2, 0, 2, 2, 2, 0, 2, 0, 0, 0, 0, 0, 2, 2, 0, 1, 1, 0, 2, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 0, 0, 0, 0, 2, 0, 0, 2, 0, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 1, 1, 0, 0, 0, 2, 0, 0, 2, 2, 2, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 2, 2, 2, 0, 1, 1, 0, 2, 0, 0, 2, 2, 0, 0, 0, 2, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 1, 1, 0, 2, 0, 0, 0, 2, 0, 2, 2, 2, 2, 2, 0, 0, 2, 0, 2, 2, 0, 0, 2, 2, 0, 2, 2, 0, 2, 2, 0, 2, 0, 0, 2, 2, 0, 1, 1, 0, 2, 0, 2, 2, 0, 2, 2, 0, 0, 0, 0, 0, 2, 2, 0, 2, 0, 0, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 1, 0, 0, 0, 0, 2, 0, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 0, 2, 0, 0, 2, 2, 0, 2, 0, 0, 0, 1, 1, 0, 0, 2, 2, 2, 0, 2, 0, 2, 0, 2, 2, 2, 0, 0, 2, 2, 0, 2, 2, 2, 0, 0, 0, 0, 2, 0, 2, 2, 0, 2, 0, 2, 2, 0, 1, 1, 0, 0, 2, 2, 2, 2, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0, 2, 0, 2, 2, 2, 0, 2, 0, 0, 1, 1, 0, 2, 0, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 2, 2, 0, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 2, 0, 0, 0, 2, 2, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 72, 10, 67, 45, 58, 25, 55, 73, 97, 49, 19, 51, 58, 95, 30, 82, 74, 9, 98, 96, 38, 64, 30, 45, 14, 73, 42, 5, 3, 61, 68, 23, 18, 14, 9, 16, 21, 7, 77, 39, 38, 16, 82, 17, 58, 87, 90, 64, 52, 1, 96, 67, 66, 16, 65, 15, 22, 41, 69, 90, 93, 92, 96, 45, 68, 17, 63, 51, 15, 61, 51, 93, 65, 55, 42, 76, 48, 52, 31, 98, 6, 88, 69, 65, 65, 30, 51, 88, 4, 13, 36, 90, 80, 23, 31, 42, 63, 86, 52, 15, 79, 78, 59, 77, 57, 71, 84, 81, 73, 56, 1, 5, 7, 86, 75, 31, 63, 76, 21, 73, 16, 41, 86, 15, 78, 85, 2, 79, 63, 54, 79, 65, 87, 13, 86, 96, 81, 69, 27, 76, 8, 48, 5, 79, 10, 74, 76, 86, 95, 55, 72, 52, 23, 41, 50, 46, 68, 29, 86, 61, 96, 29, 34, 40, 86, 86, 1, 20, 90, 35, 69, 64, 50, 51, 75, 65, 93, 19, 5, 15, 96, 3, 88, 8, 43, 66, 88, 72, 84, 69, 42, 4, 95, 51, 80, 81, 27, 75, 92, 22, 45, 54, 63, 51, 82, 91, 13, 25, 54, 41, 84, 84, 29, 98, 50, 91, 11, 40, 69, 13, 47, 42, 72, 46, 87, 31, 27, 98, 65, 94, 26, 51, 79, 39, 29, 38, 42, 46, 25, 36, 26, 66, 12, 93, 58, 1, 61, 41, 37, 57, 60, 60, 9, 70, 63, 26, 56, 1, 27, 5, 11, 93, 17, 48, 95, 19, 79, 16, 14, 16, 29, 79, 56, 16, 26, 37, 50, 10, 38, 53, 4, 10, 3, 57, 20, 59, 16, 51, 88, 66, 74, 91, 56, 42, 84, 30, 36, 31, 36, 58, 68, 66, 91, 36, 71, 30, 39, 96, 50, 84, 76, 95, 14, 89, 75, 59, 77, 66, 36, 88, 62, 60, 3, 45, 13, 39, 48, 33, 59, 21, 19, 35, 90, 81, 66, 52, 75, 34, 70, 55, 56, 47, 22, 20, 87, 73, 73, 76, 73, 8, 96, 55, 46, 5, 1, 64, 27, 8, 37, 87, 50, 8, 79, 74, 63, 26, 43, 44, 2, 85, 91, 28, 13, 16, 15, 55, 87, 94, 28, 86, 66, 29, 34, 46, 18, 41, 37, 94, 63, 31, 78, 48, 17, 4, 25, 62, 15, 10, 18, 19, 97, 50, 78, 5, 79, 5, 70, 64, 86, 61, 58, 59, 61, 5, 71, 68, 14, 24, 17, 56, 85, 52, 64, 92, 45, 90, 94, 55, 47, 5, 56, 59, 20, 15, 41, 36, 58, 55, 25, 47, 45, 69, 58, 36, 44, 80, 94, 52, 84, 17, 27, 20, 44, 51, 93, 10, 56, 77, 45, 29, 93, 63, 96, 95, 47, 31, 63, 69, 64, 74, 53, 34, 36, 20, 14, 40, 30, 61, 86, 15, 3, 94, 61, 43, 75, 59, 64, 41, 34, 98, 32, 65, 73, 18, 30, 46, 66, 38, 68, 25, 96, 16, 37, 54, 38, 44, 26, 52, 1, 2, 21, 93, 37, 26, 4, 45, 69, 82, 59, 34, 55, 34, 77, 88, 46, 70, 32, 56, 82, 10, 20, 31, 40, 20, 55, 3, 3, 93, 95, 65, 56, 61, 68, 41, 35, 62, 20, 58, 55, 42, 41, 40, 33, 51, 6, 52, 84, 27, 62, 81, 32, 35, 87, 97, 79, 7, 97, 77, 40, 48, 74, 4, 6, 36, 58, 59, 25, 6, 5, 84, 7, 44, 51, 88, 37, 9, 30, 29, 26, 91, 41, 72, 39, 24, 68, 58, 49, 80, 49, 43, 98, 43, 92, 9, 49, 64, 10, 96, 50, 86, 56, 2, 72, 58, 80, 57, 77, 61, 74, 14, 42, 50, 55, 40, 21, 77, 20, 19, 16, 80, 84, 92, 27, 32, 37, 80, 59, 69, 13, 11, 19, 6, 94, 54, 88, 51, 69, 41, 54, 68, 36, 82, 68, 19, 77, 85, 37, 5, 58, 61, 72, 5, 67, 17, 35, 29, 18, 71, 46, 5, 29, 8, 93, 97, 36, 37, 25, 93, 27, 33, 93, 79, 10, 84, 75, 6, 91, 98, 34, 32, 37, 70, 18, 84, 52, 32, 11, 88, 44, 69, 58, 92, 52, 68, 77, 39, 90, 9, 58, 74, 1, 53, 56, 64, 75, 46, 59, 39, 52, 32, 41, 62, 81, 75, 7, 93, 29, 89, 51, 34, 31, 93, 70, 94, 30, 98, 68, 3, 60, 2, 2, 49, 31, 15, 65, 11, 78, 70, 2, 50, 29, 9, 9, 85, 65, 52, 28, 95, 55, 77, 98, 29, 65, 56, 51, 32, 44, 42, 82, 14, 29, 22, 5, 29, 65, 86, 84, 88, 58, 63, 10, 13, 13, 51, 97, 17, 57, 19, 39, 83, 72, 93, 15, 54, 31, 83, 3, 43, 21, 83, 74, 2, 86, 47, 25, 89, 20, 11, 68, 80, 29, 21, 58, 69, 610415};
    private final IntComputer intComputer = new IntComputer(program, inputQueue, outputQueue);
    private final Canvas canvas = new Canvas(PLAY_FIELD_WIDTH * (double) TILE_WIDTH, PLAY_FIELD_HEIGHT * (double) TILE_HEIGHT);
    private SimpleStringProperty scoreProperty = new SimpleStringProperty();
    private ToggleButton auto;
    private ScheduledService<Void> autoMode;
    private static final int TILE_WIDTH = 30;
    private static final int TILE_HEIGHT = 30;
    private static final int PLAY_FIELD_WIDTH = 37;
    private static final int PLAY_FIELD_HEIGHT = 22;
    private int xLocationBall;
    private int xLocationPaddle;

    @Override
    public void start(Stage stage) throws Exception {
        HBox buttons = new HBox();
        auto = new ToggleButton("auto");
        auto.setPrefWidth(TILE_WIDTH * 3);
        auto.setPrefHeight(TILE_HEIGHT);
        Button left = new Button("left");
        left.setPrefWidth(TILE_WIDTH * 3);
        left.setPrefHeight(TILE_HEIGHT);
        left.setOnAction(ev -> {
            inputQueue.add(-1L);
            drawScreen();
        });
        Button neutral = new Button("neutral");
        neutral.setPrefWidth(TILE_WIDTH * 3);
        neutral.setPrefHeight(TILE_HEIGHT);
        neutral.setOnAction(ev -> {
            inputQueue.add(0L);
            drawScreen();
        });
        Button right = new Button("right");
        right.setPrefWidth(TILE_WIDTH * 3);
        right.setPrefHeight(TILE_HEIGHT);
        right.setOnAction(ev -> {
            inputQueue.add(1L);
            drawScreen();
        });
        Button reset = new Button("reset");
        reset.setPrefWidth(TILE_WIDTH * 3);
        reset.setPrefHeight(TILE_HEIGHT);
        reset.setOnAction(ev -> {
            intComputer.reset();
            intComputer.getMemory()[0] = 2L;
            drawScreen();
        });
        Label score = new Label();
        score.textProperty().bindBidirectional(scoreProperty);
        score.setPrefWidth(TILE_WIDTH * 3);
        buttons.getChildren().addAll(auto, left, neutral, right, score, reset);
        buttons.setFillHeight(true);
        buttons.setAlignment(Pos.CENTER);
        intComputer.getMemory()[0] = 2L;
        drawScreen();
        VBox vbox = new VBox();
        vbox.getChildren().addAll(canvas, buttons);
        Scene scene = new Scene(vbox, PLAY_FIELD_WIDTH * (double) TILE_WIDTH, PLAY_FIELD_HEIGHT * (double) TILE_HEIGHT + TILE_HEIGHT);
        stage.setTitle("Advent of Code Cabinet");
        stage.setScene(scene);

        autoMode = new ScheduledService<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        if (auto.isSelected() && intComputer.getState().equals(IntComputer.State.WAITING_FOR_INPUT) && inputQueue.isEmpty()) {
                            if (xLocationPaddle < xLocationBall) {
                                inputQueue.add(1L);
                            } else if (xLocationPaddle > xLocationBall) {
                                inputQueue.add(-1L);
                            } else {
                                inputQueue.add(0L);
                            }
                            drawScreen();
                        }
                        return null;
                    }
                };
            }
        };
        autoMode.setPeriod(Duration.millis(50d));
        autoMode.start();
        stage.show();
    }

    public void drawScreen() {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        intComputer.run();
        while (outputQueue.size() >= 3) {
            long x = outputQueue.remove();
            long y = outputQueue.remove();
            long data = outputQueue.remove();
            if (x == -1 && y == 0) {
                Platform.runLater(() -> scoreProperty.set(String.format(" %010d ", data)));
            } else {
                switch (Tile.values()[(int) data]) {
                    case Empty:
                        gc.setFill(Color.WHITE);
                        gc.fillRect(x * (double) TILE_WIDTH, y * (double) TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
                        break;
                    case Wall:
                        gc.setFill(Color.GRAY);
                        gc.fillRect(x * (double) TILE_WIDTH, y * (double) TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
                        break;
                    case Block:
                        gc.setFill(Color.RED);
                        gc.fillRect(x * (double) TILE_WIDTH, y * (double) TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
                        break;
                    case HorizonalPaddle:
                        gc.setFill(Color.GREEN);
                        gc.fillRect(x * (double) TILE_WIDTH, y * (double) TILE_HEIGHT + TILE_HEIGHT / 2f, TILE_WIDTH, TILE_HEIGHT / 2f);
                        xLocationPaddle = (int) x;
                        break;
                    case Ball:
                        gc.setFill(Color.ORANGE);
                        gc.fillOval(x * (double) TILE_WIDTH, y * (double) TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
                        xLocationBall = (int) x;
                        break;
                    default:
                        throw new IllegalArgumentException("Oh oh, unknown tile!");
                }
            }
        }
    }
}
