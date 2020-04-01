package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

//Controller for the Quit scene
public class ControllerQuit implements Initializable {

    private SceneManager sceneManager;
    private ButtonManager buttonManager;

    @FXML
    private Button yes;

    @FXML
    private Button no;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManager();
        buttonManager = new ButtonManager();
        buttonManager.hoverEffect(yes);
        buttonManager.hoverEffect(no);
    }

    @FXML
    //EFFECTS: Saves the contacts to file and exits the program
    public void saveContacts() throws FileNotFoundException {
        DataState.saveContacts();
        sceneManager.quitProgram();
    }

    @FXML
    //EFFECTS: Exits the program without saving the contacts to file
    public void doNotSaveContacts() {
        sceneManager.quitProgram();
    }
}
