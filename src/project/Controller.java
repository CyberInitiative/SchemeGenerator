package project;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import project.model.Element;

public class Controller{

    @FXML private AnchorPane anchorPane;
    @FXML private AnchorPane workPane;

    Element element;
    //double orgX, orgY, changeInX, changeInY;

    @FXML
    private void initialize() {
    }

    @FXML
    private void addNewElement(){
        element = new Element();
        element.relocate(100,20);
        anchorPane.getChildren().add(element);
        System.out.println(element.getScaleY());
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