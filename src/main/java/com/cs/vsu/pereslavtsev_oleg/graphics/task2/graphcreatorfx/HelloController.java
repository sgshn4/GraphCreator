package com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx;

import com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx.parser.MatchParser;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelloController {

    @FXML
    VBox vBox;

    @FXML
    HBox hBox;

    @FXML
    TextField textField;

    @FXML
    private Canvas canvas;

    ArrayList<Point2D> points = new ArrayList<Point2D>();
    private boolean isSelected;
    private List<Primitive> primitives = new ArrayList<>();

    @FXML
    private void initialize() {
        Primitive line = Figures.createLine(300, 300, 400, 400);
        Primitive rect = Figures.createRectangle(-50, -50, 50, 50);
        CanvasController.init(canvas);
        primitives.add(line);
        primitives.add(rect);
        CanvasController.setFigures(primitives);

        canvas.setOnMousePressed(CanvasController.canvasOnMousePressedEventHandler);
        canvas.setOnMouseDragged(CanvasController.canvasOnMouseDraggedEventHandler);
        canvas.setOnMouseReleased(CanvasController.canvasOnMouseReleaseEventHandler);

    }

    @FXML
    private void buttonAction() {
            CanvasController.addFigure(textField.getText());
    }

    @FXML
    private void checkBoxAction() {
        isSelected = !isSelected;
        CanvasController.setIsAxisVisible(isSelected);
    }
}