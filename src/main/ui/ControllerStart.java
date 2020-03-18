package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManager();
        sceneManager.hoverEffect(start);
        sceneManager.hoverEffect(quit);
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

    //TODO: EVERYTHING BELOW IS FOR TESTING PURPOSES ONLY!!!!
//    @FXML
//    public void quitButton() {
//        Platform.exit();
//    }
//
//    @FXML
//    public void quitButtonTwo() {
//        Platform.exit();
//    }
//
//    @FXML
//    public void loadMenuScene(ActionEvent actionEvent) throws IOException {
//        updateScene(menuFile, actionEvent);
//
////        Parent menuParent = FXMLLoader.load(getClass().getResource("MenuUI.fxml"));
////        Scene menuScene = new Scene(menuParent, 600, 600);
////
////        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
////        window.setTitle("MoneyMotion");
////        window.setScene(menuScene);
////        window.show();
//    }

}
