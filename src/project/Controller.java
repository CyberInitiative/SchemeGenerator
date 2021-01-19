package project;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import project.model.Element;
import project.model.FormulaDecoder;
import project.model.RPN_Interpreter;
import project.model.SchemeGenerator;

public class Controller {

    SchemeGenerator schemeGenerator = new SchemeGenerator();
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane workPane;

    Element element;
    //double orgX, orgY, changeInX, changeInY;

    @FXML
    private void initialize() {
        //SchemeGenerator schemeGenerator = new SchemeGenerator();
        //System.out.println(schemeGenerator.getNegativeInputs("!A*!(B+C)+!(A+!B)*D+A*(!C*!D+!B*C)"));
        //schemeGenerator.getPositiveInputs();

        RPN_Interpreter parser = new RPN_Interpreter();
        //System.out.println("(A+B)+(A*C)*(D*(A+D)+C)");
        String expr = parser.parse("!A*!(B+C)+!(A+!B)*D+A*(!C*!D+!B*C)");


        FormulaDecoder decoder = new FormulaDecoder();
        System.out.println(decoder.decoding(expr));

        System.out.println(schemeGenerator.negativeOperands);
        System.out.println(schemeGenerator.positiveOperands);
        System.out.println(schemeGenerator.negativeInputs);
        System.out.println(schemeGenerator.positiveInputs);
        System.out.println(schemeGenerator.lineArrayList);
        //schemeGenerator.positiveInputs.sort(Character::compareTo);
        //schemeGenerator.negativeOperands.sort(Character::compareTo);
    }

    @FXML
    private void addNewElement() {
        SchemeGenerator schemeGenerator = new SchemeGenerator();
        System.out.println(schemeGenerator.getNegativeInputs("!A*!(B+C)+!(A+!B)*D+A*(!C*!D+!B*C)"));
        schemeGenerator.getPositiveInputs();
        schemeGenerator.buildInputLines();
        //element = new Element();
        //element.relocate(100,20);
        //circle.relocate(200, 40);
        //anchorPane.getChildren().add(element);
        //anchorPane.getChildren().add(circle);
        //System.out.println(element.getScaleY());
        for (int i = 0; i < schemeGenerator.lineArrayList.size(); i++) {
            anchorPane.getChildren().add(schemeGenerator.lineArrayList.get(i));
        }
        anchorPane.getChildren().addAll(schemeGenerator);
    }
}


/*
        workPane.setOnMouseEntered((event) -> {
            orgX = event.getSceneX();
            orgY = event.getSceneY();
        });
        workPane.setOnMouseMoved((event) -> {
            changeInX = event.getSceneX() - orgX;
            changeInY = event.getSceneY() - orgY;
            System.out.println(orgX);
            System.out.println(orgY);

            if (changeInX > 0) {
                //System.out.println("moving right");
            }
            else if (changeInX < 0) {
                //System.out.println("moving left");
            }
            if (changeInY > 0) {
                //System.out.println("moving down");
            }
            else if (changeInY < 0) {
                //System.out.println("moving up");
            }
            orgX = event.getSceneX();
            orgY = event.getSceneY();
        });

         */