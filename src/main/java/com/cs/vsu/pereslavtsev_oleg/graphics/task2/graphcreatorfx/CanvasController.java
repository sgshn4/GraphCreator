package com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public abstract class CanvasController {

    private static double orgSceneX;
    private static double orgSceneY;
    private static double orgTranslateX;
    private static double orgTranslateY;
    private static double offsetX;
    private static double offsetY;
    private static double newTranslateX;
    private static double newTranslateY;

    private static double shiftX = 0;
    private static double shiftY = 0;
    private static Canvas canvas;
    private static List<Primitive> figures;

    public static void setCanvas(Canvas canvas) {
        CanvasController.canvas = canvas;
    }

    public static void setFigures(List<Primitive> figures) {
        CanvasController.figures = figures;
    }

    //On Press
    public static EventHandler<MouseEvent> canvasOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            orgSceneX = mouseEvent.getSceneX();
            orgSceneY = mouseEvent.getSceneY();
            orgTranslateX = ((Canvas) (mouseEvent.getSource())).getTranslateX();
            orgTranslateY = ((Canvas) (mouseEvent.getSource())).getTranslateY();
        }
    };

    //On Drag
    public static EventHandler<MouseEvent> canvasOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            mouseEvent.getEventType();
            offsetX = mouseEvent.getSceneX() - orgSceneX;
            offsetY = mouseEvent.getSceneY() - orgSceneY;
            newTranslateX = orgTranslateX + offsetX;
            newTranslateY = orgTranslateY + offsetY;
            update(canvas, figures);
            shiftX += newTranslateX;
            shiftY += newTranslateY;
            System.out.println(newTranslateX + " " + newTranslateY);
        }
    };

    //On Release
    public static EventHandler<MouseEvent> canvasOnMouseReleaseEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {

        }
    };

    public static void drawFigures(Canvas canvas, List<Primitive> figures) {
        PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        for (Primitive figure : figures) {
            Primitive primitive = figure;
            for (int i = 0; i < figure.getX().length; i++) {
                pixelWriter.setColor((int)(primitive.getX()[i] + shiftX), (int)(primitive.getY()[i] + shiftY), Color.BLACK);
            }
        }
    }

    public static void update(Canvas canvas, List<Primitive> figures) {
        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        for (int i = 0; i < canvas.getWidth(); i++) {
            for (int j = 0; j < canvas.getHeight(); j++) {
                pixelWriter.setColor(i, j, Color.WHITE);
            }
        }
        drawFigures(canvas, figures);
    }

    public static double getShiftX() {
        return shiftX;
    }

    public static double getShiftY() {
        return shiftY;
    }

    public static EventHandler<MouseEvent> getCanvasOnMousePressedEventHandler() {
        System.out.println("Mouse 1");
        return canvasOnMousePressedEventHandler;
    }

    public static EventHandler<MouseEvent> getCanvasOnMouseDraggedEventHandler() {
        System.out.println("Mouse 2");
        return canvasOnMouseDraggedEventHandler;
    }

    public static EventHandler<MouseEvent> getCanvasOnMouseReleaseEventHandler() {
        System.out.println("Mouse 3");
        update(canvas, figures);
        return canvasOnMouseReleaseEventHandler;
    }
}
