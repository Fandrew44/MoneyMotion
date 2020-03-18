package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerQuit implements Initializable {

    private SceneManager sceneManager;

    @FXML
    private Button yes;

    @FXML
    private Button no;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManager();
        sceneManager.hoverEffect(yes);
        sceneManager.hoverEffect(no);
    }

    @FXML
    //EFFECTS: Saves the contacts to file and exits the program
    public void saveContacts() {
        //TODO: IMPLEMENT SAVING THE CONTACTS
        sceneManager.quitProgram();
    }

    @FXML
    //EFFECTS: Exits the program without saving the contacts to file
    public void doNotSaveContacts() {
        sceneManager.quitProgram();
    }
}
