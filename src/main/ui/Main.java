package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import persistence.ContactsReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

// Begins the program when run
public class Main extends Application implements Initializable {

    private static Stage mainStage;
    private static Stage popupWindow;


    //TODO: This method loads saved data
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataState.init();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        //Button button = new Button("Test Button");
        //StackPane layout = new StackPane();
        //layout.getChildren().add(button);
        mainStage = primaryStage;
        Parent mainMenu = FXMLLoader.load(getClass().getResource("StartUI.fxml"));
        mainStage.setTitle("MoneyMotion");
        mainStage.setScene(new Scene(mainMenu, 600, 600));
        DataState.init();
        mainStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

//    @FXML
//    public static void createPopupWindow(String file) throws IOException {
//        Parent popupScene = FXMLLoader.load(Main.class.getResource(file));
//        popupWindow = new Stage();
//        popupWindow.initModality(Modality.APPLICATION_MODAL);
//        popupWindow.initOwner(mainStage);
//        popupWindow.setScene(new Scene(popupScene));
//        popupWindow.showAndWait();
//    }
//
//    @FXML
//    public static void closePopupWindow() {
//        popupWindow.close();
//    }
}
