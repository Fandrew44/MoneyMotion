package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


//Controller for the main menu scene
public class ControllerMenu implements Initializable {

    private SceneManager sceneManager;

    @FXML
    protected Button createContact;

    @FXML
    protected Button categories;

    @FXML
    protected Button saveContacts;

    @FXML
    protected Button quit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManager();
        sceneManager.hoverEffect(createContact);
        sceneManager.hoverEffect(categories);
        sceneManager.hoverEffect(saveContacts);
        sceneManager.hoverEffect(quit);
    }

    @FXML
    //EFFECTS: Changes to the create contact scene
    public void createNewContact(ActionEvent actionEvent) throws IOException {
        sceneManager.updateScene(sceneManager.createContactScene, actionEvent);
    }

    @FXML
    //EFFECTS: Changes to the categories scene
    public void categories(ActionEvent actionEvent) throws IOException {
        sceneManager.updateScene(sceneManager.categoriesScene, actionEvent);
    }

    @FXML
    //EFFECTS: Writes all contacts and their properties to file
    public void saveContacts(ActionEvent actionEvent) throws IOException {
        //TODO: IMPLEMENT SAVING THE CONTACTS
        //TODO: IMPLEMENT A POP-UP WINDOW
    }

    @FXML
    //EFFECTS: Exits the program
    public void quit(ActionEvent actionEvent) throws IOException {
        sceneManager.updateScene(sceneManager.quitScene, actionEvent);
    }

    //TODO: EVERYTHING BELOW IS FOR TESTING PURPOSES ONLY!!!!
//    public void testExitButton() {
//        Platform.exit();
//    }
}
