package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataState.init();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        mainStage = primaryStage;
        Parent mainMenu = FXMLLoader.load(getClass().getResource("StartUI.fxml"));
        mainStage.setTitle("MoneyMotion");
        Scene scene = new Scene(mainMenu, 600, 600);
        URL styleSheetFile = getClass().getResource("MainLogoStylesheet.css");
        String styleSheet = String.valueOf(styleSheetFile);
        scene.getStylesheets().add(styleSheet);
        mainStage.setScene(scene);
        DataState.init();
        mainStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
