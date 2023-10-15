package com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

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

    //On Press
    private static EventHandler<MouseEvent> canvasOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            orgSceneX = mouseEvent.getSceneX();
            orgSceneY = mouseEvent.getSceneY();
            orgTranslateX = ((Canvas) (mouseEvent.getSource())).getTranslateX();
            orgTranslateY = ((Canvas) (mouseEvent.getSource())).getTranslateY();
        }
    };

    //On Drag
    private static EventHandler<MouseEvent> canvasOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            mouseEvent.getEventType();
            offsetX = mouseEvent.getSceneX() - orgSceneX;
            offsetY = mouseEvent.getSceneY() - orgSceneY;
            newTranslateX = orgTranslateX + offsetX;
            newTranslateY = orgTranslateY + offsetY;
        }
    };

    //On Release
    private static EventHandler<MouseEvent> canvasOnMouseReleaseEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            shiftX += newTranslateX;
            shiftY += newTranslateY;
        }
    };

    public static double getShiftX() {
        return shiftX;
    }

    public static double getShiftY() {
        return shiftY;
    }

    public static EventHandler<MouseEvent> getCanvasOnMousePressedEventHandler() {
        return canvasOnMousePressedEventHandler;
    }

    public static EventHandler<MouseEvent> getCanvasOnMouseDraggedEventHandler() {
        return canvasOnMouseDraggedEventHandler;
    }

    public static EventHandler<MouseEvent> getCanvasOnMouseReleaseEventHandler() {
        return canvasOnMouseReleaseEventHandler;
    }
}
