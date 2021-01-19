package project.model;

import com.sun.javafx.geom.Line2D;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class SchemeGenerator extends AnchorPane {
    //public AnchorPane anchorPane;
    public ArrayList<Character> negativeOperands = new ArrayList<>(); //тут все негативные операнды
    public ArrayList<Character> positiveOperands = new ArrayList<>(); //все позитивные опернады
    public ArrayList<Character> theFormula = new ArrayList<>(); //строка из высчитывают все опернады, входящие в выражение
    public ArrayList<Point2D> negativeInputs = new ArrayList<>(); //негативные переменные, от которых идут линии
    public ArrayList<Point2D> positiveInputs = new ArrayList<>(); //позитивные переменные, от которых идут линии
    public ArrayList<Line> lineArrayList = new ArrayList<>();
    int numberOfPositiveInputs = 0;
    int numberOfNegativeInputs = 0;
    double NegCoordinateXStartPoint = 200;
    double PosCoordinateXStartPoint = 0;
    double coordinateYStartPoint = 40;
    double lineSegment = 50;

    public ArrayList<Character> getNegativeInputs(String expression) {
        //ArrayList<Character> convertedString = new ArrayList<>();
        for (Character character : expression.toCharArray()) {
            theFormula.add(character);
        }
        for (int i = 0; i < theFormula.size(); i++) {
            if (theFormula.get(i) == '!') {
                if (theFormula.get(i + 1) >= 'A' && theFormula.get(i + 1) <= 'Z') {
                    System.out.println("Got it");
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

    public void buildInputLines() {
        //Point2D startPoint = new Point2D(100,20);
        for (int i = 0; i < numberOfNegativeInputs; i++) {
            Point2D point2D = new Point2D(NegCoordinateXStartPoint + (25 * i), coordinateYStartPoint);
            negativeInputs.add(point2D);
        }

        if (!negativeInputs.isEmpty()) {
            PosCoordinateXStartPoint = negativeInputs.get(negativeInputs.size() - 1).getX() + 100.0;
        }
        for (int j = 0; j < numberOfPositiveInputs; j++) {
            Point2D point2D = new Point2D(PosCoordinateXStartPoint + (25 * j), coordinateYStartPoint);
            positiveInputs.add(point2D);
        }
        int totalOperand = negativeOperands.size() + positiveOperands.size();
        double lineWidth = totalOperand * lineSegment;
        for(int z = 0; z < negativeInputs.size(); z++){
            Line line = new Line(negativeInputs.get(z).getX(), negativeInputs.get(z).getY(), negativeInputs.get(z).getX(), negativeInputs.get(z).getY() + lineWidth);
            //Line line = new Line(100,200,200 ,10);
            line.setStrokeWidth(3.0);
            lineArrayList.add(line);
            line.setFill(Color.BLACK);
            getChildren().add(line);
        }
    }

}
