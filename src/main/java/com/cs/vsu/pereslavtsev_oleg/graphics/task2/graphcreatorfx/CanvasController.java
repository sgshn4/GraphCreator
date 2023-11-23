package com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
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
    private static List<Primitive> subLayout;

    private static boolean isGridVisible;
    private static boolean isAxisVisible;

    private static PixelWriter pixelWriter;

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
        }
    };

    //On Release
    public static EventHandler<MouseEvent> canvasOnMouseReleaseEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {

        }
    };

    public static void init(Canvas canvas) {
        pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        figures = new ArrayList<>();
        subLayout = new ArrayList<>();
        isGridVisible = false;
        isAxisVisible = true;
        CanvasController.canvas = canvas;
        subLayout.add(null);
        subLayout.add(null);
        createAxis(canvas);
        for (int i = 0; i < canvas.getWidth(); i+= 50) {
            subLayout.add(Figures.createLine(i, 0, i, (int)(canvas.getHeight())));
        }
        for (int i = 0; i < canvas.getHeight(); i+= 50) {
            subLayout.add(Figures.createLine(0, i, (int)(canvas.getWidth()), i));
        }
    }

    public static void drawFigures(List<Primitive> figures) {
        for (Primitive figure : figures) {
            Primitive primitive = figure;
            for (int i = 0; i < figure.getX().length; i++) {
                pixelWriter.setColor((int)(primitive.getX()[i] + shiftX), (int)(primitive.getY()[i] + shiftY), Color.BLACK);
            }
        }
    }

    private static void drawSubLayout() {
        if (isGridVisible) {
            for (int i = 2; i < subLayout.size(); i++) {
                for (int j = 0; j < subLayout.get(i).getX().length; j++) {
                    pixelWriter.setColor(subLayout.get(i).getX()[j], subLayout.get(i).getY()[j], Color.BLACK);
                }
            }
        }
        if (isAxisVisible) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < subLayout.get(i).getX().length; j++) {
                    pixelWriter.setColor((int)(subLayout.get(i).getX()[j] + shiftX),
                            (int)(subLayout.get(i).getY()[j] + shiftY), Color.BLACK);
                }
            }
        }
    }
    private static void createAxis(Canvas canvas) {
        //X axis
        subLayout.set(0, Figures.createLine((int)(0 - shiftX), (int)(canvas.getHeight() / 2),
                (int)(canvas.getWidth() - shiftX), (int)(canvas.getHeight() / 2)));
        //Y axis
        subLayout.set(1, Figures.createLine((int)(canvas.getWidth() / 2), (int)(0 - shiftY),
                (int)(canvas.getWidth() / 2), (int)(canvas.getHeight() - shiftY)));
    }

    public static void update(Canvas canvas, List<Primitive> figures) {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        createAxis(canvas);
        drawSubLayout();
        drawFigures(figures);
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
