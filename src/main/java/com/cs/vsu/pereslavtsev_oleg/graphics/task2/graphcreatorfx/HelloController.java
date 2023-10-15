package com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class HelloController {

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    ArrayList<Point2D> points = new ArrayList<Point2D>();

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));
        GraphicsContext gc =  canvas.getGraphicsContext2D();
        gc.fillRect(0, 0, 100, 100);

        canvas.setOnMousePressed(CanvasController.getCanvasOnMousePressedEventHandler());
        canvas.setOnMouseDragged(CanvasController.getCanvasOnMouseDraggedEventHandler());
        canvas.setOnMouseReleased(CanvasController.getCanvasOnMouseReleaseEventHandler());
//        canvas.setOnMouseClicked(event -> {
//            switch (event.getButton()) {
//                case PRIMARY -> handlePrimaryClick(canvas.getGraphicsContext2D(), event);
//            }
//        });
    }

    private void handlePrimaryClick(GraphicsContext graphicsContext, MouseEvent event) {
        final Point2D clickPoint = new Point2D(event.getX(), event.getY());

        final int POINT_RADIUS = 3;
        graphicsContext.fillOval(
                clickPoint.getX() - POINT_RADIUS, clickPoint.getY() - POINT_RADIUS,
                2 * POINT_RADIUS, 2 * POINT_RADIUS);

        if (points.size() > 0) {
            final Point2D lastPoint = points.get(points.size() - 1);
            graphicsContext.strokeLine(lastPoint.getX(), lastPoint.getY(), clickPoint.getX(), clickPoint.getY());
        }
        points.add(clickPoint);
    }


}