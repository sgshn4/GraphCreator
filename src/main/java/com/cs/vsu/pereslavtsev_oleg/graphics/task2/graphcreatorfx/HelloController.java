package com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class HelloController {


    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    ArrayList<Point2D> points = new ArrayList<Point2D>();

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));
        Primitive line = Figures.createLine(300, 300, 400, 400);
        Primitive rect = Figures.createRectangle(100, 100, 200, 200);
        Primitive round = Figures.createRound(600, 600, 100);
        List<Primitive> primitives = new ArrayList<>();
        CanvasController.setCanvas(canvas);
        primitives.add(round);
        primitives.add(line);
        primitives.add(rect);
        CanvasController.setFigures(primitives);

        canvas.setOnMousePressed(CanvasController.canvasOnMousePressedEventHandler);
        canvas.setOnMouseDragged(CanvasController.canvasOnMouseDraggedEventHandler);
        canvas.setOnMouseReleased(CanvasController.canvasOnMouseReleaseEventHandler);

    }


}