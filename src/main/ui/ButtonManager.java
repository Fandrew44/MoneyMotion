package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Contact;

import java.io.IOException;

//Manages the general operations for buttons in SceneManager
public class ButtonManager {

    //Inspiration taken from StackOverflow
    protected static final String IDLE_BUTTON = "-fx-background-color: transparent;";

    //Inspiration taken from StackOverflow
    protected static final String HOVERED_BUTTON = "-fx-background-color: -fx-shadow-highlight-color, "
            + "-fx-outer-border, -fx-inner-border, -fx-body-color;";

    @FXML
    //EFFECTS: Adds a hover effect to the button
    public void hoverEffect(Button b) {
        b.setStyle(IDLE_BUTTON);
        b.setOnMouseEntered(e -> b.setStyle(HOVERED_BUTTON));
        b.setOnMouseExited(e -> b.setStyle(IDLE_BUTTON));
    }

}
