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
    private MatchParser parser;
    private List<Primitive> primitives = new ArrayList<>();

    @FXML
    private void initialize() {
//        vBox.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
//        vBox.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));
        parser = new MatchParser();
        Primitive line = Figures.createLine(300, 300, 400, 400);
        Primitive rect = Figures.createRectangle(-50, -50, 50, 50);
        Primitive round = Figures.createRound(600, 600, 100);
        CanvasController.init(canvas);
        primitives.add(round);
        primitives.add(line);
        primitives.add(rect);
        CanvasController.setFigures(primitives);

        canvas.setOnMousePressed(CanvasController.canvasOnMousePressedEventHandler);
        canvas.setOnMouseDragged(CanvasController.canvasOnMouseDraggedEventHandler);
        canvas.setOnMouseReleased(CanvasController.canvasOnMouseReleaseEventHandler);

    }

    @FXML
    private void buttonAction() {
            try {
                parser.Parse(textField.getText());
                HashMap<String, Double> variables = parser.getVariables();
                for (String i : variables.keySet()) {
                    int[] pointsX = new int[(int)(canvas.getWidth())];
                    int[] pointsY = new int[(int)(canvas.getWidth())];
                    for (int x = (int)(0 - canvas.getWidth() / 2); x < (int)(canvas.getWidth() / 2); x++) {
                        parser.setVariable(i, (double)(x));
                        pointsX[(int)(x + canvas.getWidth() / 2)] = x;
                        pointsY[(int)(x + canvas.getWidth() / 2)] = (int)(parser.Parse(textField.getText()));
                    }
                    primitives.add(new Primitive(pointsX, pointsY));
                    CanvasController.setFigures(primitives);
                    System.out.println("Added");
                }
            } catch (Exception e) {
                System.err.println("err: " + e);
            }
    }

    @FXML
    private void checkBoxAction() {
        isSelected = !isSelected;
        CanvasController.setIsAxisVisible(isSelected);
    }
}