package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//Controller for the start menu scene
public class ControllerStart implements Initializable {

    private SceneManager sceneManager;

    @FXML
    private Button start;

    @FXML
    private Button quit;

    @FXML
    private ImageView mainLogo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManager();
        sceneManager.hoverEffect(start);
        sceneManager.hoverEffect(quit);

        try {
            FileInputStream imageInput = new FileInputStream("./data/MainLogo.png");
            Image image = new Image(imageInput);
            mainLogo.setImage(image);
        } catch (FileNotFoundException e) {
            sceneManager.quitProgram();
        }
    }

    @FXML
    //EFFECTS: Changes to the main menu scene
    public void start(ActionEvent actionEvent) throws IOException {
        sceneManager.updateScene(sceneManager.menuScene, actionEvent);
    }

    @FXML
    //EFFECTS: Changes to the quit scene
    public void quit(ActionEvent actionEvent) throws IOException {
        sceneManager.updateScene(sceneManager.quitScene, actionEvent);
    }

}
