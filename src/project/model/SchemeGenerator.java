package project.model;

import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class SchemeGenerator extends AnchorPane {
    //public AnchorPane anchorPane;
    public ArrayList<Character> negativeOperands = new ArrayList<>(); //тут все негативные операнды
    public ArrayList<Character> positiveOperands = new ArrayList<>(); //все позитивные опернады
    public ArrayList<Character> theFormula = new ArrayList<>(); //строка из высчитывают все опернады, входящие в выражение
    public ArrayList<Point2D> negativeStartLinePoint = new ArrayList<>(); //негативные переменные, от которых идут линии // координаты точек
    public ArrayList<Point2D> positiveStartLinePoint = new ArrayList<>(); //позитивные переменные, от которых идут линии // координаты точек
    public ArrayList<ConnectPoint> connectPoints = new ArrayList<>();
    public ArrayList<ConnectPoint> connectPointsPos = new ArrayList<>();
    public ArrayList<String> totalPointsNames = new ArrayList<>();
    public ArrayList<ConnectLine> negativeLineArrayList = new ArrayList<>(); //линии от инвертированных операндов
    public ArrayList<ConnectLine> positiveLineArrayList = new ArrayList<>();
    int numberOfPositiveInputs = 0;

    int numberOfNegativeInputs = 0;
    double NegCoordinateXStartPoint = 200;
    double PosCoordinateXStartPoint = 0;
    double coordinateYStartPoint = 50;
    double lineSegment = 50;

    public ArrayList<Character> getNegativeInputs(String expression) {
        for (Character character : expression.toCharArray()) {
            theFormula.add(character);
        }
        for (int i = 0; i < theFormula.size(); i++) {
            if (theFormula.get(i) == '!') {
                if (theFormula.get(i + 1) >= 'A' && theFormula.get(i + 1) <= 'Z') {
                    //System.out.println("Got it");
                    //String result = Character.toString(convertedString.get(i));
                    //result += convertedString.get( i + 1);
                    negativeOperands.add(theFormula.get(i + 1));
                    theFormula.set(i, '#');
                    theFormula.set(i + 1, '#');
                }
            }
        }
        ArrayList<Character> counting = new ArrayList<>(negativeOperands);
        counting.sort(Character::compareTo);
        for (int i = 0; i < counting.size(); i++) {
            for (int j = i + 1; j < counting.size(); j++) {
                if (counting.get(i) == counting.get(j)) {
                    counting.set(j, '#');
                }
            }
        }
        counting.removeIf(x -> x.equals('#'));
        numberOfNegativeInputs = counting.size();
        theFormula.removeIf(x -> x.equals('#'));
        return theFormula;
    }

    public void getPositiveInputs() {
        if (!theFormula.isEmpty()) {
            for (int i = 0; i < theFormula.size(); i++) {
                if (theFormula.get(i) >= 'A' && theFormula.get(i) <= 'Z') {
                    positiveOperands.add(theFormula.get(i));
                }
            }

            ArrayList<Character> counting = new ArrayList<>(positiveOperands);
            counting.sort(Character::compareTo);
            for (int i = 0; i < counting.size(); i++) {
                for (int j = i + 1; j < counting.size(); j++) {
                    if (counting.get(i) == counting.get(j)) {
                        counting.set(j, '#');
                    }
                }
            }

            counting.removeIf(x -> x.equals('#'));
            numberOfPositiveInputs = counting.size();

        } else {
            System.out.println("Error");
        }
    }

    public void buildStartLines(String expression) {
        //точки старта для линий инвертированных операндов
        for (int i = 0; i < numberOfNegativeInputs; i++) {
            Point2D point2D = new Point2D(NegCoordinateXStartPoint + (25 * i), coordinateYStartPoint);
            negativeStartLinePoint.add(point2D);
        }
        if (!negativeStartLinePoint.isEmpty()) {
            PosCoordinateXStartPoint = negativeStartLinePoint.get(negativeStartLinePoint.size() - 1).getX() + 50.0;
        }
        //точки старта для линий неинвертированных операндов
        for (int j = 0; j < numberOfPositiveInputs; j++) {
            Point2D point2D = new Point2D(PosCoordinateXStartPoint + (25 * j), coordinateYStartPoint);
            positiveStartLinePoint.add(point2D);
        }

        int totalOperandNumber = negativeOperands.size() + positiveOperands.size(); //кол-во всех операндов в формуле
        double lineWidth = totalOperandNumber * lineSegment; // размер линии

        ArrayList<Character> counting = new ArrayList<>(negativeOperands);
        counting.sort(Character::compareTo);
        for (int i = 0; i < counting.size(); i++) {
            for (int j = i + 1; j < counting.size(); j++) {
                if (counting.get(i) == counting.get(j)) {
                    counting.set(j, '#');
                }
            }
        }
        counting.removeIf(x -> x.equals('#'));

        for (int i = 0; i < negativeStartLinePoint.size(); i++) {
            Line line = new Line(negativeStartLinePoint.get(i).getX(), negativeStartLinePoint.get(i).getY(), negativeStartLinePoint.get(i).getX(), negativeStartLinePoint.get(i).getY() + lineWidth);
            ConnectLine connectLine = new ConnectLine(counting.get(i), line);
            negativeLineArrayList.add(connectLine);
            getChildren().add(line);
        }
        ArrayList<Character> countingPositive = new ArrayList<>(positiveOperands);
        countingPositive.sort(Character::compareTo);
        for (int i = 0; i < countingPositive.size(); i++) {
            for (int j = i + 1; j < countingPositive.size(); j++) {
                if (countingPositive.get(i) == countingPositive.get(j)) {
                    countingPositive.set(j, '#');
                }
            }
        }
        countingPositive.removeIf(x -> x.equals('#'));

        for (int i = 0; i < positiveStartLinePoint.size(); i++) {
            Line line = new Line(positiveStartLinePoint.get(i).getX(), positiveStartLinePoint.get(i).getY(), positiveStartLinePoint.get(i).getX(), positiveStartLinePoint.get(i).getY() + lineWidth);
            ConnectLine connectLine = new ConnectLine(countingPositive.get(i), line);
            positiveLineArrayList.add(connectLine);
            getChildren().add(line);
        }
    }

    public void buildStartPoints(String expression) {
        ArrayList<Character> convertedString = new ArrayList<>();
        for (Character character : expression.toCharArray()) {
            convertedString.add(character);
        }
        for (int q = 0; q < convertedString.size(); q++) {
            if (convertedString.get(q) >= 'A' && convertedString.get(q) <= 'Z') {
                if (q > 0) {
                    if (convertedString.get(q - 1) == '!') {
                        for (int w = 0; w < negativeLineArrayList.size(); w++) {
                            if (negativeLineArrayList.get(w).name == convertedString.get(q)) {
                                String name = convertedString.get(q - 1).toString() + convertedString.get(q).toString();
                                totalPointsNames.add(name);
                                convertedString.set(q, '#');
                            }
                        }
                    }
                }
                if(convertedString.get(q) != '#'){
                    for (int r = 0; r < positiveLineArrayList.size(); r++) {
                        if (positiveLineArrayList.get(r).name == convertedString.get(q)) {
                            totalPointsNames.add(positiveLineArrayList.get(r).name.toString());
                        }
                    }
                }
            }
        }
        for(int z = 0; z < totalPointsNames.size(); z++){
            if(totalPointsNames.get(z).length() > 1){
                if(z == 0){
                    for(int x = 0; x < negativeLineArrayList.size(); x++){
                        if(negativeLineArrayList.get(x).name.equals(totalPointsNames.get(z).charAt(1))){
                            Point2D firstPoint = new Point2D(negativeLineArrayList.get(x).line.getStartX(), negativeLineArrayList.get(x).line.getStartY() + 25);
                            ConnectPoint connectPoint = new ConnectPoint(totalPointsNames.get(z), firstPoint);
                            connectPoints.add(connectPoint);
                            Circle circle = new Circle(firstPoint.getX(), firstPoint.getY(), 3);
                            getChildren().addAll(circle);
                            //System.out.println(firstPoint);
                        }
                    }
                }
                else {
                    for(int x = 0; x < negativeLineArrayList.size(); x++){
                        if(negativeLineArrayList.get(x).name.equals(totalPointsNames.get(z).charAt(1))){
                            Point2D point2D = new Point2D(negativeLineArrayList.get(x).line.getStartX(), connectPoints.get(connectPoints.size() - 1).point2D.getY() + 25);
                            ConnectPoint connectPoint = new ConnectPoint(totalPointsNames.get(z), point2D);
                            connectPoints.add(connectPoint);
                            Circle circle = new Circle(point2D.getX(), point2D.getY(), 3);
                            getChildren().addAll(circle);
                        }
                    }
                }
            }
        }
        //System.out.println(totalPointsNames);
    }
}

/*
public void buildStartPoints(String expression){
        ArrayList<Character> convertedString = new ArrayList<>();  //преобразовываем выражение в массив символов
        for (Character character : expression.toCharArray()) {
            convertedString.add(character);
        }
        for (int q = 0; q < convertedString.size(); q++) {
            if (convertedString.get(q) >= 'A' && convertedString.get(q) <= 'Z') {
                if (q > 0) {
                    if (convertedString.get(q - 1) == '!') {
                        for (int w = 0; w < negativeLineArrayList.size(); w++) {
                            if (negativeLineArrayList.get(w).name == convertedString.get(q)) {
                                Point2D point2D = new Point2D(negativeLineArrayList.get(w).line.getStartX(), negativeLineArrayList.get(w).line.getStartY() + 10 * q);
                                String name = (convertedString.get(q - 1).toString() + convertedString.get(q).toString());
                                ConnectPoint connectPoint = new ConnectPoint(name, point2D);
                                connectPoints.add(connectPoint);
                                Circle circle = new Circle(point2D.getX(), point2D.getY(), 3);
                                getChildren().addAll(circle);
                                convertedString.set(q, '#');
                            }
                        }
                    }
                }
                if (convertedString.get(q) != '#') {
                    for (int r = 0; r < positiveLineArrayList.size(); r++) {
                        if (positiveLineArrayList.get(r).name == convertedString.get(q)) {
                            Point2D point2D = new Point2D(positiveLineArrayList.get(r).line.getStartX(), positiveLineArrayList.get(r).line.getStartY() + 10 * q);
                            String name = convertedString.get(q).toString();
                            ConnectPoint connectPoint = new ConnectPoint(name, point2D);
                            connectPointsPos.add(connectPoint);
                            Circle circle = new Circle(point2D.getX(), point2D.getY(), 3);
                            getChildren().addAll(circle);
                        }
                    }
                }
            }
        }
    }

 */