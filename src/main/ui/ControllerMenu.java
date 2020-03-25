package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


//Controller for the main menu scene
public class ControllerMenu implements Initializable {

    private SceneManager sceneManager;
    private ButtonManager buttonManager;

    @FXML
    protected Button createContact;

    @FXML
    protected Button categories;

    @FXML
    protected Button saveContacts;

    @FXML
    protected Button quit;

    @FXML
    private Label totalFinances;

    @FXML
    private ImageView logo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManager();
        buttonManager = new ButtonManager();
        buttonManager.hoverEffect(createContact);
        buttonManager.hoverEffect(categories);
        buttonManager.hoverEffect(saveContacts);
        buttonManager.hoverEffect(quit);
        totalFinances.setText("Current Financial Situation: " + DataState.printFinancialSituation());

        try {
            FileInputStream imageInput = new FileInputStream("./data/Logo.png");
            Image image = new Image(imageInput);
            logo.setImage(image);
        } catch (FileNotFoundException e) {
            sceneManager.quitProgram();
        }
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
        DataState.saveContacts();
    }

    @FXML
    //EFFECTS: Exits the program
    public void quit(ActionEvent actionEvent) throws IOException {
        sceneManager.updateScene(sceneManager.quitScene, actionEvent);
    }
}
