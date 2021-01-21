package project.model;

import javafx.geometry.Point2D;

public class ConnectPoint {
    public String name;
    public Point2D point2D;

    public ConnectPoint(String name, Point2D point2D) {
        this.name = name;
        this.point2D = point2D;
    }

    @Override
    public String toString() {
        return "ConnectPoint{" +
                "name='" + name + '\'' +
                ", point2D=" + point2D +
                '}';
    }
}
