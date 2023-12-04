package com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    Label text;

    @FXML
    TextField textField;

    @FXML
    private Canvas canvas;

    @FXML
    ListView<TextField> list;


    private boolean isSelected;
    private List<Primitive> primitives = new ArrayList<>();

    @FXML
    private void initialize() {
        Primitive line = Figures.createLine(300, 300, 400, 400);
        Primitive rect = Figures.createRectangle(-50, -50, 50, 50);
        CanvasController.init(canvas);
        primitives.add(line);
        primitives.add(rect);

        canvas.setOnMousePressed(CanvasController.canvasOnMousePressedEventHandler);
        canvas.setOnMouseDragged(CanvasController.canvasOnMouseDraggedEventHandler);
        canvas.setOnMouseReleased(CanvasController.canvasOnMouseReleaseEventHandler);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                text.setText("X: " + CanvasController.getShiftX() + " Y: " + CanvasController.getShiftY());
            }
        });
    }

    @FXML
    private void buttonAction() {
            CanvasController.mathFigure(textField.getText());
    }

    @FXML
    private void checkBoxAction() {
        isSelected = !isSelected;
        CanvasController.setIsAxisVisible(isSelected);
    }


}