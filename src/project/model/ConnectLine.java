package project.model;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class ConnectLine extends AnchorPane {
    public Character name;
    public Line line;

    public ConnectLine(Character name, Line line) {
        this.name = name;
        this.line = line;
    }

    @Override
    public String toString() {
        return "ConnectLine{" +
                "name=" + name +
                ", line=" + line +
                '}';
    }
}
