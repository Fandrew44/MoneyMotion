package ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//Controller for the Categories scene
public class ControllerCategories implements Initializable {

    private SceneManager sceneManager;

    @FXML
    private Button debts;

    @FXML
    private Button loans;

    @FXML
    private Button neutral;

    @FXML
    private Button menu;

    @FXML
    private Label blankLabel;

    @FXML
    private ImageView logo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManager();
        sceneManager.hoverEffect(debts);
        sceneManager.hoverEffect(loans);
        sceneManager.hoverEffect(neutral);
        sceneManager.hoverEffect(menu);

        try {
            FileInputStream imageInput = new FileInputStream("./data/Logo.png");
            Image image = new Image(imageInput);
            logo.setImage(image);
        } catch (FileNotFoundException e) {
            sceneManager.quitProgram();
        }
    }

    @FXML
    //EFFECTS: Changes the scene to debts scene
    public void debtsCategory(ActionEvent actionEvent) throws IOException {
        sceneManager.updateScene(sceneManager.debtsScene, actionEvent);
    }

    @FXML
    //EFFECTS: Changes the scene to loans scene
    public void loansCategory(ActionEvent actionEvent) throws IOException {
        sceneManager.updateScene(sceneManager.loansScene, actionEvent);
    }

    @FXML
    //EFFECTS: Changes the scene to neutral scene
    public void neutralCategory(ActionEvent actionEvent) throws IOException {
        sceneManager.updateScene(sceneManager.neutralScene, actionEvent);
    }

    @FXML
    //EFFECTS: Changes the scene to menu scene
    public void menu(ActionEvent actionEvent) throws IOException {
        sceneManager.updateScene(sceneManager.menuScene, actionEvent);
    }
}
