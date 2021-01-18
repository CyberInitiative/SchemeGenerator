package project.model;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

public class Element extends AnchorPane {
    private String expression;
    private Shape body;
    private Line output;
    private Shape fullElement;

    public Element(){

        Shape body = new Rectangle(75,50);
        Line output = new Line(74,25,100,25);
        output.setStrokeWidth(2);
        Shape fullElement = Shape.union(body, output);
        fullElement.setFill(javafx.scene.paint.Color.WHITE);
        fullElement.setStrokeType(StrokeType.INSIDE);
        fullElement.setStroke(Color.BLACK);
        fullElement.setStrokeWidth(2);
        getChildren().addAll(fullElement);
    }


    /*
    javafx.scene.shape.Shape shape;
    shape = new Rectangle(75,50);
    Line line;
    line = new Line(74,25,100,25);
        line.setStrokeWidth(2);
    javafx.scene.shape.Shape element;
    element = Shape.union(shape,line);
        element.setFill(javafx.scene.paint.Color.WHITE);
        element.setStroke(Color.BLACK);
        element.setStrokeType(StrokeType.INSIDE);
        element.setStrokeWidth(2);
        element.relocate(100,20);
        anchorPane.getChildren().add(element);
     */
}
